import { Produto } from "@/components/custom/server/produto";
import {
	Carousel,
	CarouselContent,
	CarouselItem,
} from "@/components/ui/carousel";
import { api } from "@/lib/api";
import { validateSchema } from "@/lib/validation";
import { z } from "zod";
import { DetalhesProdutos } from "./components/detalhes-produto";
import { Imagem } from "./components/imagem";

export default async function Page({
	params,
}: {
	readonly params: Promise<{
		produtoId: string;
	}>;
}) {
	const schema = z.object({
		produtoId: z.coerce.number(),
	});

	const { data, success } = validateSchema(schema, await params);

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
			<section className="relative flex aspect-square h-[570px] items-center bg-muted md:col-span-1 md:aspect-auto md:rounded-lg lg:col-span-2">
				<Imagem data={dataProduto} />
			</section>

			<DetalhesProdutos produto={dataProduto} />

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
