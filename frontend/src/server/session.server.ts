import "server-only";

import { api } from "@/lib/api";
import { decodeJwt } from "jose";
import { cookies } from "next/headers";
import { redirect } from "next/navigation";
import { client } from "./cookie.server";

export async function createSession(token: string) {
	const { set } = await cookies();

	const decodedToken = decodeJwt(token);

	set(client.name, token, {
		...client.options,
		expires: new Date(decodedToken.exp! * 1000),
	});

	return redirect("/");
}

export async function verifySession() {
	const { get } = await cookies();
	const token = get(client.name)?.value;

	if (!token) {
		return;
	}

	try {
		const decodedToken = decodeJwt(token);
		const currentTime = Math.floor(Date.now() / 1000);

		if (decodedToken.exp && decodedToken.exp < currentTime) {
			return;
		}

		const { data, error } = await api.GET("/cliente/me", {
			headers: {
				Authorization: `Bearer ${token}`,
			},
		});

		if (error) {
			return;
		}

		return { cliente: data, token: token };
	} catch {
		return;
	}
}

export async function deleteSession() {
	(await cookies()).delete(client.name);
	return redirect("/login");
}
