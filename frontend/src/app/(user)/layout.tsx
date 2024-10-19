import { Footer } from "@/components/custom/footer";
import { Header } from "@/components/custom/header";

export default function UserLayout({
	children,
}: Readonly<{
	children: React.ReactNode;
}>) {
	return (
		<>
			<Header />
			<main className="mx-auto w-full max-w-screen-xl grow px-5 py-6">
				{children}
			</main>
			<Footer />
		</>
	);
}
