"use client";

import { Toast } from "@/server/utils.server";
import { usePathname } from "next/navigation";
import { useEffect } from "react";
import { toast } from "sonner";

export default function ToastProvider({
	children,
}: Readonly<{
	children: React.ReactNode;
}>) {
	const pathname = usePathname();

	useEffect(() => {
		const cookieValue = document.cookie
			.split("; ")
			.find((row) => row.startsWith("toast="))
			?.split("=")[1];

		if (cookieValue) {
			try {
				const toastData: Toast = JSON.parse(
					decodeURIComponent(cookieValue),
				);

				if (toastData.type && toastData.title) {
					toast[toastData.type](toastData.title, {
						description: toastData.description,
					});
				}

				document.cookie = "toast=; Max-Age=0; path=/";
			} catch (error) {
				throw new Error(
					`Erro ao processar o cookie do toast: ${error}`,
				);
			}
		}
	}, [pathname, children]);

	return <>{children}</>;
}
