import { toast } from "../components/toast.js";
import { api } from "../lib/api.js";

const sectionCategorias = document.getElementById("categorias");

const handleFetchCategorias = async () => {

    sectionCategorias.innerHTML = `<div>Carregando...</div>`;

    // await new Promise(() => {
    // });

    const {data, loading, error} = await api.GET("/categoria");

    if (error) {
        toast.error("Erro!", "Ocorreu um erro!");
    }

    if (!loading) {
        sectionCategorias.innerHTML = data.map(categoria => `
            <a href="/categorias/produtos?categoriaId=${categoria.id}">
                <div class="w-40 h-48 flex flex-col rounded-md overflow-hidden cursor-pointer">
                    <div class="grow flex items-center justify-center bg-gradient-to-tr from-primary to-primary/30">
                        <img alt={categoria.nome} src=${categoria.imagem} class="object-contain size-32">
                    </div>
                
                    <div class="h-10 w-full bg-muted grid place-items-center">
                        <span class="text-xs truncate font-semibold">${categoria.nome}</span>
                    </div>
                </div>
            </a>
        `).join("");
    }
};

document.addEventListener("DOMContentLoaded", () => {

    handleFetchCategorias();
});
