import { Footer } from "@/components/custom/footer";
import { HeaderSimples } from "@/components/custom/header-simples";

export default function UserLayout({
	children,
}: Readonly<{
	children: React.ReactNode;
}>) {
	return (
		<>
			<HeaderSimples />
			<main className="mx-auto flex w-full max-w-screen-sm grow items-center justify-center px-5 py-6">
				{children}
			</main>
			<Footer />
		</>
	);
}
