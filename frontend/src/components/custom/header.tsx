import { ShoppingCart, User2 } from "lucide-react";
import Link from "next/link";
import { Button } from "../ui/button";
import { Separator } from "../ui/separator";
import {
	Sheet,
	SheetContent,
	SheetHeader,
	SheetTitle,
	SheetTrigger,
} from "../ui/sheet";

export function Header() {
	return (
		<header className="border-b">
			<div className="mx-auto flex min-h-20 max-w-screen-xl items-center justify-between px-5">
				<Link href="/">
					<h1 className="text-xl font-bold text-primary">
						Tech
						<span className="font-semibold text-foreground">
							Commerce
						</span>
					</h1>
				</Link>

				<div className="hidden items-baseline gap-6 font-semibold md:flex">
					<Link href="/">
						<Button
							variant="ghost"
							className="text-base hover:bg-transparent"
						>
							Início
						</Button>
					</Link>
					<p className="text-2xl font-bold text-muted">|</p>
					<Link href="/categorias">
						<Button
							variant="ghost"
							className="text-base hover:bg-transparent"
						>
							Categorias
						</Button>
					</Link>
				</div>

				<div className="flex gap-2">
					<Link href="/login">
						<Button variant="outline" size="icon">
							<User2 size={20} />
						</Button>
					</Link>
					<Link href="/carrinho">
						<Button
							variant="outline"
							size="icon"
							className="inline-flex lg:hidden"
						>
							<ShoppingCart size={20} />
						</Button>
					</Link>

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
										{/* ADICIONAR QUANTIDADE DE ITENS DO CARRINHO */}
									</span>
								</div>
							</Button>
						</SheetTrigger>
						<SheetContent
							side="right"
							className="flex flex-col gap-0"
						>
							<SheetHeader className="border-b">
								<SheetTitle>Carrinho</SheetTitle>
							</SheetHeader>

							<div className="flex grow flex-col justify-between px-5 pb-4">
								<section className="flex h-2/6 flex-col gap-2">
									{/* ADICIONAR ITENS AO CARRINHO */}
								</section>
								<section className="flex grow flex-col justify-between">
									<div className="grid gap-3 py-4 text-sm font-semibold text-muted-foreground">
										<Separator className="h-0.5 rounded-sm" />
										<span className="flex justify-between">
											<span>Subtotal</span>
											<span>R$ 1.000,00</span>
										</span>
										<Separator className="h-0.5 rounded-sm" />
										<span className="flex justify-between">
											<span>Entrega</span>
											<span>GRÁTIS</span>
										</span>
										<Separator className="h-0.5 rounded-sm" />
										<span className="flex justify-between">
											<span>Descontos</span>
											<span>- R$ 1.000,00</span>
										</span>
										<Separator className="h-0.5 rounded-sm" />
										<span className="flex justify-between text-lg text-white">
											<span>TOTAL</span>
											<span>R$ 1.000,00</span>
										</span>
									</div>
									<Button>FINALIZAR COMPRA</Button>
								</section>
							</div>
						</SheetContent>
					</Sheet>
				</div>
			</div>
		</header>
	);
}
