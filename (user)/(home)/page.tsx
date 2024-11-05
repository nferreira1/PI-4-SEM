import { Button } from "@/components/ui/button";
import { api } from "@/lib/api";
import Image from "next/image";
import Link from "next/link";
import { Carousel } from "./components/carousel";
import { PromoBanner } from "./components/promo-banner";

export default async function Page() {
	const [{ data: dataCategorias }, { data: dataProdutos }] =
		await Promise.all([api.GET("/categoria"), api.GET("/produto")]);

	const mouses = dataProdutos?.filter((produto) => produto.categoriaId === 2);
	const headsets = dataProdutos?.filter(
		(produto) => produto.categoriaId === 3,
	);

	const dezMelhoresOfertas = dataProdutos
		?.sort((a, b) => (a.valor as number) - (b.valor as number))
		.slice(0, 10);

	return (
		<>
			<section className="relative hidden h-[300px] w-full bg-primary sm:block lg:h-[500px]">
				<Image
					src="/notebook.jpg"
					alt=""
					layout="fill"
					className="absolute h-full w-full bg-primary object-cover"
				/>
				<div className="absolute inset-0 bg-primary opacity-60" />

				<div className="absolute left-1/2 top-1/2 z-50 mx-auto flex h-full w-full max-w-screen-xl -translate-x-1/2 -translate-y-1/2 items-center justify-between">
					<p className="text-center text-6xl font-bold leading-tight">
						<span className="block">Ofertas</span>
						<span className="block rounded-full border-2 border-primary px-4">
							Imperdíveis
						</span>
					</p>

					<p>
						<span className="block">
							<span className="text-4xl font-light">até </span>
							<span className="text-6xl font-bold">55%</span>
						</span>
						<span className="text-6xl font-bold leading-8">
							Desconto
						</span>
						<span className="block text-4xl font-light leading-5">
							só esse mês
						</span>
					</p>
				</div>
			</section>

			<div className="mx-auto w-full max-w-screen-xl px-5 py-6">
				<div className="flex h-40 w-full items-center justify-between rounded-xl bg-gradient-to-r from-primary to-primary/30 px-5 sm:hidden">
					<p className="grid">
						<span>
							<span>até </span>
							<span className="text-6xl font-bold">55%</span>
						</span>
						<span className="text-4xl font-bold leading-6">
							Desconto
						</span>
						<span>só esse mês</span>
					</p>

					<p>a</p>
				</div>

				<section className="grid grid-cols-[repeat(auto-fit,minmax(150px,1fr))] gap-x-4 gap-y-2 pt-8 sm:pt-0">
					{dataCategorias?.map((categoria) => (
						<Button
							key={categoria.id}
							variant="outline"
							size="lg"
							asChild
						>
							<Link href={`/categorias/${categoria.id}`}>
								{categoria.nome}
							</Link>
						</Button>
					))}
				</section>

				<section className="pt-8">
					<p className="font-bold">MELHORES OFERTAS</p>
					<Carousel data={dezMelhoresOfertas!} />
				</section>

				<div className="w-full gap-4 sm:flex">
					<PromoBanner
						className="grow"
						categoria="headsets"
						imagem="/headset.png"
						porcentagemDesconto={20}
						href={`/categorias/${3}`}
						invert
					/>

					<PromoBanner
						className="hidden grow md:block"
						categoria="mouses"
						imagem="/mouse.png"
						porcentagemDesconto={55}
						href={`/categorias/${2}`}
					/>
				</div>

				<section className="pt-8">
					<p className="font-bold">MOUSES</p>
					<Carousel data={mouses!} />
				</section>

				<PromoBanner
					className="block md:hidden"
					categoria="mouses"
					imagem="/mouse.png"
					porcentagemDesconto={55}
					href={`/categorias/${2}`}
				/>

				<section className="pt-8">
					<p className="font-bold">HEADSETS</p>
					<Carousel data={headsets!} />
				</section>
			</div>
		</>
	);
}
