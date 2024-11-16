"use client";

import { Button } from "@/components/ui/button";
import { Separator } from "@/components/ui/separator";
import {
	Sheet,
	SheetContent,
	SheetHeader,
	SheetTitle,
	SheetTrigger,
} from "@/components/ui/sheet";
import { api } from "@/lib/api";
import { formatNumber } from "@/lib/utils";
import { ChevronLeft, ChevronRight, ShoppingCart } from "lucide-react";
import Image from "next/image";
import { useState } from "react";

export function SheetCarrinho({
	carrinho: initialCarrinho,
}: Readonly<{
	carrinho: Schema["CarrinhoDTO"] | undefined;
}>) {
	const [produtos, setProdutos] = useState<
		Schema["CarrinhoProdutosResponseDTO"][] | undefined
	>(initialCarrinho?.itens);

	const handleUpdateProduct = async (
		produtoId: number,
		quantidade: number,
	) => {
		const { data } = await api.PATCH("/carrinho", {
			body: {
				produtoId,
				quantidade,
			},
		});

		setProdutos(data?.itens);
	};

	return (
		<Sheet>
			<SheetTrigger asChild>
				<Button
					variant="outline"
					size="icon"
					className="relative hidden lg:flex"
				>
					<ShoppingCart size={20} />
					<div className="absolute -bottom-2 -left-2 flex h-5 w-5 items-center justify-center rounded-full bg-primary">
						<span className="text-xs font-semibold">
							{produtos?.length}
						</span>
					</div>
				</Button>
			</SheetTrigger>
			<SheetContent side="right" className="flex flex-col gap-0">
				<SheetHeader className="border-b">
					<SheetTitle>Carrinho</SheetTitle>
				</SheetHeader>
				<div className="flex grow flex-col justify-between px-5 pb-4">
					<section className="flex h-2/6 flex-col gap-2 overflow-auto pt-4">
						{produtos?.map((produto) => (
							<div
								key={produto.produto?.id}
								className="flex w-full gap-3 pt-0.5"
							>
								<div className="flex h-24 w-24 items-center justify-center rounded-md bg-muted">
									<Image
										src={
											produto.produto?.imagens?.find(
												(img) => img.imagemPrincipal,
											)?.imagem as string
										}
										alt={produto.produto?.nome as string}
										className="size-16 object-contain"
									/>
								</div>
								<div className="flex flex-col justify-between">
									<div>
										<h6 className="truncate text-sm">
											{produto.produto?.nome}
										</h6>
										<div className="flex items-baseline gap-2">
											<h4 className="text-lg font-bold">
												{formatNumber(
													produto.produto
														?.valor as number,
												)}
											</h4>
										</div>
									</div>
									<div className="flex items-center gap-4">
										<Button
											onClick={() =>
												handleUpdateProduct(
													produto.produto
														?.id as number,
													(produto.quantidade ?? 1) -
														1,
												)
											}
											size="icon"
											variant="outline"
										>
											<ChevronLeft size={18} />
										</Button>
										<span className="text-sm">
											{produto.quantidade}
										</span>
										<Button
											onClick={() =>
												handleUpdateProduct(
													produto.produto
														?.id as number,
													(produto.quantidade ?? 1) +
														1,
												)
											}
											size="icon"
											variant="outline"
										>
											<ChevronRight size={18} />
										</Button>
									</div>
								</div>
							</div>
						))}
					</section>
					<section className="flex grow flex-col justify-between">
						<div className="grid gap-3 py-4 text-sm font-semibold text-muted-foreground">
							<Separator className="h-0.5 rounded-sm" />
							<span className="flex justify-between">
								<span>Subtotal</span>
								<span>
									{formatNumber(
										produtos?.reduce(
											(acc, produto) =>
												acc +
												(produto?.produto
													?.valor as number) *
													(produto?.quantidade as number),
											0,
										) as number,
									)}
								</span>
							</span>
							<Separator className="h-0.5 rounded-sm" />
							<span className="flex justify-between">
								<span>Entrega</span>
								<span>GRÁTIS</span>
							</span>
							<Separator className="h-0.5 rounded-sm" />
							<span className="flex justify-between">
								<span>Descontos</span>
								<span>
									-{" "}
									{formatNumber(
										produtos?.reduce(
											(acc, produto) =>
												acc +
												(produto?.produto
													?.valor as number) *
													(produto?.quantidade as number),
											0,
										) as number,
									)}
								</span>
							</span>
							<Separator className="h-0.5 rounded-sm" />
							<span className="flex justify-between text-lg text-white">
								<span>TOTAL</span>
								<span>
									{formatNumber(
										produtos?.reduce(
											(acc, produto) =>
												acc +
												(produto?.produto
													?.valor as number) *
													(produto?.quantidade as number),
											0,
										) as number,
									)}
								</span>
							</span>
						</div>
						<Button>FINALIZAR COMPRA</Button>
					</section>
				</div>
			</SheetContent>
		</Sheet>
	);
}
