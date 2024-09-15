import { api } from "../lib/api.js";

const handleFetchProdutos = async (id) => {

    const {data, error, response, loading} = await api.GET(`/produto/${id}`);

    return {data, error, response, loading};
};

document.addEventListener("DOMContentLoaded", async () => {
    const params = new URLSearchParams(window.location.search);
    const categoriaId = params.get("id");

    const {data, error, response, loading} = await handleFetchProdutos(categoriaId);

    console.log(loading);

});
