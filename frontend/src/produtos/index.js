import { produtosRecomendados } from "../components/produtosRecomendados.js";
import { api } from "../lib/api.js";

let imagemSelecionada;
let buttonSelecionado;
const imagemPrincipal = document.getElementById("imagemPrincipal");
const divImagens = document.getElementById("imagens");
const nomeProduto = document.getElementById("nomeProduto");
const produtoValor = document.getElementById("produtoValor");
const descricaoProduto = document.getElementById("descricaoProduto");
const produtosRecomendadosSection = document.getElementById("produtosRecomendadosSection");

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

document.addEventListener("DOMContentLoaded", async (e) => {
    const params = new URLSearchParams(window.location.search);
    const produtoId = params.get("produtoId");

    const [
        {data: produto, error: errorProduto},
        {data: produtos, error: errorProdutos}
    ] = await Promise.all([
        await api.GET(`/produto/${produtoId}`),
        await api.GET(`/produto`)
    ]);

    if (errorProduto) {
        window.history.back();
    }

    imagemSelecionada = produto.imagens.find(imagem => imagem.imagemPrincipal)?.id;
    imagemPrincipal.src = produto.imagens.find(imagem => imagem.imagemPrincipal)?.imagem || "";
    imagemPrincipal.alt = produto.nome;

    divImagens.innerHTML = produto.imagens.map(imagem => `
        <button class="h-20 bg-muted aspect-square rounded-lg flex items-center justify-center cursor-pointer">
            <img src=${imagem.imagem} alt=${produto.nome} class="size-14 object-cover pointer-events-none">
        </button>
    `).join("");

    adicionarEventosDeCliqueNasImagens(produto.imagens);

    nomeProduto.textContent = produto.nome;
    produtoValor.textContent = Intl.NumberFormat("pt-BR", {style: "currency", currency: "BRL"}).format(produto.valor);
    descricaoProduto.textContent = produto.descricao;

    produtosRecomendados(produtosRecomendadosSection, produtos);
});
