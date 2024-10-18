package br.edu.senac.Services;

import br.edu.senac.Exceptions.ErrorResponseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class ImageManagerService {

    private static final String localDirectory = "./temp/images/";

    @Value("${IP_SERVER_IMAGES}")
    private String ipServerImages;

    @Value("${USERNAME_SERVER_IMAGES}")
    private String usernameServerImages;

    @Value("${DIRECTORY_SERVER_IMAGES}")
    private String directoryServerImages;

    @Value("${VM_IP}")
    private String vmIP;

    @Value("${DIRECTORY_AZURE_IMAGES}")
    private String directoryAzureImages;

    public Optional<Boolean> findByPath(String path) {
        try {
            URL url = new URL("http://" + ipServerImages + directoryServerImages + path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");

            int responseCode = connection.getResponseCode();
            return Optional.of(responseCode == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<String> insert(String base64Image, String subDirectory, String finalImageName) {
        isValidImage(base64Image);

        var extension = base64Image.split("/")[1].split(";")[0];
        var filenameWithExtensionImage = finalImageName + "." + extension;
        var base64ImageString = base64Image.split(",")[1];

        String localFilePath = localDirectory + filenameWithExtensionImage;
        String remoteDirectoryPath = directoryServerImages + subDirectory;
        String azureDirectoryPath = directoryAzureImages + subDirectory + "/" + filenameWithExtensionImage;

        try {
            saveBase64ImageToFile(base64ImageString, localFilePath);
            uploadImage(filenameWithExtensionImage, remoteDirectoryPath);
            log.info("Imagem enviada com sucesso para o servidor: {}{}", ipServerImages, remoteDirectoryPath + filenameWithExtensionImage);
            return Optional.of("http://" + vmIP + azureDirectoryPath);
        } catch (IOException | InterruptedException e) {
            log.error("Erro ao processar a imagem: {}", e.getMessage());
            return Optional.empty();
        } finally {
            try {
                deleteTemporaryFile(localFilePath);
            } catch (IOException e) {
                log.error("Erro ao excluir o arquivo temporário: {}", e.getMessage());
            }
        }
    }

    public Optional<String> update(String currentImage, String base64Image, String finalImageName) {
        isValidImage(base64Image);
        this.delete(currentImage);

        Pattern pattern = Pattern.compile(".*?/images(.*)");
        Matcher matcher = pattern.matcher(currentImage);
        String subDirectory = null;

        if (matcher.find()) {
            subDirectory = matcher.group(1).substring(0, matcher.group(1).lastIndexOf("/"));
        } else {
            throw new ErrorResponseException(HttpStatus.BAD_REQUEST, "Ocorreu um erro ao tentar atualizar a imagem.");
        }

        return this.insert(base64Image, subDirectory, finalImageName);
    }

    public void delete(String path) {
        this.findByPath(path).orElseThrow(() -> new ErrorResponseException(HttpStatus.NOT_FOUND, "Imagem não encontrada."));

        Pattern pattern = Pattern.compile("/images(.*)");
        Matcher matcher = pattern.matcher(path);

        if (matcher.find()) {
            path = matcher.group(1);
        } else {
            throw new ErrorResponseException(HttpStatus.BAD_REQUEST, "Ocorreu um erro ao tentar excluir a imagem.");
        }

        String command = String.format("ssh -o StrictHostKeyChecking=no %s@%s rm %s", usernameServerImages, ipServerImages, directoryServerImages + path);

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                log.info("Arquivo excluído com sucesso.");
            } else {
                throw new ErrorResponseException(HttpStatus.BAD_REQUEST, "Ocorreu um erro ao tentar excluir a imagem.");
            }
        } catch (Exception e) {
            throw new ErrorResponseException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro interno no servidor.");
        }
    }

    private void isValidImage(String base64Image) {
        try {
            if (!base64Image.contains(",") || base64Image.split(",").length < 2) {
                throw new ErrorResponseException(HttpStatus.BAD_REQUEST, "Formato de imagem inválido.");
            }

            String base64ImageString = base64Image.split(",")[1];
            byte[] imageBytes = Base64.getDecoder().decode(base64ImageString);

            BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageBytes));
            if (image == null) {
                throw new ErrorResponseException(HttpStatus.BAD_REQUEST, "Insira uma imagem válida.");
            }

            log.info("Imagem validada com sucesso.");

        } catch (IllegalArgumentException e) {
            throw new ErrorResponseException(HttpStatus.BAD_REQUEST, "A string base64 da imagem é inválida.");
        } catch (IOException e) {
            throw new ErrorResponseException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro interno ao processar a imagem.");
        }
    }

    private void saveBase64ImageToFile(String base64Image, String localFilePath) {
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);

        try (FileOutputStream fos = new FileOutputStream(localFilePath)) {
            createDirectoryIfNotExists(localDirectory);
            fos.write(imageBytes);
        } catch (Exception e) {
            throw new ErrorResponseException(HttpStatus.BAD_REQUEST, "Ocorreu um erro ao salvar a imagem.");
        }
    }

    private void uploadImage(String finalImageName, String remoteDirectoryPath) throws IOException, InterruptedException {
        String command = String.format("scp %s %s@%s:%s/%s", localDirectory + finalImageName, usernameServerImages, ipServerImages, remoteDirectoryPath, finalImageName);
        log.info(command);
        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
        Process process = processBuilder.start();
        int exitCode = process.waitFor();

        if (exitCode == 0) {
            log.info("Arquivo enviado com sucesso para o servidor.");
        } else {
            throw new IOException("Falha ao enviar o arquivo. Código de saída: " + exitCode);
        }
    }

    private void deleteTemporaryFile(String localFilePath) throws IOException {
        File file = new File(localFilePath);

        if (file.exists()) {
            Files.delete(file.toPath());
            log.info("Arquivo temporário excluído: {}", localFilePath);
        } else {
            log.info("Arquivo temporário não encontrado para exclusão.");
        }
    }

    private void createDirectoryIfNotExists(String directoryPath) throws IOException {
        Path path = Paths.get(directoryPath);

        if (!Files.exists(path)) {
            Files.createDirectories(path);
            log.info("Diretório criado: {}", directoryPath);
        } else {
            log.info("Diretório já existe: {}", directoryPath);
        }
    }
}
