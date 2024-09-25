export function carregarHeader() {
    const header = document.createElement("header");
    header.className = "border-b";
    header.innerHTML = `
        <div id="burger-sheet" class="fixed top-0 left-0 w-96 h-full bg-background border transform -translate-x-full transition-transform duration-300 z-50">
            <div class="p-5 flex justify-between items-center border-b">
                <h2 class="text-lg font-semibold">Menu</h2>
                <button id="fechar-burger" class="fill-muted-foreground">
                    <svg class="lucide lucide-x" fill="none" height="20" stroke="currentColor" stroke-linecap="round"
                         stroke-linejoin="round" stroke-width="2" viewBox="0 0 24 24" width="20"
                         xmlns="http://www.w3.org/2000/svg">
                        <line x1="18" x2="6" y1="6" y2="18"/>
                        <line x1="6" x2="18" y1="6" y2="18"/>
                    </svg>
                </button>
            </div>
            <div class="p-5">
                <p>Seu menu está aqui.</p>
                <ul>
                    <li><a href="/" class="block py-2">Início</a></li>
                    <li><a href="/categorias/" class="block py-2">Categorias</a></li>
                </ul>
            </div>
        </div>

        <div class="max-w-screen-xl px-5 mx-auto min-h-20 flex items-center justify-between">
            <button id="burger-button" class="border rounded-sm p-2 size-10 flex md:hidden items-center justify-center">
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
                <button class="border rounded-sm p-2 size-10 hidden md:flex items-center justify-center">
                    <svg class="lucide lucide-user-round" fill="none" height="20" stroke="currentColor" stroke-linecap="round"
                         stroke-linejoin="round" stroke-width="2" viewBox="0 0 24 24" width="20"
                         xmlns="http://www.w3.org/2000/svg">
                        <circle cx="12" cy="8" r="5"/>
                        <path d="M20 21a8 8 0 0 0-16 0"/>
                    </svg>
                </button>
                <a href="/carrinho/">
                    <button class="border rounded-sm p-2 size-10 flex lg:hidden items-center justify-center">
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
                <button id="carrinho-button" class="border rounded-sm p-2 size-10 hidden lg:flex items-center justify-center">
                    <svg class="lucide lucide-shopping-cart" fill="none" height="20" stroke="currentColor"
                         stroke-linecap="round"
                         stroke-linejoin="round" stroke-width="2" viewBox="0 0 24 24" width="20"
                         xmlns="http://www.w3.org/2000/svg">
                        <circle cx="8" cy="21" r="1"/>
                        <circle cx="19" cy="21" r="1"/>
                        <path d="M2.05 2.05h2l2.66 12.42a2 2 0 0 0 2 1.58h9.78a2 2 0 0 0 1.95-1.57l1.65-7.43H5.12"/>
                    </svg>
                </button>
            </div>
        </div>
        
        <div id="carrinho-sheet" class="fixed top-0 right-0 w-96 flex flex-col h-full bg-background border transform translate-x-full transition-transform duration-300 z-50">
            <div class="p-5 flex justify-between items-center border-b">
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
                <button id="fechar-carrinho" class="fill-muted-foreground">
                    <svg class="lucide lucide-x" fill="none" height="20" stroke="currentColor" stroke-linecap="round"
                         stroke-linejoin="round" stroke-width="2" viewBox="0 0 24 24" width="20"
                         xmlns="http://www.w3.org/2000/svg">
                        <line x1="18" x2="6" y1="6" y2="18"/>
                        <line x1="6" x2="18" y1="6" y2="18"/>
                    </svg>
                </button>
            </div>
            <div class="grow p-5">
            
            </div>
        </div>
        
        <div id="overlay" class="fixed inset-0 bg-black bg-opacity-50 hidden z-40"></div>
    `;
    document.body.insertAdjacentElement("afterbegin", header);

    const cartButton = document.getElementById("carrinho-button");
    const cartModal = document.getElementById("carrinho-sheet");
    const closeCartButton = document.getElementById("fechar-carrinho");
    const overlay = document.getElementById("overlay");

    const burgerButton = document.getElementById("burger-button");
    const burgerModal = document.getElementById("burger-sheet");
    const closeBurgerButton = document.getElementById("fechar-burger");

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
