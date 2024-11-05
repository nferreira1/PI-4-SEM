import {
	Accordion,
	AccordionContent,
	AccordionItem,
	AccordionTrigger,
} from "@/components/ui/accordion";
import { Separator } from "@/components/ui/separator";
import { ShoppingBasket } from "lucide-react";

export default async function Page() {
	return (
		<>
			<section className="pb-8">
				<div className="flex w-max items-center space-x-1 rounded-full border-2 border-primary px-4 py-1">
					<ShoppingBasket size={20} strokeWidth={2} />
					<span className="text-sm font-semibold">MEUS PEDIDOS</span>
				</div>
			</section>

			<section>
				<Accordion type="multiple" className="grid w-full gap-4">
					<AccordionItem value="001">
						<AccordionTrigger>
							<div>
								<h6 className="font-bold">NÚMERO DO PEDIDO</h6>
								<span className="font-normal text-muted-foreground">
									#001
								</span>
							</div>
						</AccordionTrigger>
						<AccordionContent className="grid gap-5">
							<div className="flex justify-between">
								<p className="grid">
									<span className="font-semibold">
										STATUS
									</span>
									<span className="font-semibold text-primary">
										Pago
									</span>
								</p>
								<p className="grid">
									<span className="font-semibold">DATA</span>
									<span className="font-normal text-muted-foreground">
										05/11/2024
									</span>
								</p>
								<p className="grid">
									<span className="font-semibold">
										PAGAMENTO
									</span>
									<span className="font-normal text-muted-foreground">
										Cartão
									</span>
								</p>
							</div>

							<Separator className="h-0.5 rounded-sm" />

							<div></div>

							<div className="grid gap-3 py-4 text-sm font-semibold text-muted-foreground">
								<Separator className="h-0.5 rounded-sm" />
								<span className="flex justify-between">
									<span>Subtotal</span>
									<span>R$ 1.000,00</span>
								</span>
								<Separator className="h-0.5 rounded-sm" />
								<span className="flex justify-between">
									<span>Entrega</span>
									<span>GRÁTIS</span>
								</span>
								<Separator className="h-0.5 rounded-sm" />
								<span className="flex justify-between">
									<span>Descontos</span>
									<span>- R$ 1.000,00</span>
								</span>
								<Separator className="h-0.5 rounded-sm" />
								<span className="flex justify-between text-lg text-white">
									<span>TOTAL</span>
									<span>R$ 1.000,00</span>
								</span>
							</div>
						</AccordionContent>
					</AccordionItem>
				</Accordion>
			</section>
		</>
	);
}
