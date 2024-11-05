"use server";

import { cookies } from "next/headers";
import { redirect } from "next/navigation";
import { z } from "zod";

const toastSchema = z.object({
	type: z.enum(["success", "info", "warning", "error"]),
	title: z.string(),
	description: z.string().optional(),
});

export type Toast = z.infer<typeof toastSchema>;

export async function redirectWithToast(path: string, toast: Toast) {
	const cookieStore = await cookies();
	cookieStore.set("toast", JSON.stringify(toastSchema.parse(toast)), {
		httpOnly: false,
		path: "/",
		maxAge: 10,
	});

	return redirect(path);
}
