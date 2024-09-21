import { formatarNumero } from "../lib/formatarNumero.js";

export function produtosRecomendados(section, data) {
    section.innerHTML = data.map(produto => {
        const imagemPrincipal = produto.imagens.find(imagem => imagem.imagemPrincipal);
        const imagem = imagemPrincipal ? imagemPrincipal.imagem : (produto.imagens.length > 0 ? produto.imagens[0].imagem : "");

        let estrelas = "";
        for (let i = 0; i < 4; i++) {
            estrelas += `
                <svg class="lucide lucide-star fill-primary text-primary" fill="none" height="15" stroke="currentColor" stroke-linecap="round"
                     stroke-linejoin="round" stroke-width="2" viewBox="0 0 24 24" width="15"
                     xmlns="http://www.w3.org/2000/svg"
                    >
                    <polygon points="12  2 15.09 8.26 22 9.27 17 14.14 18.18 21.02 12 17.77 5.82 21.02 7 14.14 2 9.27 8.91 8.26 12 2"/>
                </svg>
            `;
        }

        return `
            <a href="/produtos/?produtoId=${produto.id}" class="inline-block">
                <div class="w-40 lg:w-44 h-52 lg:h-60 flex flex-col">
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
                        <img alt=${produto.nome} src="${imagem}" class="size-24 lg:size-28 object-cover">
                    </div>
                    <div class="h-2/6 flex flex-col pt-2 space-y-0.5">
                        <span class="text-xs lg:text-sm truncate">${produto.nome}</span>
                        <div class="flex items-baseline gap-1">
                            <span class="font-semibold lg:text-lg">
                                ${formatarNumero(produto.valor)}
                            </span>
                            <span class="line-through text-xs lg:text-sm text-muted-foreground">
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
                            <span class="text-xs lg:text-sm text-muted-foreground ml-1">(25)</span>
                        </div>  
                    </div>
                </div>
            </a>
        `;
    }).join("");
}