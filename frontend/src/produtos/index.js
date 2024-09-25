import { produtos as produtosFunction } from "../components/produtos.js";
import { api } from "../lib/api.js";
import { adicionarAoCarrinho } from "../lib/carrinho.js";

let imagemSelecionada;
let buttonSelecionado;
let quantidade = 1;
const imagemPrincipal = document.getElementById("imagemPrincipal");
const divImagensLista = document.querySelectorAll("[data-name='imagens']");
const divEstrelas = document.getElementById("estrelas");
const nomeProduto = document.getElementById("nomeProduto");
const produtoValor = document.getElementById("produtoValor");
const produtoQuantidade = document.getElementById("produtoQuantidade");
const produtoDiminuirQuantidade = document.getElementById("produtoDiminuirQuantidade");
const produtoAumentarQuantidade = document.getElementById("produtoAumentarQuantidade");
const descricaoProduto = document.getElementById("descricaoProduto");
const buttonAdicionarAoCarrinho = document.getElementById("buttonAdicionarAoCarrinho");
const produtosRecomendadosSection = document.getElementById("produtosRecomendadosSection");

const handleSelecionarImagemProduto = (imagem, button) => {
    if (buttonSelecionado && buttonSelecionado !== button) {
        buttonSelecionado.classList.remove("border", "border-primary");
    }

    imagemSelecionada = imagem.id;
    imagemPrincipal.src = imagem.imagem;

    button.classList.add("border", "border-primary");

    buttonSelecionado = button;
};

const renderizarProdutoQuantidade = () => {
    produtoQuantidade.innerText = quantidade;
};

const handleAumentarQuantidadeProduto = () => {
    quantidade++;
    renderizarProdutoQuantidade();
};

const handleDiminuirQuantidadeProduto = () => {
    if (quantidade > 1) {
        quantidade--;
        renderizarProdutoQuantidade();
    }
};

const adicionarEventosDeCliqueNasImagens = (imagens) => {
    divImagensLista.forEach(div => {
        const buttons = div.querySelectorAll("button");

        buttons.forEach((button, index) => {
            button.addEventListener("click", () => {
                handleSelecionarImagemProduto(imagens[index], button);
            });

            if (imagens[index].id === imagemSelecionada) {
                button.classList.add("border", "border-primary");
                buttonSelecionado = button;
            }
        });
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
        return;
    }

    imagemSelecionada = produto.imagens.find(imagem => imagem.imagemPrincipal)?.id;
    imagemPrincipal.src = produto.imagens.find(imagem => imagem.imagemPrincipal)?.imagem || "";
    imagemPrincipal.alt = produto.nome;

    divImagensLista.forEach(div => {
        div.innerHTML = produto.imagens.map(imagem => `
            <button class="h-20 bg-muted md:bg-background aspect-square rounded-lg flex items-center justify-center cursor-pointer">
                <img src=${imagem.imagem} alt=${produto.nome} class="size-14 object-cover pointer-events-none">
            </button>
        `).join("");
    });

    let estrelas = "";
    for (let i = 0; i < 4; i++) {
        estrelas += `
            <svg class="lucide lucide-star fill-primary text-primary" fill="none" height="15" stroke="currentColor" stroke-linecap="round"
                 stroke-linejoin="round" stroke-width="2" viewBox="0 0 24 24" width="15"
                 xmlns="http://www.w3.org/2000/svg">
                <polygon points="12  2 15.09 8.26 22 9.27 17 14.14 18.18 21.02 12 17.77 5.82 21.02 7 14.14 2 9.27 8.91 8.26 12 2"/>
            </svg>
        `;
    }
    divEstrelas.insertAdjacentHTML("afterbegin", estrelas);

    adicionarEventosDeCliqueNasImagens(produto.imagens);

    nomeProduto.textContent = produto.nome;
    produtoValor.innerHTML = `
        <h6 class="text-2xl font-bold">
            ${Intl.NumberFormat("pt-BR", {style: "currency", currency: "BRL"}).format(produto.valor)}
        </h6>
        <span class="text-muted-foreground text-sm">
            De: 
            <span class="line-through">
                ${Intl.NumberFormat("pt-BR", {style: "currency", currency: "BRL"}).format(produto.valor)}
            </span>
        </span>
    `;
    produtoDiminuirQuantidade.addEventListener("click", handleDiminuirQuantidadeProduto);
    produtoAumentarQuantidade.addEventListener("click", handleAumentarQuantidadeProduto);

    descricaoProduto.textContent = produto.descricao;

    produtosFunction(produtosRecomendadosSection, produtos);
    renderizarProdutoQuantidade();
    buttonAdicionarAoCarrinho.addEventListener("click", () => {
        const nome = nomeProduto.textContent;
        const valor = parseFloat(produtoValor.querySelector("h6").textContent.replace(/[^\d,-]/g, "").replace(",", "."));
        adicionarAoCarrinho(produtoId, nome, valor, valor, imagemPrincipal.src, quantidade);
    });
});
