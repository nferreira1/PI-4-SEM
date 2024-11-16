"use client";

import { AuthProvider } from "@/providers/auth-provider";
import { CarrinhoProvider } from "@/providers/carrinho-provider";
import ToastProvider from "@/providers/toast-provider";
import { ThemeProvider } from "next-themes";

export function Providers({
	children,
	initialAuth,
}: Readonly<{
	children: React.ReactNode;
	initialAuth: {
		authenticated: boolean;
		cliente: Schema["ClienteDTO"] | null;
	};
}>) {
	return (
		<ThemeProvider
			attribute="class"
			defaultTheme="dark"
			enableSystem
			disableTransitionOnChange
		>
			<ToastProvider>
				<AuthProvider initialAuth={initialAuth}>
					<CarrinhoProvider>{children}</CarrinhoProvider>
				</AuthProvider>
			</ToastProvider>
		</ThemeProvider>
	);
}
