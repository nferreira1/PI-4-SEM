import { Produto } from "@/components/custom/produto";
import { Button } from "@/components/ui/button";
import {
	Carousel,
	CarouselContent,
	CarouselItem,
} from "@/components/ui/carousel";
import { api } from "@/lib/api";
import { formatNumber } from "@/lib/utils";
import { validateSchema } from "@/lib/validation";
import { ChevronLeft, ChevronRight, Star, Truck } from "lucide-react";
import { z } from "zod";
import { Imagem } from "./components/imagem";

export default async function Page({
	params,
}: {
	readonly params: {
		produtoId: string;
	};
}) {
	const schema = z.object({
		produtoId: z.coerce.number(),
	});

	const { data, success } = validateSchema(schema, params);

	if (!success) throw new Error("Produto não encontrado.");

	const [{ data: dataProduto }, { data: dataProdutos }] = await Promise.all([
		api.GET("/produto/{produtoId}", {
			params: {
				path: {
					produtoId: data.produtoId,
				},
			},
		}),
		await api.GET("/produto"),
	]);

	return (
		<>
			<section className="relative flex aspect-square items-center bg-muted md:col-span-1 md:aspect-auto md:rounded-lg lg:col-span-2">
				<Imagem data={dataProduto} />
			</section>
			<section className="grow px-5 md:col-span-1 md:rounded-lg md:bg-muted md:py-8 lg:col-span-1">
				<section className="grid gap-4 pt-8 md:pt-0">
					<div>
						<span className="text-xs text-muted-foreground">
							Novo | 100 vendidos
						</span>
						<h4 className="text-lg font-semibold">
							{dataProduto?.nome}
						</h4>
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
							{formatNumber(dataProduto?.valor as number)}
						</h6>
						<span className="text-sm text-muted-foreground">
							De:
							<span className="line-through">
								{" "}
								{formatNumber(dataProduto?.valor as number)}
							</span>
						</span>
					</div>

					<div className="flex items-center gap-4">
						<Button
							size="icon"
							className="transition-transform hover:scale-110"
						>
							<ChevronLeft size={16} />
						</Button>

						<span className="text-sm">1</span>

						<Button
							size="icon"
							className="transition-transform hover:scale-110"
						>
							<ChevronRight size={16} />
						</Button>
					</div>

					<div className="py-6">
						<h6 className="pb-2 font-semibold">Descrição</h6>
						<p className="text-sm text-muted-foreground">
							{dataProduto?.descricao}
						</p>
					</div>

					<Button size="lg">ADICIONAR AO CARRINHO</Button>

					<div className="flex items-center gap-x-6 rounded-xl bg-muted px-6 py-4 md:bg-accent">
						<Truck size={30} />

						<div className="grid text-sm">
							<span className="truncate">
								Entrega via
								<span className="font-semibold italic">
									{" "}
									Sedex
								</span>
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
			</section>

			<section className="col-span-2 overflow-x-hidden px-5 pb-8 md:px-0 md:pb-0 lg:col-span-3">
				<h4 className="text-lg font-bold">PRODUTOS RECOMENDADOS</h4>
				<Carousel
					className="pt-4"
					opts={{
						dragFree: true,
					}}
				>
					<CarouselContent>
						{dataProdutos
							?.sort(() => Math.random() - 0.5)
							?.map((produto) => (
								<CarouselItem
									key={produto.id}
									className="basis-auto"
								>
									<Produto produto={produto} />
								</CarouselItem>
							))}
					</CarouselContent>
				</Carousel>
			</section>
		</>
	);
}
