export function carregarFooter() {
    const footer = document.createElement("footer");
    footer.className = "min-h-20 flex items-center bg-muted px-8";
    footer.innerHTML = `
        <span class="text-muted-foreground text-sm">© 2024 Copyright TechCommerce</span>
    `;
    document.body.appendChild(footer);
}
