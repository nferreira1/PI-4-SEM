import { Button } from "@/components/ui/button";
import {
	Card,
	CardContent,
	CardDescription,
	CardFooter,
	CardHeader,
	CardTitle,
} from "@/components/ui/card";
import { api } from "@/lib/api";
import { verifySession } from "@/server/session.server";
import { FileSpreadsheet, MapPin } from "lucide-react";
import { FormDados } from "./components/form-dados";
import { FormEndereco } from "./components/form-endereco";

export default async function Page() {
	const session = await verifySession();

	const { data, error } = await api.GET("/genero");

	if (error) {
		throw new Error(error.message);
	}

	return (
		<>
			<Card className="flex h-min flex-col justify-between sm:h-[744px]">
				<CardHeader>
					<CardTitle className="flex items-center gap-1.5">
						<FileSpreadsheet className="text-primary" size={20} />
						<span>DADOS BÁSICOS</span>
					</CardTitle>
					<CardDescription>
						Edite os seus dados básicos, como nome, celular e
						gênero.
					</CardDescription>
				</CardHeader>

				<CardContent>
					<FormDados generos={data} />
				</CardContent>

				<CardFooter>
					<Button className="w-full" size="lg">
						SALVAR ALTERAÇÕES
					</Button>
				</CardFooter>
			</Card>

			<Card className="relative flex h-[744px] flex-col">
				<CardHeader>
					<CardTitle className="flex items-center gap-1.5">
						<MapPin className="text-primary" size={20} />
						<span>ENDEREÇOS</span>
					</CardTitle>
					<CardDescription>
						Crie, edite ou exclua os seus endereços de entrega.
					</CardDescription>
				</CardHeader>

				<CardContent className="grid gap-4 overflow-auto pb-24">
					{session?.cliente?.enderecos
						?.sort((a, b) =>
							a.enderecoPrincipal === b.enderecoPrincipal
								? 0
								: a.enderecoPrincipal
									? -1
									: 1,
						)
						?.map((endereco, i) => (
							<FormEndereco key={i} endereco={endereco} />
						))}
				</CardContent>

				<CardFooter className="absolute bottom-0 w-full rounded-b-lg bg-card px-6">
					<Button size="lg" className="w-full">
						CADASTRAR NOVO ENDEREÇO
					</Button>
				</CardFooter>
			</Card>
		</>
	);
}
