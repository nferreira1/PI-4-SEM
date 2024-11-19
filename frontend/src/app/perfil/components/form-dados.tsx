"use client";

import { FormControl, FormField } from "@/components/custom/client/form-field";
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
import { useAuth } from "@/hooks/useAuth";
import { capitalizeFirstLetter } from "@/lib/utils";
import { LockKeyhole } from "lucide-react";

export function FormDados({
	generos,
}: Readonly<{
	generos: Schema["GeneroDTO"][] | undefined;
}>) {
	const { cliente } = useAuth();

	return (
		<form className="grid gap-y-4">
			<FormField label="Nome Completo *">
				<FormControl>
					<Input
						type="text"
						name="nome"
						placeholder="Nome completo"
						defaultValue={cliente?.nome}
					/>
				</FormControl>
			</FormField>

			<FormField label="Telefone *">
				<FormControl>
					<Input
						type="tel"
						name="telefone"
						placeholder="11962506450"
						defaultValue={cliente?.telefone}
					/>
				</FormControl>
			</FormField>

			<FormField label="Gênero **">
				<FormControl>
					<Select
						name="genero"
						defaultValue={cliente?.generoId?.toString()}
					>
						<SelectTrigger>
							<SelectValue placeholder="Selecione um gênero" />
						</SelectTrigger>
						<SelectContent>
							<SelectGroup>
								<SelectLabel>Gênero</SelectLabel>
								{generos?.map((genero) => (
									<SelectItem
										key={genero?.id}
										value={genero?.id?.toString() as string}
									>
										{capitalizeFirstLetter(genero?.nome)}
									</SelectItem>
								))}
							</SelectGroup>
						</SelectContent>
					</Select>
				</FormControl>
			</FormField>

			<FormField label="CPF">
				<FormControl>
					<div className="relative">
						<Input
							type="text"
							name="cpf"
							placeholder="123.456.789-10"
							defaultValue={cliente?.cpf}
							disabled
						/>
						<Button
							type="button"
							size="icon"
							className="absolute right-0 top-0"
							variant="ghost"
							disabled
						>
							<LockKeyhole size={20} />
						</Button>
					</div>
				</FormControl>
			</FormField>

			<FormField label="E-mail">
				<FormControl>
					<div className="relative">
						<Input
							type="text"
							name="email"
							placeholder="nathan@exemplo.com"
							defaultValue={cliente?.email}
							disabled
						/>
						<Button
							type="button"
							size="icon"
							className="absolute right-0 top-0"
							variant="ghost"
							disabled
						>
							<LockKeyhole size={20} />
						</Button>
					</div>
				</FormControl>
			</FormField>

			<div className="grid w-full gap-2">
				<p
					className="text-sm text-muted-foreground md:line-clamp-2 lg:line-clamp-3 xl:line-clamp-none"
					title='(**) As opções "Masculino" e "Feminino" abrangem cisgêneros e transgêneros. A opção "Outro" inclui gênero fluido, não-binário, queer, intersexo e outras identidades de gênero. Utilize a opção "Não especificado" caso não queira preencher esse campo ou não encontre a opção que mais se identifica.'
				>
					(**) As opções &quot;Masculino&quot; e &quot;Feminino&quot;
					abrangem cisgêneros e transgêneros. A opção
					&quot;Outro&quot; inclui gênero fluido, não-binário, queer,
					intersexo e outras identidades de gênero. Utilize a opção
					&quot;Não especificado&quot; caso não queira preencher esse
					campo ou não encontre a opção que mais se identifica.
				</p>
				<p className="text-sm text-muted-foreground">
					(*) Campos obrigatórios
				</p>
				<div className="flex items-center space-x-2">
					<Checkbox
						id="recebeOfertas"
						name="recebeOfertas"
						defaultChecked={cliente?.recebeOfertas}
					/>
					<Label htmlFor="recebeOfertas">
						Quero receber ofertas e novidades por e-mail da
						plataforma{" "}
						<span className="font-semibold text-primary">
							TechCommerce
						</span>
					</Label>
				</div>
			</div>
		</form>
	);
}
