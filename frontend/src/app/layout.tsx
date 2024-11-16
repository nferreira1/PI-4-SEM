import { Toaster } from "@/components/ui/sonner";
import { queryClient } from "@/lib/query";
import { verifySession } from "@/server/session.server";
import { QueryClientProvider } from "@tanstack/react-query";
import type { Metadata } from "next";
import localFont from "next/font/local";
import "./globals.css";
import { Providers } from "./providers";

const geistSans = localFont({
	src: "./fonts/GeistVF.woff",
	variable: "--font-geist-sans",
	weight: "100 900",
});

const geistMono = localFont({
	src: "./fonts/GeistMonoVF.woff",
	variable: "--font-geist-mono",
	weight: "100 900",
});

export const metadata: Metadata = {
	title: "TechCommerce",
	description:
		"Na TechCommerce, somos especialistas em oferecer uma ampla gama de produtos tecnológicos de alta qualidade. Desde mouses, teclados e placas-mãe até outros acessórios essenciais para seu setup, temos tudo o que você precisa para elevar sua experiência digital. Nosso compromisso é proporcionar a melhor tecnologia com os preços mais competitivos e um atendimento que faz a diferença. Explore nosso catálogo e encontre o equipamento perfeito para suas necessidades.",
};

export default async function RootLayout({
	children,
}: Readonly<{
	children: React.ReactNode;
}>) {
	const session = await verifySession();

	return (
		<html lang="pt">
			<body
				className={`${geistSans.variable} ${geistMono.variable} flex min-h-screen flex-col antialiased`}
			>
				<QueryClientProvider client={queryClient}>
					<Providers
						initialAuth={{
							authenticated: !!session?.cliente,
							cliente: session?.cliente || null,
						}}
					>
						{children}
					</Providers>
					<Toaster richColors />
				</QueryClientProvider>
			</body>
		</html>
	);
}
