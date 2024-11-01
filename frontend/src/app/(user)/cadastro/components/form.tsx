"use client";

import { FormControl, FormField } from "@/components/custom/form-field";
import { Button } from "@/components/ui/button";
import { Checkbox } from "@/components/ui/checkbox";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import {
	Select,
	SelectContent,
	SelectGroup,
	SelectItem,
	SelectLabel,
	SelectTrigger,
	SelectValue,
} from "@/components/ui/select";
import { RotateCw } from "lucide-react";
import Link from "next/link";
import { useActionState } from "react";
import { criarConta } from "../actions";

export function Form() {
	const [state, action, isPending] = useActionState(criarConta, null);

	console.log(state?.validation);

	return (
		<form className="grid place-items-center space-y-4" action={action}>
			<div className="grid gap-2 pb-4 text-center">
				<h1 className="text-3xl font-bold">Criar conta</h1>
				<p className="text-balance text-muted-foreground">
					Insira suas informações para criar uma conta
				</p>
			</div>

			<div className="grid w-full gap-4 sm:grid-cols-2">
				<FormField
					label="Nome Completo *"
					error={state?.validation?.nome}
				>
					<FormControl>
						<Input
							type="text"
							name="nome"
							placeholder="Nome completo"
						/>
					</FormControl>
				</FormField>

				<FormField label="CPF *" error={state?.validation?.cpf}>
					<FormControl>
						<Input
							type="text"
							name="cpf"
							placeholder="123.456.789-10"
						/>
					</FormControl>
				</FormField>

				<FormField
					className="sm:col-span-2"
					label="Gênero **"
					error={state?.validation?.genero}
				>
					<FormControl>
						<Select name="genero">
							<SelectTrigger>
								<SelectValue placeholder="Selecione um gênero" />
							</SelectTrigger>
							<SelectContent>
								<SelectGroup>
									<SelectLabel>Gênero</SelectLabel>
									<SelectItem value="não especificado">
										Não especificado
									</SelectItem>
									<SelectItem value="masculino">
										Masculino
									</SelectItem>
									<SelectItem value="feminino">
										Feminino
									</SelectItem>
									<SelectItem value="outro">Outro</SelectItem>
								</SelectGroup>
							</SelectContent>
						</Select>
					</FormControl>
				</FormField>

				<FormField
					label="Telefone *"
					error={state?.validation?.telefone}
				>
					<FormControl>
						<Input
							type="tel"
							name="telefone"
							placeholder="11962506450"
						/>
					</FormControl>
				</FormField>

				<FormField label="E-mail *" error={state?.validation?.email}>
					<FormControl>
						<Input
							type="text"
							name="email"
							placeholder="nathan@exemplo.com"
						/>
					</FormControl>
				</FormField>

				<FormField label="Senha *" error={state?.validation?.senha}>
					<FormControl>
						<Input
							type="password"
							name="senha"
							placeholder="••••••••"
						/>
					</FormControl>
				</FormField>

				<FormField
					label="Confirmar senha *"
					error={state?.validation?.confirmarSenha}
				>
					<FormControl>
						<Input
							type="password"
							name="confirmarSenha"
							placeholder="••••••••"
						/>
					</FormControl>
				</FormField>
			</div>

			<div className="grid w-full gap-2">
				<p className="text-sm text-muted-foreground">
					(**) As opções &quot;Masculino&quot; e &quot;Feminino&quot;
					abrangem cisgêneros e transgêneros. A opção
					&quot;outros&quot; inclui gênero fluido, não-binário, queer,
					intersexo e outras identidades de gênero. Utilize a opção
					&quot;não especificar&quot; caso não queira preencher esse
					campo ou não encontre a opção que mais se identifica.
				</p>
				<p className="text-sm text-muted-foreground">
					(*) Campos obrigatórios
				</p>
				<div className="flex items-center space-x-2">
					<Checkbox id="ofertas" disabled />
					<Label id="ofertas">
						Quero receber ofertas e novidades por e-mail da
						plataforma{" "}
						<span className="font-semibold text-primary">
							TechCommerce
						</span>
					</Label>
				</div>

				<div className="items-top flex space-x-2">
					<Checkbox id="termos" disabled={isPending} />
					<Label htmlFor="termos" className="grid">
						<span>Aceitar termos e condições</span>
						<span className="font-normal text-muted-foreground">
							Você concorda com nossos{" "}
							<Link
								href=""
								className="text-primary hover:underline"
							>
								Termos de Serviço e Privacidade Política
							</Link>
							.
						</span>
					</Label>
				</div>
			</div>

			<Button
				type="submit"
				size="lg"
				className="w-full sm:w-min"
				disabled={isPending}
			>
				{isPending ? (
					<>
						<RotateCw className="animate-spin" size={20} />
						<span className="ml-2">Enviando...</span>
					</>
				) : (
					"Criar conta"
				)}
			</Button>
		</form>
	);
}
