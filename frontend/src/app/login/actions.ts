"use server";

import { api } from "@/lib/api";
import { validateSchema } from "@/lib/validation";
import { createSession } from "@/server/session.server";
import { redirectWithToast } from "@/server/utils.server";
import { z } from "zod";

export async function singin(_previousState: unknown, formData: FormData) {
	const schema = z.object({
		email: z
			.string({
				message: "O e-mail é obrigatório.",
			})
			.email({
				message: "E-mail no formato inválido.",
			}),
		senha: z
			.string({
				message: "A senha é obrigatória.",
			})
			.min(1, "A senha é obrigatória."),
	});

	const data = {
		email: formData.get("email"),
		senha: formData.get("senha"),
	};

	const result = validateSchema(schema, data);

	if (!result.success) {
		return {
			validation: result.error,
			previousState: {
				...data,
			},
		};
	}

	const { error, data: response } = await api.POST("/login", {
		body: {
			email: result.data.email,
			senha: result.data.senha,
		},
	});

	if (error?.status === 401) {
		return {
			validation: {
				email: [error?.message] as string[],
				senha: [error?.message] as string[],
			},
			previousState: {
				...data,
			},
		};
	}

	if (error) {
		return await redirectWithToast("/login", {
			type: "error",
			title: error.message as string,
			description: error.message as string,
		});
	}

	return await createSession(response.token);
}
