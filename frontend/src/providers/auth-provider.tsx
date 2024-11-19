"use client";

import { Loader2 } from "lucide-react";
import { useRouter } from "next/navigation";
import { createContext, useState } from "react";

export const AuthContext = createContext<
	Readonly<{
		authenticated: boolean;
		cliente: Schema["ClienteDTO"] | null;
		logout: () => Promise<void>;
	}>
>({
	authenticated: false,
	cliente: null,
	logout: async () => {},
});

export function AuthProvider({
	children,
	initialAuth,
}: Readonly<{
	children: React.ReactNode;
	initialAuth: {
		authenticated: boolean;
		cliente: Schema["ClienteDTO"] | null;
	};
}>) {
	const [loading, setLoading] = useState<boolean>();
	const router = useRouter();
	const [auth, setAuth] = useState(initialAuth);

	const logout = async () => {
		setLoading(true);
		await fetch("/api/auth", {
			method: "DELETE",
			cache: "no-store",
		});
		setAuth({ authenticated: false, cliente: null });
		router.push("/login");
		setLoading(false);
	};

	return (
		<AuthContext.Provider
			value={{
				...auth,
				logout,
			}}
		>
			{loading && (
				<div
					className="fixed inset-0 z-50 flex items-center justify-center"
					style={{
						backgroundColor: "rgba(0, 0, 0, 0.6)",
					}}
				>
					<Loader2 size={48} className="animate-spin text-primary" />
				</div>
			)}
			{children}
		</AuthContext.Provider>
	);
}
