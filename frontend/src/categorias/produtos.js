import { api } from "../lib/api.js";

const produtosSection = document.getElementById("produtos");

const handleFetchProdutos = async (categoriaId) => {
    const {data, error, response, loading} = await api.GET(`/produto/categoria/${categoriaId}`);
    return {data, error, response, loading};
};

document.addEventListener("DOMContentLoaded", async () => {
    const params = new URLSearchParams(window.location.search);
    const categoriaId = params.get("categoriaId");

    const {data} = await handleFetchProdutos(categoriaId);

    produtosSection.innerHTML = data.map(produto => {
        const imagemPrincipal = produto.imagens.find(imagem => imagem.imagemPrincipal);

        const imagem = imagemPrincipal ? imagemPrincipal.imagem : (produto.imagens.length > 0 ? produto.imagens[0].imagem : "");

        return `
            <a href="/produtos/?produtoId=${produto.id}">
                <div class="w-40 h-56 flex flex-col">
                    <div class="flex items-center justify-center grow bg-muted rounded-md">
                        <img alt=${produto.nome} src="${imagem}" class="size-24 object-cover">
                    </div>
                    <div class="h-2/6 flex flex-col pt-2 space-y-0.5">
                        <span class="text-xs truncate ">${produto.nome}</span>
                        <span class="font-semibold">
                            ${Intl.NumberFormat("pt-BR", {style: "currency", currency: "BRL"}).format(produto.valor)}
                        </span>
                    </div>
                </div>
            </a>
        `;
    }).join("");
});