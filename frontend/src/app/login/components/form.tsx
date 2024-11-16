"use client";

import { FormControl, FormField } from "@/components/custom/client/form-field";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { RotateCw } from "lucide-react";
import Link from "next/link";
import { useActionState } from "react";
import { singin } from "../actions";

export function Form() {
	const [state, action, isPending] = useActionState(singin, null);

	return (
		<form className="grid w-[350px] gap-5" action={action}>
			<div className="grid gap-2 pb-4 text-center">
				<h1 className="text-3xl font-bold">Login</h1>
				<p className="text-balance text-muted-foreground">
					Digite seu e-mail e senha abaixo para fazer login
				</p>
			</div>

			<FormField label="E-mail" error={state?.validation.email}>
				<FormControl>
					<Input
						type="text"
						name="email"
						placeholder="nathan@exemplo.com"
						defaultValue={state?.previousState.email as string}
						disabled={isPending}
					/>
				</FormControl>
			</FormField>

			<FormField label="Senha" error={state?.validation.senha}>
				<FormControl>
					<Input
						type="password"
						name="senha"
						placeholder="••••••••"
						defaultValue={state?.previousState.senha as string}
						disabled={isPending}
					/>
				</FormControl>
			</FormField>

			<div className="grid gap-y-8">
				<Button type="submit">
					{isPending ? (
						<>
							<RotateCw className="animate-spin" size={20} />
							<span className="ml-2">Enviando...</span>
						</>
					) : (
						"Entrar"
					)}
				</Button>

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
