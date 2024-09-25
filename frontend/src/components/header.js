import { carrinho } from "../lib/carrinho.js";

export function carregarHeader() {

    const carrinhoItens = carrinho.map(item => `
        <div class="w-full flex gap-3 pt-0.5">
            <div class="h-24 w-24 bg-muted rounded-md flex items-center justify-center">
                <img src=${item.imagem} class="size-16 object-cover">
            </div>
            <div class="flex flex-col justify-between">
                <div>
                    <h6 class="text-sm truncate">${item.nome}</h6>
                    <div class="flex items-baseline gap-2">
                        <h4 class="text-lg font-bold">
                            ${Intl.NumberFormat("pt-BR", {style: "currency", currency: "BRL"}).format(item.valor)}
                        </h4>
                        <span class="text-sm text-muted-foreground line-through font-semibold">
                            ${Intl.NumberFormat("pt-BR", {style: "currency", currency: "BRL"}).format(item.valor)}
                        </span>
                    </div>
                </div>
                
                <div class="flex items-center gap-4">
                    <button class="size-9 border rounded-sm flex justify-center items-center bg-background">
                        <svg class="lucide lucide-chevron-left" fill="none" height="18" stroke="currentColor"
                             stroke-linecap="round" stroke-linejoin="round" stroke-width="2" viewBox="0 0 24 24"
                             width="18" xmlns="http://www.w3.org/2000/svg">
                            <path d="m15 18-6-6 6-6"/>
                        </svg>
                    </button>
                    
                    <span class="text-sm">${item.quantidade}</span>
                    
                    <button class="size-9 border rounded-sm flex justify-center items-center bg-background">
                        <svg class="lucide lucide-chevron-right" fill="none" height="18" stroke="currentColor"
                             stroke-linecap="round" stroke-linejoin="round" stroke-width="2" viewBox="0 0 24 24"
                             width="18" xmlns="http://www.w3.org/2000/svg">
                            <path d="m9 18 6-6-6-6"/>
                        </svg>
                    </button>
                </div>
            </div>
        </div>
    `).join("");

    const header = document.createElement("header");
    header.className = "border-b";
    header.innerHTML = `
        <div id="burgerSheet" class="fixed top-0 left-0 w-96 h-full bg-background border transform -translate-x-full transition-transform duration-300 z-50">
            <div class="p-5 flex justify-between items-center border-b">
                <h2 class="text-lg font-semibold">Menu</h2>
                <button id="fecharBurger" class="fill-muted-foreground">
                    <svg class="lucide lucide-x" fill="none" height="20" stroke="currentColor" stroke-linecap="round"
                         stroke-linejoin="round" stroke-width="2" viewBox="0 0 24 24" width="20"
                         xmlns="http://www.w3.org/2000/svg">
                        <line x1="18" x2="6" y1="6" y2="18"/>
                        <line x1="6" x2="18" y1="6" y2="18"/>
                    </svg>
                </button>
            </div>
            <div class="p-5">
                <a href="/login/">
                    <button class="w-full flex items-center gap-2 border rounded-md px-3 py-2 bg-muted">
                        <svg class="lucide lucide-log-out" fill="none" height="20" stroke="currentColor" stroke-linecap="round"
                             stroke-linejoin="round" stroke-width="2" viewBox="0 0 24 24" width="20"
                             xmlns="http://www.w3.org/2000/svg">
                            <path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"/>
                            <polyline points="16 17 21 12 16 7"/>
                            <line x1="21" x2="9" y1="12" y2="12"/>
                        </svg>
                        <span class="font-semibold">Login</span>
                    </button>
                </a>
            </div>
        </div>

        <div class="max-w-screen-xl px-5 mx-auto min-h-20 flex items-center justify-between">
            <button id="burgerButton" class="border rounded-sm p-2 size-10 flex md:hidden items-center justify-center">
                <svg class="lucide lucide-menu" fill="none" height="20" stroke="currentColor" stroke-linecap="round"
                     stroke-linejoin="round" stroke-width="2" viewBox="0 0 24 24" width="20"
                     xmlns="http://www.w3.org/2000/svg">
                    <line x1="4" x2="20" y1="12" y2="12"/>
                    <line x1="4" x2="20" y1="6" y2="6"/>
                    <line x1="4" x2="20" y1="18" y2="18"/>
                </svg>
            </button>
            
            <a href="/">
                <h1 class="text-primary font-bold text-xl">
                    Tech<span class="text-foreground font-semibold">Commerce</span>
                </h1>
            </a>
            
            <div class="hidden md:flex items-baseline space-x-6">
                <a href="/">
                    <button class="font-semibold">
                        Início
                    </button>
                </a>
                <p class="text-muted text-2xl font-semibold">|</p>
                <a href="/categorias/">
                    <button class="font-semibold">
                        Categorias
                    </button>
                </a>                
            </div>
            
            <div class="flex gap-2">
                <a href="/login/">
                    <button class="border rounded-sm p-2 size-10 hidden md:flex items-center justify-center">
                        <svg class="lucide lucide-user-round" fill="none" height="20" stroke="currentColor" stroke-linecap="round"
                             stroke-linejoin="round" stroke-width="2" viewBox="0 0 24 24" width="20"
                             xmlns="http://www.w3.org/2000/svg">
                            <circle cx="12" cy="8" r="5"/>
                            <path d="M20 21a8 8 0 0 0-16 0"/>
                        </svg>
                    </button>
                </a>
                <a href="/carrinho/">
                    <button class="relative border rounded-sm p-2 size-10 flex lg:hidden items-center justify-center">
                        <svg class="lucide lucide-shopping-cart" fill="none" height="20" stroke="currentColor"
                             stroke-linecap="round"
                             stroke-linejoin="round" stroke-width="2" viewBox="0 0 24 24" width="20"
                             xmlns="http://www.w3.org/2000/svg">
                            <circle cx="8" cy="21" r="1"/>
                            <circle cx="19" cy="21" r="1"/>
                            <path d="M2.05 2.05h2l2.66 12.42a2 2 0 0 0 2 1.58h9.78a2 2 0 0 0 1.95-1.57l1.65-7.43H5.12"/>
                        </svg>
                    </button>
                </a>
                <button id="carrinhoButton" class="relative border rounded-sm p-2 size-10 hidden lg:flex items-center justify-center">
                    <svg class="lucide lucide-shopping-cart" fill="none" height="20" stroke="currentColor"
                         stroke-linecap="round"
                         stroke-linejoin="round" stroke-width="2" viewBox="0 0 24 24" width="20"
                         xmlns="http://www.w3.org/2000/svg">
                        <circle cx="8" cy="21" r="1"/>
                        <circle cx="19" cy="21" r="1"/>
                        <path d="M2.05 2.05h2l2.66 12.42a2 2 0 0 0 2 1.58h9.78a2 2 0 0 0 1.95-1.57l1.65-7.43H5.12"/>
                        <div class="w-5 h-5 absolute -bottom-2 -left-2 bg-primary rounded-full flex items-center justify-center">
                            <span class="text-xs font-semibold" name="contadorCarrinho">
                                ${carrinho.length}
                            </span>
                        </div>
                    </svg>
                </button>
            </div>
        </div>
        
        <div id="carrinhoSheet" class="fixed top-0 right-0 w-96 flex flex-col h-full bg-background border transform translate-x-full transition-transform duration-300 z-50">
            <div class="p-5 flex justify-between items-center">
                <div class="flex items-center w-min border-2 rounded-full border-primary py-1 px-4 space-x-1.5">
                    <svg class="lucide lucide-shopping-cart" fill="none" height="14" stroke="currentColor"
                         stroke-linecap="round"
                         stroke-linejoin="round" stroke-width="2" viewBox="0 0 24 24" width="14"
                         xmlns="http://www.w3.org/2000/svg">
                        <circle cx="8" cy="21" r="1"/> 
                        <circle cx="19" cy="21" r="1"/>
                        <path d="M2.05 2.05h2l2.66 12.42a2 2 0 0 0 2 1.58h9.78a2 2 0 0 0 1.95-1.57l1.65-7.43H5.12"/>
                    </svg>
                    <span class="font-semibold text-sm">CARRINHO</span>
                </div>
                <button id="fecharCarrinho" class="fill-muted-foreground">
                    <svg class="lucide lucide-x" fill="none" height="20" stroke="currentColor" stroke-linecap="round"
                         stroke-linejoin="round" stroke-width="2" viewBox="0 0 24 24" width="20"
                         xmlns="http://www.w3.org/2000/svg">
                        <line x1="18" x2="6" y1="6" y2="18"/>
                        <line x1="6" x2="18" y1="6" y2="18"/>
                    </svg>
                </button>
            </div>
            ${carrinho.length === 0 ? `
                <div class="grow flex justify-center items-center">
                    <span class="font-semibold">Seu carrinho está vazio</span>
                </div>
            ` : `
                <div class="grow flex flex-col justify-between px-5 pb-4">
                    <section class="h-2/6 flex flex-col gap-2">
                        ${carrinhoItens}
                    </section>
                    <section class="grow flex flex-col justify-between">
                        <div class="py-4 text-muted-foreground text-sm font-semibold grid gap-3">
                            <div class="h-0.5 bg-muted"></div>
                            <span class="flex justify-between">
                                <span>Subtotal</span>
                                <span>
                                    ${Intl.NumberFormat("pt-BR", {style: "currency", currency: "BRL"})
        .format(carrinho.reduce((acc, item) => (acc + item.valor), 0))}
                                </span>
                            </span>
                            <div class="h-0.5 bg-muted"></div>
                            <span class="flex justify-between">
                                <span>Entrega</span>
                                <span>
                                    GRÁTIS
                                </span>
                            </span>
                            <div class="h-0.5 bg-muted"></div>
                            <span class="flex justify-between">
                                <span>Descontos</span>
                                <span>
                                    - ${Intl.NumberFormat("pt-BR", {style: "currency", currency: "BRL"})
        .format(carrinho.reduce((acc, item) => (acc + item.produtoDesconto), 0))}
                                </span>
                            </span>
                            <div class="h-0.5 bg-muted"></div>
                            <span class="flex justify-between text-lg text-white">
                                <span>TOTAL</span>
                                <span>
                                    ${Intl.NumberFormat("pt-BR", {style: "currency", currency: "BRL"})
        .format(carrinho.reduce((acc, item) => (acc + item.valor), 0))}
                                </span>
                            </span>
                        </div>
                        <button class="w-full h-10 rounded-md font-semibold bg-primary">FINALIZAR COMPRA</button>
                    </section>
                </div>
            `}
        </div>
        
        <div id="overlay" class="fixed inset-0 bg-black bg-opacity-50 hidden z-40"></div>
    `;
    document.body.insertAdjacentElement("afterbegin", header);

    const cartButton = document.getElementById("carrinhoButton");
    const cartModal = document.getElementById("carrinhoSheet");
    const closeCartButton = document.getElementById("fecharCarrinho");
    const overlay = document.getElementById("overlay");

    const burgerButton = document.getElementById("burgerButton");
    const burgerModal = document.getElementById("burgerSheet");
    const closeBurgerButton = document.getElementById("fecharBurger");

    function abrirCarrinho() {
        cartModal.classList.remove("translate-x-full");
        cartModal.classList.add("translate-x-0");
        overlay.classList.remove("hidden");
        document.body.classList.add("overflow-hidden");
    }

    function fecharCarrinho() {
        cartModal.classList.remove("translate-x-0");
        cartModal.classList.add("translate-x-full");
        overlay.classList.add("hidden");
        document.body.classList.remove("overflow-hidden");
    }

    function abrirBurger() {
        burgerModal.classList.remove("-translate-x-full");
        burgerModal.classList.add("translate-x-0");
        overlay.classList.remove("hidden");
        document.body.classList.add("overflow-hidden");
    }

    function fecharBurger() {
        burgerModal.classList.remove("translate-x-0");
        burgerModal.classList.add("-translate-x-full");
        overlay.classList.add("hidden");
        document.body.classList.remove("overflow-hidden");
    }

    cartButton.addEventListener("click", abrirCarrinho);
    closeCartButton.addEventListener("click", fecharCarrinho);

    burgerButton.addEventListener("click", abrirBurger);
    closeBurgerButton.addEventListener("click", fecharBurger);

    overlay.addEventListener("click", () => {
        fecharCarrinho();
        fecharBurger();
    });
}
