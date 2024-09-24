export function carregarHeader() {
    const header = document.createElement("header");
    header.className = "border-b";
    header.innerHTML = `
        <div class="max-w-screen-xl px-5 mx-auto min-h-20 flex items-center justify-between">
            <button class="border rounded-sm p-2 size-10 flex md:hidden items-center justify-center">
                <svg class="lucide lucide-menu" fill="none" height="20" stroke="currentColor" stroke-linecap="round"
                     stroke-linejoin="round" stroke-width="2" viewBox="0 0 24 24" width="20"
                     xmlns="http://www.w3.org/2000/svg">
                    <line x1="4" x2="20" y1="12" y2="12"/>
                    <line x1="4" x2="20" y1="6" y2="6"/>
                    <line x1="4" x2="20" y1="18" y2="18"/>
                </svg>
            </button>
            
            <h1 class="text-primary font-bold text-xl">
                Tech<span class="text-foreground font-semibold">Commerce</span>
            </h1>
            
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
            
            <div class="flex gap-4">
                <button class="border rounded-sm p-2 size-10 hidden md:flex items-center justify-center">
                    <svg class="lucide lucide-user-round" fill="none" height="20" stroke="currentColor" stroke-linecap="round"
                         stroke-linejoin="round" stroke-width="2" viewBox="0 0 24 24" width="20"
                         xmlns="http://www.w3.org/2000/svg">
                        <circle cx="12" cy="8" r="5"/>
                        <path d="M20 21a8 8 0 0 0-16 0"/>
                    </svg>
                </button>
                <button class="border rounded-sm p-2 size-10 flex items-center justify-center">
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
    `;
    document.body.insertAdjacentElement("afterbegin", header);
}
