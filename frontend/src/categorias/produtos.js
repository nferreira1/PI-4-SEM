import { produtosRecomendados } from "../components/produtosRecomendados.js";
import { api } from "../lib/api.js";

const produtosRecomendadosSection = document.getElementById("produtos");

const handleFetchProdutos = async (categoriaId) => {
    const {data, error, response, loading} = await api.GET(`/produto/categoria/${categoriaId}`);
    return {data, error, response, loading};
};

document.addEventListener("DOMContentLoaded", async () => {
    const params = new URLSearchParams(window.location.search);
    const categoriaId = params.get("categoriaId");

    const {data} = await handleFetchProdutos(categoriaId);

    produtosRecomendados(produtosRecomendadosSection, data);
});