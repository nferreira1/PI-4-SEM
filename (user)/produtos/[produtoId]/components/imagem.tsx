"use client";

import Image from "next/image";
import { useState } from "react";

export function Imagem({
	data,
}: {
	readonly data: Schema["ProdutoDTO"] | undefined;
}) {
	const [imagemSelecionada, setImagemSelecionada] = useState<
		Schema["ProdutoImagensDTO"]["imagem"]
	>(data?.imagens?.find((imagem) => imagem.imagemPrincipal)?.imagem);

	const [isAnimating, setIsAnimating] = useState(false);

	const handleImagemSelecionada = (
		imagem: Schema["ProdutoImagensDTO"]["imagem"],
	) => {
		setIsAnimating(true);
		setTimeout(() => {
			setImagemSelecionada(imagem);
			setIsAnimating(false);
		}, 300);
	};

	return (
		<>
			<div className="relative grid h-full w-full place-items-center">
				<Image
					src={imagemSelecionada as string}
					alt={data?.nome as string}
					className={`size-72 object-contain transition-opacity duration-300 ${
						isAnimating ? "opacity-0" : "opacity-100"
					}`}
					width={288}
					height={288}
				/>

				<div className="absolute bottom-8 flex justify-evenly gap-4 md:hidden">
					{data?.imagens?.map((imagem) => (
						<button
							key={imagem.imagem as string}
							data-imagem-selecionada={
								imagemSelecionada === imagem.imagem
							}
							className="flex aspect-square h-20 cursor-pointer items-center justify-center rounded-lg bg-background data-[imagem-selecionada=true]:border data-[imagem-selecionada=true]:border-primary"
							onClick={() =>
								handleImagemSelecionada(imagem.imagem)
							}
						>
							<Image
								src={imagem.imagem as string}
								alt={data.nome as string}
								className="pointer-events-none size-14 object-contain"
								width={56}
								height={56}
							/>
						</button>
					))}
				</div>
			</div>

			<div className="left-8 top-8 hidden flex-col gap-4 md:absolute lg:flex">
				{data?.imagens?.map((imagem) => (
					<button
						key={imagem.imagem as string}
						data-imagem-selecionada={
							imagemSelecionada === imagem.imagem
						}
						className="flex aspect-square h-20 cursor-pointer items-center justify-center rounded-lg bg-muted data-[imagem-selecionada=true]:border data-[imagem-selecionada=true]:border-primary md:bg-background"
						onClick={() => handleImagemSelecionada(imagem.imagem)}
					>
						<Image
							src={imagem.imagem as string}
							alt={data.nome as string}
							className="pointer-events-none size-14 object-contain"
							width={56}
							height={56}
						/>
					</button>
				))}
			</div>
		</>
	);
}
