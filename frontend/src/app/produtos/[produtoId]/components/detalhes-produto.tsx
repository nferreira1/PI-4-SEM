"use client";

import { Button } from "@/components/ui/button";
import { $api } from "@/lib/api";
import { formatNumber } from "@/lib/utils";
import { ChevronLeft, ChevronRight, Loader2, Star, Truck } from "lucide-react";
import { useState } from "react";

export function DetalhesProdutos({
	produto,
}: Readonly<{
	produto: Schema["ProdutoDTO"] | undefined;
}>) {
	const [quantidade, setQuantidade] = useState<number>(1);

	const handleQuantidade = (value: number) => {
		if (value < 1 || value > (produto?.estoque ?? 0)) return;
		setQuantidade(value);
	};

	const handleAdicionarAoCarrinho = () =>
		mutate({
			body: {
				produtoId: produto?.id,
				quantidade,
			},
		});

	const { mutate, isPending } = $api.useMutation("post", "/carrinho");

	return (
		<section className="grid grow gap-4 px-5 py-8 md:col-span-1 md:rounded-lg md:bg-muted lg:col-span-1">
			<div>
				<span className="text-xs text-muted-foreground">
					Novo | 100 vendidos
				</span>
				<h4 className="text-lg font-semibold">{produto?.nome}</h4>
				<span className="text-xs text-primary">
					Disponível em estoque
				</span>
				<div className="flex items-center gap-1 pt-0.5">
					{Array.from({ length: 5 }).map((_, index) => (
						<Star
							key={index}
							size={15}
							className="fill-primary text-primary data-[last=true]:fill-transparent"
						/>
					))}
					<span className="ml-1 text-sm text-muted-foreground">
						(25 avaliações)
					</span>
				</div>
			</div>

			<div>
				<h6 className="text-2xl font-bold">
					{formatNumber(produto?.valor as number)}
				</h6>
				<span className="text-sm text-muted-foreground">
					De:
					<span className="line-through">
						{" "}
						{formatNumber(produto?.valor as number)}
					</span>
				</span>
			</div>

			<div className="flex items-center gap-4">
				<Button
					size="icon"
					className="transition-transform hover:scale-110"
					disabled={isPending || quantidade === 1}
					onClick={() => handleQuantidade(quantidade - 1)}
				>
					<ChevronLeft size={16} />
				</Button>

				<span className="text-sm">{quantidade}</span>

				<Button
					size="icon"
					className="transition-transform hover:scale-110"
					disabled={isPending || produto?.estoque === quantidade}
					onClick={() => handleQuantidade(quantidade + 1)}
				>
					<ChevronRight size={16} />
				</Button>
			</div>

			<div className="py-6">
				<h6 className="pb-2 font-semibold">Descrição</h6>
				<p className="text-sm text-muted-foreground">
					{produto?.descricao}
				</p>
			</div>

			<Button
				size="lg"
				disabled={isPending}
				onClick={handleAdicionarAoCarrinho}
			>
				{isPending ? (
					<Loader2 size={22} className="animate-spin" />
				) : (
					"ADICIONAR AO CARRINHO"
				)}
			</Button>

			<div className="flex items-center gap-x-6 rounded-xl bg-muted px-6 py-4 md:bg-accent">
				<Truck size={30} />

				<div className="grid text-sm">
					<span className="truncate">
						Entrega via
						<span className="font-semibold italic"> Sedex</span>
					</span>
					<span className="truncate text-primary">
						Envio para todo o Brasil
					</span>
				</div>

				<span className="ml-auto truncate text-xs font-semibold">
					Frete grátis
				</span>
			</div>
		</section>
	);
}
