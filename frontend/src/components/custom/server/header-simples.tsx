import { Button } from "@/components/ui/button";
import { ArrowLeft } from "lucide-react";
import Link from "next/link";

export async function HeaderSimples() {
	return (
		<header className="border-b">
			<div className="mx-auto flex min-h-20 max-w-screen-xl items-center justify-between px-5">
				<Button
					variant="ghost"
					className="flex items-center gap-2"
					asChild
				>
					<Link href="/">
						<ArrowLeft size={18} />
						<span className="font-semibold">Voltar</span>
					</Link>
				</Button>

				<Link href="/">
					<h1 className="text-xl font-bold text-primary">
						Tech
						<span className="font-semibold text-foreground">
							Commerce
						</span>
					</h1>
				</Link>
			</div>
		</header>
	);
}
