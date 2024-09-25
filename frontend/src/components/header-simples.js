export function carregarHeaderSimples() {
    const header = document.createElement("header");
    header.className = "border-b";
    header.innerHTML = `
        <div class="max-w-screen-xl px-5 mx-auto min-h-20 flex items-center justify-between">
            <a href="/">
                <button class="flex items-center gap-2">
                    <svg class="lucide lucide-arrow-left" fill="none" height="18" stroke="currentColor"
                         stroke-linecap="round"
                         stroke-linejoin="round" stroke-width="2" viewBox="0 0 24 24" width="18"
                         xmlns="http://www.w3.org/2000/svg">
                        <path d="m12 19-7-7 7-7"/>
                        <path d="M19 12H5"/>
                    </svg>
                    <span class="font-semibold">Voltar</span>
                </button>
            </a>
            
            <a href="/">
                <h1 class="text-primary font-bold text-xl">
                    Tech<span class="text-foreground font-semibold">Commerce</span>
                </h1>
            </a>
        </div>
    `;
    document.body.insertAdjacentElement("afterbegin", header);
}