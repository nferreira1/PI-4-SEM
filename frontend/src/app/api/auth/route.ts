import { api } from "@/lib/api";
import { client } from "@/server/cookie.server";
import { deleteSession } from "@/server/session.server";
import { NextRequest } from "next/server";

export const revalidate = 0;

export async function GET(request: NextRequest) {
	const token = request.cookies.get(client.name)?.value as string;

	if (!token) {
		return Response.json({ cliente: null });
	}

	const { data: cliente } = await api.GET("/cliente/me", {
		headers: {
			Authorization: `Bearer ${token}`,
		},
	});

	return Response.json({ cliente });
}

export async function DELETE() {
	await deleteSession();
}
