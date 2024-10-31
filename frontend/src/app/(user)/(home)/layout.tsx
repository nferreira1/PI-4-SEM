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
			<main className="grow">{children}</main>
			<Footer />
		</>
	);
}
