import { CarrinhoContext } from "@/providers/carrinho-provider";
import { useContext } from "react";

export const useCarrinho = () => {
	const context = useContext(CarrinhoContext);
	if (context === undefined) {
		throw new Error("useCarrinho must be used within a AuthProvider");
	}
	return context;
};
