"use client";

import { FormControl, FormField } from "@/components/custom/form-field";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import Link from "next/link";

export function Form() {
	return (
		<form className="grid w-[350px] gap-5">
			<div className="grid gap-2 pb-4 text-center">
				<h1 className="text-3xl font-bold">Login</h1>
				<p className="text-balance text-muted-foreground">
					Digite seu e-mail e senha abaixo para fazer login
				</p>
			</div>

			<FormField label="E-mail">
				<FormControl>
					<Input
						type="text"
						name="email"
						placeholder="nathan@exemplo.com"
					/>
				</FormControl>
			</FormField>

			<FormField label="Senha">
				<FormControl>
					<Input
						type="password"
						name="senha"
						placeholder="••••••••"
					/>
				</FormControl>
			</FormField>

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
