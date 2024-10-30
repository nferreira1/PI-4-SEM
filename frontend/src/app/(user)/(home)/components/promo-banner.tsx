import { cn } from "@/lib/utils";
import Image from "next/image";
import Link from "next/link";

export function PromoBanner({
	imagem,
	porcentagemDesconto,
	categoria,
	className,
	href,
	invert,
	...props
}: React.HTMLAttributes<HTMLElement> &
	Readonly<{
		imagem: string;
		porcentagemDesconto: number;
		categoria: string;
		href: string;
		invert?: boolean;
	}>) {
	return (
		<section className={cn("pt-8", className)}>
			<Link href={href}>
				<div
					data-invert={invert}
					className="flex h-40 w-full items-center justify-around rounded-xl bg-gradient-to-r from-[#36393C] to-[#36393C]/30 px-5 data-[invert=true]:bg-gradient-to-l sm:h-52 sm:justify-evenly"
				>
					<Image
						src={imagem}
						alt="imagem"
						width={175}
						height={175}
						className="object-cover"
					/>

					<span className="grid leading-tight">
						<span className="text-lg font-thin">até</span>
						<span>
							<span className="text-5xl font-extrabold leading-7 text-primary">
								{porcentagemDesconto}%
							</span>
							<span className="font-thin"> de</span>
						</span>
						<span className="text-3xl font-bold leading-7">
							Desconto
						</span>
						<span className="font-thin">em {categoria}</span>
					</span>
				</div>
			</Link>
		</section>
	);
}
