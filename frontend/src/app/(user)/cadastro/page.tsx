import { FormField } from "@/components/custom/form-field";
import { Button } from "@/components/ui/button";
import { Checkbox } from "@/components/ui/checkbox";
import { Label } from "@/components/ui/label";

export default async function Page() {
	return (
		<form className="w-full space-y-4">
			<div className="grid gap-2 pb-4 text-center">
				<h1 className="text-3xl font-bold">Criar conta</h1>
				<p className="text-balance text-muted-foreground">
					Insira suas informações para criar uma conta
				</p>
			</div>

			<div className="grid gap-4 sm:grid-cols-2">
				<FormField
					type="text"
					name="nome"
					label="Nome"
					placeholder="Nathan Ferreira"
					required
				/>

				<FormField
					type="text"
					name="cpf"
					label="CPF"
					placeholder="123.456.789-10"
					maxLength={11}
					required
				/>

				<FormField
					type="tel"
					name="telefone"
					label="Telefone"
					placeholder="11 96250-6450"
					required
				/>

				<FormField
					type="email"
					name="email"
					label="E-mail"
					placeholder="nathan@exemplo.com.br"
					required
				/>

				<FormField
					type="password"
					name="senha"
					label="Senha"
					required
				/>

				<FormField
					type="password"
					name="senha"
					label="Confirmar senha"
					required
				/>
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
							<span className="font-normal text-muted-foreground">
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
