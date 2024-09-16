import { api } from "../lib/api.js";

let imagemSelecionada;
let buttonSelecionado;
const imagemPrincipal = document.getElementById("imagemPrincipal");
const divImagens = document.getElementById("imagens");
const nomeProduto = document.getElementById("nomeProduto");
const produtoValor = document.getElementById("produtoValor");
const descricaoProduto = document.getElementById("descricaoProduto");
const produtosRecomendados = document.getElementById("produtosRecomendados");

const handleFetchProduto = async (id) => {
    const {data, error, response, loading} = await api.GET(`/produto/${id}`);
    return {data, error, response, loading};
};

const handleFetchProdutos = async () => {
    const {data, error, response, loading} = await api.GET(`/produto`);
    return {data, error, response, loading};
};

const handleSelecionarImagemProduto = (imagem, button) => {
    if (buttonSelecionado) {
        buttonSelecionado.classList.remove("border", "border-primary");
    }

    imagemSelecionada = imagem.id;
    imagemPrincipal.src = imagem.imagem;

    button.classList.add("border", "border-primary");

    buttonSelecionado = button;
};

const adicionarEventosDeCliqueNasImagens = (imagens) => {
    const buttons = divImagens.querySelectorAll("button");
    buttons.forEach((button, index) => {
        button.addEventListener("click", () => {
            handleSelecionarImagemProduto(imagens[index], button);
        });

        if (imagens[index].id === imagemSelecionada) {
            button.classList.add("border", "border-primary");
            buttonSelecionado = button;
        }
    });
};

document.addEventListener("DOMContentLoaded", async () => {
    const params = new URLSearchParams(window.location.search);
    const produtoId = params.get("produtoId");

    const {data} = await handleFetchProduto(produtoId);
    const {data: produtos} = await handleFetchProdutos();

    imagemSelecionada = data.imagens.find(imagem => imagem.imagemPrincipal)?.id;
    imagemPrincipal.src = data.imagens.find(imagem => imagem.imagemPrincipal)?.imagem || "";
    imagemPrincipal.alt = data.nome;

    divImagens.innerHTML = data.imagens.map(imagem => `
        <button class="h-20 bg-muted aspect-square rounded-lg flex items-center justify-center cursor-pointer">
            <img src=${imagem.imagem} alt=${data.nome} class="size-14 object-cover pointer-events-none">
        </button>
    `).join("");

    adicionarEventosDeCliqueNasImagens(data.imagens);

    nomeProduto.textContent = data.nome;
    produtoValor.textContent = Intl.NumberFormat("pt-BR", {style: "currency", currency: "BRL"}).format(data.valor);
    descricaoProduto.textContent = data.descricao;

    produtosRecomendados.innerHTML = produtos.map(produto => {
        const imagemPrincipal = produto.imagens.find(imagem => imagem.imagemPrincipal);
        const imagem = imagemPrincipal ? imagemPrincipal.imagem : (produto.imagens.length > 0 ? produto.imagens[0].imagem : "");

        return `
            <a href="/produtos/?produtoId=${produto.id}">
                <div class="w-40 h-56 flex flex-col">
                    <div class="flex items-center justify-center grow bg-muted rounded-md">
                        <img alt=${produto.nome} class="size-24 object-cover" src="${imagem}">
                    </div>
                    <div class="h-2/6 flex flex-col pt-2 space-y-0.5">
                        <span class="text-xs truncate">${produto.nome}</span>
                        <span class="font-semibold">
                            ${Intl.NumberFormat("pt-BR", {style: "currency", currency: "BRL"}).format(produto.valor)}
                        </span>
                    </div>
                </div>
            </a>
        `;
    }).join("");
});
