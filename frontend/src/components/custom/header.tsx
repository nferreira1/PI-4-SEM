import { Menu, ShoppingCart, User2 } from "lucide-react";
import Link from "next/link";
import { Button } from "../ui/button";
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
			{/* <div className="fixed left-0 top-0 z-50 h-full w-96 -translate-x-full transform border bg-background transition-transform duration-300">
				<div className="flex items-center justify-between border-b p-5">
					<h2 className="text-lg font-semibold">Menu</h2>
					<Button
						variant="outline"
						size="icon"
						className="fill-muted-foreground"
					>
						<Menu size={20} />
					</Button>
				</div>
				<div className="p-5">
					<Link href="/login/">
						<Button className="flex w-full items-center gap-2 rounded-md border bg-muted px-3 py-2">
							<svg
								className="lucide lucide-log-out"
								fill="none"
								height="20"
								stroke="currentColor"
								stroke-linecap="round"
								stroke-linejoin="round"
								stroke-width="2"
								viewBox="0 0 24 24"
								width="20"
								xmlns="http://www.w3.org/2000/svg"
							>
								<path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4" />
								<polyline points="16 17 21 12 16 7" />
								<line x1="21" x2="9" y1="12" y2="12" />
							</svg>
							<span className="font-semibold">Login</span>
						</Button>
					</Link>
				</div>
			</div> */}
			<div className="mx-auto flex min-h-20 max-w-screen-xl items-center justify-between px-5">
				<Sheet>
					<SheetTrigger asChild>
						<Button
							variant="outline"
							size="icon"
							className="inline-flex md:hidden"
						>
							<Menu size={20} />
						</Button>
					</SheetTrigger>
					<SheetContent side="left">
						<SheetHeader className="border-b">
							<SheetTitle>Menu</SheetTitle>
						</SheetHeader>

						{/* <SheetFooter>
							<SheetClose asChild>
								<Button type="submit">Save changes</Button>
							</SheetClose>
						</SheetFooter> */}
					</SheetContent>
				</Sheet>

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
						<Button
							variant="outline"
							size="icon"
							className="hidden md:inline-flex"
						>
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
				</div>
			</div>
		</header>
	);
}
