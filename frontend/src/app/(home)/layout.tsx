import { Footer } from "@/components/custom/server/footer";
import { Header } from "@/components/custom/server/header";

export default async function Layout({
	children,
}: Readonly<{
	children: React.ReactNode;
}>) {
	return (
		<>
			<Header />
			<main className="grow">{children}</main>
			<Footer />
		</>
	);
}
