import { deleteSession } from "@/server/session.server";

export async function DELETE() {
	await deleteSession();
}
