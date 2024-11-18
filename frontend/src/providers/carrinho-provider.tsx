import { useLocalStorage } from "@/hooks/useLocalStorage";
import { createContext, useEffect, useState } from "react";
import { toast } from "sonner";

export const CarrinhoContext = createContext<
	Readonly<{
		carrinho: Schema["CarrinhoDTO"]["itens"];
		add: ({
			produto,
			quantidade,
		}: {
			produto: Schema["ProdutoDTO"];
			quantidade: number;
		}) => void;
		update: ({
			produto,
			quantidade,
		}: {
			produto: Schema["ProdutoDTO"];
			quantidade: number;
		}) => void;
		remove: (produto: Schema["ProdutoDTO"]) => void;
		removeAll: () => void;
		inTheCart: (produto: Schema["ProdutoDTO"]) => boolean;
	}>
>({
	carrinho: [],
	add: () => {},
	update: () => {},
	remove: () => {},
	removeAll: () => {},
	inTheCart: () => false,
});

export const CarrinhoProvider = ({
	children,
}: Readonly<{ children: React.ReactNode }>) => {
	const [value, setValue, removeValue] = useLocalStorage<
		Schema["CarrinhoDTO"]["itens"]
	>("carrinho", [], {});
	const [carrinho, setCarrinho] = useState<Schema["CarrinhoDTO"]["itens"]>(
		[],
	);

	const add = ({
		produto,
		quantidade,
	}: {
		produto: Schema["ProdutoDTO"];
		quantidade: number;
	}) => {
		const item = carrinho!.find((item) => item.produto?.id === produto?.id);

		if (item) {
			const updatedCarrinho = carrinho!.map((item) =>
				item.produto?.id === produto?.id
					? { ...item, quantidade: item.quantidade! + quantidade }
					: item,
			);

			setCarrinho(updatedCarrinho);
			setValue(updatedCarrinho);
		} else {
			const newItem = {
				produto: { ...produto },
				quantidade,
			};

			const updatedCarrinho = [...carrinho!, newItem];
			setCarrinho(updatedCarrinho);
			setValue(updatedCarrinho);
		}
	};

	const update = ({
		produto,
		quantidade,
	}: {
		produto: Schema["ProdutoDTO"];
		quantidade: number;
	}) => {
		const item = carrinho!.findIndex(
			(item) => item.produto?.id === produto?.id,
		);

		if (item !== -1) {
			if (quantidade > produto.estoque!) {
				toast.warning("Atenção!", {
					description: `A quantidade máxima disponível em estoque é de ${produto.estoque} unidades.`,
					position: "bottom-left",
				});
				return;
			}

			if (quantidade === 0) {
				remove(produto);
				return;
			}

			const updatedCarrinho = [...carrinho!];
			updatedCarrinho[item] = {
				produto: { ...produto },
				quantidade,
			};

			setCarrinho(updatedCarrinho);
			setValue(updatedCarrinho);
		}
	};

	const remove = (produto: Schema["ProdutoDTO"]) => {
		const updatedCarrinho = carrinho!.filter(
			(item) => item.produto?.id !== produto?.id,
		);

		setCarrinho(updatedCarrinho);
		setValue(updatedCarrinho);
	};

	const removeAll = () => {
		setCarrinho([]);
		setValue([]);
	};

	const inTheCart = (produto: Schema["ProdutoDTO"]) => {
		return carrinho!.some((item) => item.produto?.id === produto?.id);
	};

	useEffect(() => {
		if (value) {
			setCarrinho(value);
		} else {
			removeValue();
		}
	}, [value, removeValue]);

	return (
		<CarrinhoContext.Provider
			value={{ carrinho, add, update, remove, removeAll, inTheCart }}
		>
			{children}
		</CarrinhoContext.Provider>
	);
};
