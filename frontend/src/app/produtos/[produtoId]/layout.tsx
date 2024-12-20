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
			<main className="flex w-full max-w-screen-xl grow auto-rows-min flex-col md:mx-auto md:grid md:grid-cols-2 md:gap-8 md:px-5 md:py-8 lg:grid-cols-3">
				{children}
			</main>
			<Footer />
		</>
	);
}
