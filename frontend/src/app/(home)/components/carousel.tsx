"use client";

import { Produto } from "@/components/custom/produto";
import {
	Carousel as C,
	CarouselContent,
	CarouselItem,
} from "@/components/ui/carousel";
import AutoScroll from "embla-carousel-auto-scroll";

export function Carousel({
	data,
}: Readonly<{
	data: Schema["ProdutoDTO"][];
}>) {
	return (
		<C
			className="pt-4"
			opts={{
				dragFree: true,
				loop: true,
			}}
			plugins={[
				AutoScroll({
					active: true,
				}),
			]}
		>
			<CarouselContent>
				{data?.map((produto) => (
					<CarouselItem key={produto.id} className="basis-auto">
						<Produto produto={produto} />
					</CarouselItem>
				))}
			</CarouselContent>
		</C>
	);
}
