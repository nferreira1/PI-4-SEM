import { SheetCarrinho } from "@/components/custom/client/sheet-carrinho";
import { SheetMenu } from "@/components/custom/client/sheet-menu";
import { Button } from "@/components/ui/button";
import { verifySession } from "@/server/session.server";
import { ShoppingCart, User2 } from "lucide-react";
import Link from "next/link";

export async function Header() {
	const session = await verifySession();

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
					{!session ? (
						<Link href="/login">
							<Button variant="outline" size="icon">
								<User2 size={20} />
							</Button>
						</Link>
					) : (
						<SheetMenu />
					)}
					<Link href="/carrinho">
						<Button
							variant="outline"
							size="icon"
							className="inline-flex lg:hidden"
						>
							<ShoppingCart size={20} />
						</Button>
					</Link>

					<SheetCarrinho />
				</div>
			</div>
		</header>
	);
}
