export function carregarHeader() {
    const header = document.createElement("header");
    header.className = "min-h-20 flex items-center justify-between border-b px-8";
    header.innerHTML = `
        <button class="border rounded-sm p-2 size-10 flex items-center justify-center">
            <svg class="lucide lucide-menu" fill="none" height="20" stroke="currentColor" stroke-linecap="round"
                 stroke-linejoin="round" stroke-width="2" viewBox="0 0 24 24" width="20"
                 xmlns="http://www.w3.org/2000/svg">
                <line x1="4" x2="20" y1="12" y2="12"/>
                <line x1="4" x2="20" y1="6" y2="6"/>
                <line x1="4" x2="20" y1="18" y2="18"/>
            </svg>
        </button>
        
        <img alt="" src="">
        
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
    `;
    document.body.insertAdjacentElement("afterbegin", header);
}
