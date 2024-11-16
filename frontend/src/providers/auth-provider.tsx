"use client";

import { useRouter } from "next/navigation";
import { createContext, useState } from "react";

export const AuthContext = createContext<{
	authenticated: boolean;
	cliente: Schema["ClienteDTO"] | null;
	logout: () => Promise<void>;
}>({
	authenticated: false,
	cliente: null,
	logout: async () => {},
});

export function AuthProvider({
	children,
	initialAuth,
}: {
	children: React.ReactNode;
	initialAuth: {
		authenticated: boolean;
		cliente: Schema["ClienteDTO"] | null;
	};
}) {
	const router = useRouter();
	const [auth, setAuth] = useState(initialAuth);

	const logout = async () => {
		await fetch("/api/auth", {
			method: "DELETE",
			cache: "no-store",
		});
		setAuth({ authenticated: false, cliente: null });
		router.push("/login");
	};

	return (
		<AuthContext.Provider
			value={{
				...auth,
				logout,
			}}
		>
			{children}
		</AuthContext.Provider>
	);
}
