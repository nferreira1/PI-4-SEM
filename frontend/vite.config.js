import fs from "fs";
import path from "path";
import { defineConfig } from "vite";

function obterArquivosHtmlDeEntrada(diretorioSrc) {
    const entrada = {};

    function percorrerDiretorio(diretorioAtual) {
        const arquivos = fs.readdirSync(diretorioAtual);

        arquivos.forEach((arquivo) => {
            const caminhoArquivo = path.join(diretorioAtual, arquivo);
            const eDiretorio = fs.statSync(caminhoArquivo).isDirectory();

            if (eDiretorio) {
                percorrerDiretorio(caminhoArquivo);
            } else if (path.extname(arquivo) === ".html") {
                const nome = path
                    .relative(diretorioSrc, caminhoArquivo)
                    .replace(/\..*$/, "")
                    .replace(/\\/g, "/");
                entrada[nome] = caminhoArquivo.replace(/\\/g, "/");
            }
        });
    }

    percorrerDiretorio(diretorioSrc);

    return entrada;
}

export default defineConfig({
    base: "./",
    root: "src",
    build: {
        rollupOptions: {
            input: obterArquivosHtmlDeEntrada(path.resolve(__dirname, "src"))
        },
        outDir: "../dist",
        emptyOutDir: true,
        assetsDir: "assets"
    },
    envDir: path.resolve(__dirname),
    optimizeDeps: {
        entries: "src/**/*{.html,.css,.js}"
    }
});