import { produtos } from "./components/produtos.js";
import { toast } from "./components/toast.js";
import { api } from "./lib/api.js";

const sectionCategorias = document.getElementById("sectionCategorias");
const sectionTodasCategorias = document.getElementById("sectionTodasCategorias");

const handleFetchCategorias = async () => {

    sectionCategorias.innerHTML = `<div>Carregando...</div>`;
    // sectionTodasCategorias.innerHTML = `<div>Carregando...</div>`;

    // await new Promise(() => {
    // });

    const {data: dataCategorias, loading: loadingCategorias, error: errorCategorias} = await api.GET("/categoria");

    if (errorCategorias) {
        toast.error("Erro!", "Ocorreu um erro!");
    }

    if (!loadingCategorias) {
        sectionCategorias.innerHTML = dataCategorias.map(categoria => `
            <a href="/categorias/produtos?categoriaId=${categoria.id}">
                <button class="w-full rounded-lg border px-6 py-1.5 font-semibold text-sm">
                    ${categoria.nome}
                </button>
            </a>
        `).join("");
    }

    for (const categoria of dataCategorias) {
        const {
            data: dataProduto,
            loading: loadingProduto,
            error: errorProduto
        } = await api.GET(`/produto/categoria/${categoria.id}`);

        if (errorProduto) {
            toast.error("Erro!", "Ocorreu um erro!");
        }

        const sectionCategoria = document.createElement("section");
        const divSectionCategoria = document.createElement("div");
        const tituloCategoria = document.createElement("h6");

        divSectionCategoria.classList.add("flex", "pb-4", "pt-2", "gap-4");

        if (!loadingProduto) {
            produtos(divSectionCategoria, dataProduto);
        }

        tituloCategoria.textContent = `${categoria.nome}`;
        tituloCategoria.classList.add("font-semibold", "text-sm");

        sectionCategoria.insertAdjacentElement("afterbegin", tituloCategoria);
        sectionCategoria.insertAdjacentElement("beforeend", divSectionCategoria);
        sectionTodasCategorias.insertAdjacentElement("afterbegin", sectionCategoria);
    }
};

document.addEventListener("DOMContentLoaded", () => {
    handleFetchCategorias();
});
