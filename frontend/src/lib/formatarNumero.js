export function formatarNumero(numero) {
    return Intl.NumberFormat("pt-BR", {style: "currency", currency: "BRL"}).format(numero);
}