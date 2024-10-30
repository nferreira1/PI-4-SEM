package br.edu.senac.services;

import br.edu.senac.exceptions.ErrorResponseException;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.Optional;

@Slf4j
@Service
public class ImageManagerService {

    private static final String localDirectory = "./temp/images/";

    @Value("${DIRECTORY_SERVER_IMAGES}")
    private String directoryServerImages;

    @Value("${VM_IP}")
    private String vmIP;

    public Optional<String> insert(String base64Image, String subDirectory, String finalImageName) {
        isValidImage(base64Image);

        var extension = base64Image.split("/")[1].split(";")[0];
        var filenameWithExtensionImage = finalImageName.replaceAll(" ", "_") + "." + extension;
        var base64ImageString = base64Image.split(",")[1];

        String localFilePath = localDirectory + filenameWithExtensionImage;
        saveBase64ImageToFile(base64ImageString, localFilePath);

        Path destinationPath = Paths.get(directoryServerImages, subDirectory, filenameWithExtensionImage);

        try {
            createDirectoryIfNotExists(destinationPath.getParent().toString());

            Files.copy(Paths.get(localFilePath), destinationPath, StandardCopyOption.REPLACE_EXISTING);

            log.info("Imagem salva com sucesso no servidor: {}", destinationPath);
            return Optional.of("http://" + vmIP + directoryServerImages + subDirectory + "/" + filenameWithExtensionImage);
        } catch (Exception e) {
            throw new ErrorResponseException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao mover a imagem para o servidor.");
        } finally {
            try {
                deleteTemporaryFile(localFilePath);
            } catch (IOException e) {
                log.error("Erro ao excluir o arquivo temporário: {}", e.getMessage());
            }
        }
    }

    public void delete(String imageUrl) {
        String baseUrl = "http://" + vmIP;
        String filePath = imageUrl.replace(baseUrl, "");

        Path imagePath = Paths.get(filePath);

        try {
            if (Files.exists(imagePath)) {
                Files.delete(imagePath);
                log.info("Imagem excluída com sucesso: {}", imagePath.toString());
            } else {
                throw new ErrorResponseException(HttpStatus.NOT_FOUND, "Imagem não encontrada.");
            }
        } catch (IOException e) {
            throw new ErrorResponseException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao excluir a imagem.");
        }
    }

    public Optional<String> update(String currentImage, String base64Image, String finalImageName) {
        String baseUrl = "http://" + vmIP + directoryServerImages;
        String relativePath = currentImage.replace(baseUrl, "");
        String subDirectory = relativePath.contains("/") ? relativePath.substring(0, relativePath.indexOf("/")) : "";

        this.delete(currentImage);

        return this.insert(base64Image, subDirectory, finalImageName);
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
