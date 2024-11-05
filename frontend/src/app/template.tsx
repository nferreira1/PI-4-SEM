"use client";

import { Toast } from "@/server/utils.server";
import Cookies from "js-cookie";
import { useEffect } from "react";
import { toast } from "sonner";

export default function Template({
	children,
}: Readonly<{
	children: React.ReactNode;
}>) {
	useEffect(() => {
		const cookie = Cookies.get("toast");
		if (cookie) {
			const toastCookie = JSON.parse(cookie) as unknown as Toast;
			toast[toastCookie.type](toastCookie.title, {
				description: toastCookie.description,
			});
			Cookies.remove("toast", { path: "/" });
		}
	}, []);

	return children;
}
