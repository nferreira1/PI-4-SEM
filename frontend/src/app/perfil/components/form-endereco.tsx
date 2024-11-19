"use client";

import {
	AlertDialog,
	AlertDialogAction,
	AlertDialogCancel,
	AlertDialogContent,
	AlertDialogDescription,
	AlertDialogFooter,
	AlertDialogHeader,
	AlertDialogTitle,
	AlertDialogTrigger,
} from "@/components/ui/alert-dialog";
import { Button } from "@/components/ui/button";

export function FormEndereco({
	endereco,
}: Readonly<{ endereco: Schema["EnderecoDTO"] }>) {
	return (
		<form
			data-principal={endereco.enderecoPrincipal}
			className="relative grid rounded-lg border px-4 py-2.5 data-[principal=true]:border-l-4 data-[principal=true]:border-primary data-[principal=true]:bg-primary/10"
		>
			{endereco.enderecoPrincipal && (
				<span className="absolute right-2 top-2 font-semibold text-primary">
					(PRINCIPAL)
				</span>
			)}

			<span className="font-semibold">{endereco.nomeEndereco}</span>

			<span className="text-muted-foreground">
				{`${endereco.logradouro}, ${endereco.numero} ${endereco.complemento ? `| ${endereco.complemento}` : ""}`}
			</span>
			<span className="text-muted-foreground">
				{`${endereco.cep} - ${endereco.cidade}, ${endereco.uf}`}
			</span>

			<div className="flex justify-end">
				<Button
					className="text-xs hover:bg-transparent"
					variant="ghost"
				>
					EDITAR
				</Button>

				{!endereco.enderecoPrincipal && (
					<>
						<AlertDialog>
							<AlertDialogTrigger asChild>
								<Button
									variant="ghost"
									className="text-xs font-semibold hover:bg-transparent"
								>
									EXCLUIR
								</Button>
							</AlertDialogTrigger>

							<AlertDialogContent className="w-min rounded-lg">
								<AlertDialogHeader>
									<AlertDialogTitle className="text-center font-bold">
										Sair
									</AlertDialogTitle>
									<AlertDialogDescription className="text-center text-muted-foreground">
										Deseja mesmo excluir este endereço?
									</AlertDialogDescription>
								</AlertDialogHeader>
								<AlertDialogFooter className="mt-2 flex-row items-center justify-center gap-2">
									<AlertDialogCancel className="mt-0 w-[134px]">
										Voltar
									</AlertDialogCancel>
									<AlertDialogAction className="w-[134px] bg-destructive hover:bg-destructive/90">
										Excluir
									</AlertDialogAction>
								</AlertDialogFooter>
							</AlertDialogContent>
						</AlertDialog>

						<Button
							variant="ghost"
							className="text-xs font-semibold text-primary hover:bg-transparent hover:text-primary"
						>
							DEIXAR PADRÃO
						</Button>
					</>
				)}
			</div>
		</form>
	);
}
