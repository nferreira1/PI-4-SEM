import { api } from "@/lib/api";
import { verifySession } from "@/server/session.server";

export async function GET() {
	const session = await verifySession();

	if (!session?.token) {
		return Response.json({ cliente: null });
	}

	const {
		data: carrinho,
		error,
		response,
	} = await api.GET("/carrinho", {
		headers: {
			Authorization: `Bearer ${session.token}`,
		},
	});

	if (error) {
		return new Response(JSON.stringify(error), {
			headers: {
				"Content-Type": "application/json",
			},
			status: response.status,
			statusText: response.statusText,
		});
	}

	return new Response(JSON.stringify(carrinho), {
		headers: {
			"Content-Type": "application/json",
		},
		status: response.status,
		statusText: response.statusText,
	});
}

export async function POST(request: Request) {
	const body = await request.json();
	const session = await verifySession();

	if (!session?.token) {
		return Response.json({ cliente: null });
	}

	const {
		data: carrinho,
		response,
		error,
	} = await api.POST("/carrinho", {
		body: {
			produtoId: body.produtoId,
			quantidade: body.quantidade,
		},
		headers: {
			Authorization: `Bearer ${session.token}`,
		},
	});

	if (error) {
		return new Response(JSON.stringify(error), {
			headers: {
				"Content-Type": "application/json",
			},
			status: response.status,
			statusText: response.statusText,
		});
	}

	return new Response(JSON.stringify(carrinho), {
		headers: {
			"Content-Type": "application/json",
		},
		status: response.status,
		statusText: response.statusText,
	});
}
