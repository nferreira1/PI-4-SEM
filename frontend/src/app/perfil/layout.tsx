import { Footer } from "@/components/custom/server/footer";
import { Header } from "@/components/custom/server/header";

export default function Layout({
	children,
}: Readonly<{
	children: React.ReactNode;
}>) {
	return (
		<>
			<Header />
			<main className="mx-auto grid w-full max-w-screen-xl grow gap-4 px-5 py-6 md:grid-cols-2">
				{children}
			</main>
			<Footer />
		</>
	);
}
