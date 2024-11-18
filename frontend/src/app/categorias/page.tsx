import { api } from "@/lib/api";
import { LayoutGrid } from "lucide-react";
import Image from "next/image";
import Link from "next/link";

export default async function Page() {
	const { data, error } = await api.GET("/categoria");

	if (error) {
		throw new Error(error.message);
	}

	return (
		<>
			<section className="pb-8">
				<div className="flex w-min items-center space-x-1 rounded-full border-2 border-primary px-4 py-1">
					<LayoutGrid size={20} fill="white" strokeWidth={2} />
					<span className="text-sm font-semibold">CATEGORIAS</span>
				</div>
			</section>

			<section className="flex w-full flex-wrap gap-4 lg:gap-6">
				{data?.map((categoria) => (
					<>
						<Link href={`/categorias/${categoria.id}`}>
							<div className="flex h-48 w-40 cursor-pointer flex-col overflow-hidden rounded-md lg:h-64 lg:w-96">
								<div className="flex grow items-center justify-center bg-gradient-to-r from-primary to-primary/30">
									<Image
										alt={
											categoria.nome ??
											"Categoria sem nome"
										}
										src={
											categoria.imagem ??
											"/path/to/default/image.jpg"
										}
										width={160}
										height={160}
										className="size-32 object-contain lg:size-40"
									/>
								</div>

								<div className="grid h-10 w-full place-items-center bg-muted lg:min-h-14">
									<span className="truncate text-xs font-semibold lg:text-base">
										{categoria.nome}
									</span>
								</div>
							</div>
						</Link>
					</>
				))}
			</section>
		</>
	);
}
