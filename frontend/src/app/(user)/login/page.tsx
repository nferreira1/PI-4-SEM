import { FormField } from "@/components/custom/form-field";
import { Button } from "@/components/ui/button";
import Link from "next/link";

export default async function Page() {
	return (
		<form className="grid w-[350px] gap-5">
			<div className="grid gap-2 pb-4 text-center">
				<h1 className="text-3xl font-bold">Login</h1>
				<p className="text-balance text-muted-foreground">
					Digite seu e-mail e senha abaixo para fazer login
				</p>
			</div>

			<FormField
				type="email"
				label="E-mail"
				name="email"
				placeholder="nathan@exemplo.com.br"
			/>

			<FormField type="password" name="senha" label="Senha" required />

			<div className="grid gap-y-8">
				<Button type="submit">Entrar</Button>

				<span className="text-center">
					Não tem uma conta?{" "}
					<Link
						className="font-bold text-primary underline"
						href="/cadastro"
					>
						Inscreva-se
					</Link>
				</span>
			</div>
		</form>
	);
}
