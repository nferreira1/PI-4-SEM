import { z } from "zod";

export function validateSchema<T extends z.ZodTypeAny>(
	schema: T,
	data: unknown,
) {
	const { success, error, data: result } = schema.safeParse(data);

	if (!success) {
		throw new Error("Ocorreu um erro ao validar o schema.");
	}

	return {
		success,
		result: result as z.infer<typeof schema>,
		error,
	};
}
