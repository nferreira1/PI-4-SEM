export let carrinho = JSON.parse(localStorage.getItem("carrinho")) || [];

window.onload = () => {
    const carrinhoSalvo = localStorage.getItem("carrinho");
    if (carrinhoSalvo) {
        carrinho = JSON.parse(carrinhoSalvo);
        renderizarCarrinho();
    }
};

function atualizarContadorCarrinho() {
    let contadoresCarrinho = document.getElementsByName("contadorCarrinho");
    if (contadoresCarrinho) {
        contadoresCarrinho.forEach(contador => {
            contador.textContent = carrinho.length;
        });
    }
}

export function adicionarAoCarrinho(produtoId, produtoNome, produtoValor, produtoDesconto, produtoImagem, quantidade) {
    const itemExistente = carrinho.find(item => item.id === produtoId);

    if (itemExistente) {
        itemExistente.quantidade = quantidade;
    } else {
        carrinho.push({
            id: produtoId,
            nome: produtoNome,
            imagem: produtoImagem,
            valor: parseFloat(produtoValor),
            produtoDesconto: produtoDesconto,
            quantidade
        });
    }

    localStorage.setItem("carrinho", JSON.stringify(carrinho));
    renderizarCarrinho();
    atualizarContadorCarrinho();
}

export function removerDoCarrinho(produtoId) {
    carrinho = carrinho.filter(item => item.id !== produtoId);
    localStorage.setItem("carrinho", JSON.stringify(carrinho));
    renderizarCarrinho();
    atualizarContadorCarrinho();
}

export function renderizarCarrinho() {
    console.log(carrinho);
}