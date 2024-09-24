export function carregarFooter() {
    const footer = document.createElement("footer");
    footer.className = "bg-muted";
    footer.innerHTML = `
        <div class="max-w-screen-xl mx-auto px-5 min-h-20 flex items-center">
            <span class="text-muted-foreground text-sm">© 2024 Copyright TechCommerce</span>
        </div>
    `;
    document.body.appendChild(footer);
}
