import { api } from "../lib/api.js";
import { formatarNumero } from "../lib/formatarNumero.js";

let imagemSelecionada;
let buttonSelecionado;
const imagemPrincipal = document.getElementById("imagemPrincipal");
const divImagens = document.getElementById("imagens");
const nomeProduto = document.getElementById("nomeProduto");
const produtoValor = document.getElementById("produtoValor");
const descricaoProduto = document.getElementById("descricaoProduto");
const produtosRecomendados = document.getElementById("produtosRecomendados");

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

    produtosRecomendados.innerHTML = produtos.map(produto => {
        const imagemPrincipal = produto.imagens.find(imagem => imagem.imagemPrincipal);
        const imagem = imagemPrincipal ? imagemPrincipal.imagem : (produto.imagens.length > 0 ? produto.imagens[0].imagem : "");

        let estrelas = "";
        for (let i = 0; i < 4; i++) {
            estrelas += `
                <svg class="lucide lucide-star fill-primary text-primary" fill="none" height="15" stroke="currentColor" stroke-linecap="round"
                     stroke-linejoin="round" stroke-width="2" viewBox="0 0 24 24" width="15"
                     xmlns="http://www.w3.org/2000/svg"
                    >
                    <polygon points="12 2 15.09 8.26 22 9.27 17 14.14 18.18 21.02 12 17.77 5.82 21.02 7 14.14 2 9.27 8.91 8.26 12 2"/>
                </svg>
            `;
        }

        return `
            <a href="/produtos/?produtoId=${produto.id}">
                <div class="w-40 h-56 flex flex-col">
                     <div class="relative flex items-center justify-center grow bg-muted rounded-md">
                        <div class="absolute top-1.5  left-1.5  flex items-center rounded-2xl bg-primary px-2 py-1.5 gap-1">
                            <svg width="12" height="14" viewBox="0 0 8 10" fill="none" xmlns="http://www.w3.org/2000/svg">
                                <g clip-path="url(#clip0_1_432)">
                                <path d="M1.40002 6.05005L4.20002 8.85005L7.00002 6.05005" stroke="white" stroke-width="1.4" stroke-linecap="round" stroke-linejoin="round"/>
                                <path d="M4.19995 1.14996L4.19995 8.84996" stroke="white" stroke-width="1.4" stroke-linecap="round" stroke-linejoin="round"/>
                                </g>
                                <defs>
                                <clipPath id="clip0_1_432">
                                <rect width="7" height="9.1" fill="white" transform="translate(0.5 0.450012)"/>
                                </clipPath>
                                </defs>
                            </svg>
                            <span class="text-xs font-semibold">55%</span>
                        </div>
                        <img alt=${produto.nome} src="${imagem}" class="size-24 object-cover">
                    </div>
                    <div class="h-2/6 flex flex-col pt-2 space-y-0.5">
                        <span class="text-xs truncate">${produto.nome}</span>
                        <div class="flex items-baseline gap-1">
                            <span class="font-semibold">
                                ${formatarNumero(produto.valor)}
                            </span>
                            <span class="line-through text-xs text-muted-foreground">
                                ${formatarNumero(produto.valor)}
                            </span>
                        </div>
                        <div class="flex items-center gap-0.5">
                            ${estrelas}
                            <svg class="lucide lucide-star text-primary" fill="none" height="15" stroke="currentColor" stroke-linecap="round"
                                 stroke-linejoin="round" stroke-width="2" viewBox="0 0 24 24" width="15"
                                 xmlns="http://www.w3.org/2000/svg"
                                >
                                <polygon points="12 2 15.09 8.26 22 9.27 17 14.14 18.18 21.02 12 17.77 5.82 21.02 7 14.14 2 9.27 8.91 8.26 12 2"/>
                            </svg>
                            <span class="text-xs ml-1">(25)</span>
                        </div>  
                    </div>
                </div>
            </a>
        `;
    }).join("");
});
