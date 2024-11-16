import { Footer } from "@/components/custom/server/footer";
import { Header } from "@/components/custom/server/header";

export default function Layout({ children }: { children: React.ReactNode }) {
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
