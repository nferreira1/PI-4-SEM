import { formatNumber } from "@/lib/utils";
import { MoveDown, Star } from "lucide-react";
import Image from "next/image";
import Link from "next/link";

export function Produto({
	produto,
}: Readonly<{
	produto: Schema["ProdutoDTO"];
}>) {
	return (
		<Link
			key={produto.id}
			href={`/produtos/${produto.id}`}
			className="inline-block"
		>
			<div className="flex h-60 w-40 flex-col lg:h-64 lg:w-44">
				<div className="relative flex aspect-square grow items-center justify-center rounded-md bg-muted">
					<div className="absolute left-1.5 top-1.5 flex items-center gap-1 rounded-2xl bg-primary px-2 py-1.5">
						<MoveDown size={14} strokeWidth={3} />
						<span className="text-xs font-semibold">55%</span>
					</div>
					<Image
						alt={produto.nome as string}
						src={
							produto.imagens?.find(
								(imagem) => imagem.imagemPrincipal,
							)?.imagem as string
						}
						width={96}
						height={96}
						className="object-contain"
					/>
				</div>
				<div className="flex flex-col space-y-0.5 pt-2">
					<span className="truncate text-xs lg:text-sm">
						{produto.nome}
					</span>
					<div className="flex items-baseline gap-1">
						<span className="font-semibold lg:text-lg">
							{formatNumber(produto.valor!)}
						</span>
						<span className="text-xs text-muted-foreground line-through lg:text-sm">
							{formatNumber(produto.valor!)}
						</span>
					</div>
					<div className="flex items-center gap-0.5">
						{Array.from({ length: 5 }).map((_, index) => (
							<Star
								key={index}
								size={15}
								className="fill-primary text-primary data-[last=true]:fill-transparent"
							/>
						))}
						<span className="ml-1 text-xs text-muted-foreground lg:text-sm">
							(25)
						</span>
					</div>
				</div>
			</div>
		</Link>
	);
}
