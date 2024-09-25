let carrinho = [];

window.onload = () => {
    const carrinhoSalvo = localStorage.getItem("carrinho");
    if (carrinhoSalvo) {
        carrinho = JSON.parse(carrinhoSalvo);
        renderizarCarrinho();
    }
};

export function adicionarAoCarrinho(produtoId, produtoNome, produtoPreco, produtoImagem) {
    const itemExistente = carrinho.find(item => item.id === produtoId);

    if (itemExistente) {
        itemExistente.quantidade += 1;
    } else {
        carrinho.push({
            id: produtoId,
            name: produtoNome,
            preco: parseFloat(produtoPreco),
            quantidade: 1
        });
    }

    localStorage.setItem("carrinho", JSON.stringify(carrinho));
    renderizarCarrinho();
}

export function removerDoCarrinho(produtoId) {
    carrinho = carrinho.filter(item => item.id !== produtoId);
    localStorage.setItem("carrinho", JSON.stringify(carrinho));
    renderizarCarrinho();
}

export function renderizarCarrinho() {


}