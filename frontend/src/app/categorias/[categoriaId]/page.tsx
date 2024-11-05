import { Produto } from "@/components/custom/produto";
import { api } from "@/lib/api";
import { validateSchema } from "@/lib/validation";
import { z } from "zod";

export default async function Page({
	params,
}: Readonly<{
	params: {
		categoriaId: string;
	};
}>) {
	const schema = z.object({
		categoriaId: z.coerce.number().refine((value) => !isNaN(value), {
			message: "Categoria não encontrada.",
		}),
	});

	const { data, success } = validateSchema(schema, await params);

	if (!success) throw new Error("Categoria não encontrada.");

	const [{ data: dataCategorias }, { data: dataProdutos }] =
		await Promise.all([
			await api.GET("/categoria/{categoriaId}", {
				params: {
					path: {
						categoriaId: data.categoriaId,
					},
				},
			}),
			await api.GET("/produto/categoria/{categoriaId}/produtos", {
				params: {
					path: {
						categoriaId: data.categoriaId,
					},
				},
				cache: "no-cache",
			}),
		]);

	return (
		<>
			<section className="pb-8">
				<div className="flex w-min items-center space-x-1 rounded-full border-2 border-primary px-4 py-1">
					<span className="text-sm font-semibold">
						{dataCategorias?.nome}
					</span>
				</div>
			</section>

			<section className="flex flex-wrap gap-6">
				{dataProdutos?.map((produto) => (
					<Produto key={produto.id} produto={produto} />
				))}
			</section>
		</>
	);
}
