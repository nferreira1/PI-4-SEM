import { Checkbox } from "@/components/ui/checkbox";
import { Label } from "@/components/ui/label";
import { Skeleton } from "@/components/ui/skeleton";
import Link from "next/link";

export default function Loading() {
	return (
		<div className="grid w-full animate-pulse place-items-center space-y-4 text-primary/10">
			<div className="grid w-full place-items-center gap-4 pb-4 text-center">
				<Skeleton className="h-9 w-36" />
				<Skeleton className="h-4 w-80" />
			</div>

			<div className="grid w-full gap-4 sm:grid-cols-2">
				{Array.from({ length: 7 }).map((_, i) => (
					<div
						data-colspan={i === 2}
						className="space-y-3 data-[colspan=true]:sm:col-span-2"
						key={i}
					>
						<Skeleton
							data-width28={i === 0 || i === 6}
							className="h-4 w-12 data-[width28=true]:w-28"
						/>
						<Skeleton className="h-10 w-full" />
					</div>
				))}
			</div>

			<div className="grid w-full gap-2">
				<p className="text-sm">
					(**) As opções &quot;Masculino&quot; e &quot;Feminino&quot;
					abrangem cisgêneros e transgêneros. A opção
					&quot;outros&quot; inclui gênero fluido, não-binário, queer,
					intersexo e outras identidades de gênero. Utilize a opção
					&quot;não especificar&quot; caso não queira preencher esse
					campo ou não encontre a opção que mais se identifica.
				</p>

				<p className="text-sm">(*) Campos obrigatórios</p>

				<div className="flex items-center space-x-2">
					<Checkbox
						id="ofertas"
						disabled
						className="border-primary/10"
					/>
					<Label id="ofertas">
						Quero receber ofertas e novidades por e-mail da
						plataforma{" "}
						<span className="font-semibold">TechCommerce</span>
					</Label>
				</div>

				<div className="items-top flex space-x-2">
					<Checkbox
						id="termos"
						className="border-primary/10"
						disabled
					/>
					<Label htmlFor="termos" className="grid">
						<span>Aceitar termos e condições</span>
						<span className="font-normal">
							Você concorda com nossos{" "}
							<Link href="" className="hover:underline">
								Termos de Serviço e Privacidade Política
							</Link>
							.
						</span>
					</Label>
				</div>
			</div>

			<Skeleton className="h-10 w-full sm:w-36" />

			<Skeleton className="h-4 w-64" />
		</div>
	);
}
