import { api } from "@/lib/api";
import { deleteSession, verifySession } from "@/server/session.server";

export async function GET() {
	const session = await verifySession();

	if (!session?.token) {
		return Response.json({ cliente: null });
	}

	const { data: cliente } = await api.GET("/cliente/me", {
		headers: {
			Authorization: `Bearer ${session.token}`,
		},
	});

	return Response.json({ cliente });
}

export async function DELETE() {
	await deleteSession();
}
