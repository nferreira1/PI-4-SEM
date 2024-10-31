"use client";

import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Eye } from "lucide-react";
import Link from "next/link";
import { useState } from "react";

export function FormLogin() {
	const [password, setPassword] =
		useState<React.InputHTMLAttributes<HTMLInputElement>["type"]>(
			"password",
		);

	const handlePassword = () =>
		setPassword((prev) => (prev === "password" ? "text" : "password"));

	return (
		<form className="grid w-[350px] gap-5">
			<div className="grid gap-2 pb-4 text-center">
				<h1 className="text-3xl font-bold">Login</h1>
				<p className="text-balance text-muted-foreground">
					Digite seu e-mail e senha abaixo para fazer login
				</p>
			</div>

			<div className="grid gap-2">
				<Label htmlFor="email">E-mail</Label>
				<Input id="email" placeholder="nathan@exemplo.com.br" />
			</div>

			<div className="grid gap-2">
				<Label htmlFor="senha">Senha</Label>
				<div className="relative flex items-center justify-end">
					<Input id="senha" type={password} placeholder="••••••••" />
					<Button
						type="button"
						size="icon"
						variant="ghost"
						className="absolute right-0"
						onClick={handlePassword}
					>
						<Eye size={20} />
					</Button>
				</div>
			</div>

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
