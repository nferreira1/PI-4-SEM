"use client";

import { Button } from "@/components/ui/button";
import { Checkbox } from "@/components/ui/checkbox";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Eye, EyeOff } from "lucide-react";
import { useState } from "react";

export function FormCriarConta() {
	const [password, setPassword] =
		useState<React.InputHTMLAttributes<HTMLInputElement>["type"]>(
			"password",
		);

	const handlePassword = () =>
		setPassword((prev) => (prev === "password" ? "text" : "password"));

	return (
		<form className="w-full space-y-4">
			<div className="grid gap-2 pb-4 text-center">
				<h1 className="text-3xl font-bold">Criar conta</h1>
				<p className="text-balance text-muted-foreground">
					Insira suas informações para criar uma conta
				</p>
			</div>

			<div className="grid gap-4 sm:grid-cols-2">
				<div className="grid gap-2">
					<Label htmlFor="nome">Nome completo *</Label>
					<Input id="nome" placeholder="Nathan Ferreira" />
				</div>

				<div className="grid gap-2">
					<Label htmlFor="cpf">CPF *</Label>
					<Input
						id="cpf"
						placeholder="123.456.789-10"
						type="number"
					/>
				</div>

				<div className="grid gap-2">
					<Label htmlFor="telefone">Telefone *</Label>
					<Input
						id="telefone"
						placeholder="11 96250-6450"
						type="tel"
					/>
				</div>

				<div className="grid gap-2">
					<Label htmlFor="email">E-mail *</Label>
					<Input
						id="email"
						placeholder="nathan@exemplo.com.br"
						type="email"
					/>
				</div>

				<div className="grid gap-2">
					<Label htmlFor="senha">Senha *</Label>
					<div className="relative flex items-center justify-end">
						<Input
							id="senha"
							type={password}
							placeholder="••••••••"
						/>
						<Button
							type="button"
							size="icon"
							variant="ghost"
							className="absolute right-0"
							onClick={handlePassword}
						>
							{password === "password" ? (
								<Eye size={20} />
							) : (
								<EyeOff size={20} />
							)}
						</Button>
					</div>
				</div>

				<div className="grid gap-2">
					<Label htmlFor="confirmarSenha">Confirme sua senha *</Label>
					<div className="relative flex items-center justify-end">
						<Input
							id="confirmarSenha"
							type={password}
							placeholder="••••••••"
						/>
						<Button
							type="button"
							size="icon"
							variant="ghost"
							className="absolute right-0"
							onClick={handlePassword}
						>
							{password === "password" ? (
								<Eye size={20} />
							) : (
								<EyeOff size={20} />
							)}
						</Button>
					</div>
				</div>
			</div>

			<div className="grid gap-2">
				<p className="text-sm text-muted-foreground">
					(*) Campos obrigatórios
				</p>
				<div className="flex items-center space-x-2">
					<Checkbox id="ofertas" disabled />
					<Label id="ofertas">
						Quero receber ofertas e novidades por e-mail da
						plataforma TechCommerce
					</Label>
				</div>

				<div className="items-top flex space-x-2">
					<Checkbox id="termos" />
					<div className="grid leading-none">
						<Label htmlFor="termos" className="grid">
							<span>Aceitar termos e condições</span>
							<span className="font-normal leading-tight text-muted-foreground sm:leading-relaxed">
								Você concorda com nossos Termos de Serviço e
								Privacidade Política.
							</span>
						</Label>
					</div>
				</div>
			</div>

			<div className="grid place-items-center gap-y-8">
				<Button size="lg" className="w-full sm:w-min">
					Criar conta
				</Button>

				<span className="text-center">
					Já possui cadastro?{" "}
					<a
						className="font-bold text-primary underline"
						href="/login/"
					>
						Fazer login
					</a>
				</span>
			</div>
		</form>
	);
}
