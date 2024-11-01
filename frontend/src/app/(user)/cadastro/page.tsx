import Link from "next/link";
import { Form } from "./components/form";

export default async function Page() {
	return (
		<div className="grid w-full gap-6">
			<Form />

			<div className="grid w-full place-items-center gap-y-4">
				<span className="text-center">
					Já possui cadastro?{" "}
					<Link
						className="font-bold text-primary underline"
						href="/login"
					>
						Fazer login
					</Link>
				</span>
			</div>
		</div>
	);
}
