import { z } from "zod";

export function validateSchema(schema: z.ZodSchema, data: unknown) {
	const { success, error, data: result } = schema.safeParse(data);

	if (!success) {
		return {
			success,
			result,
			error,
		};
	}

	return {
		success,
		result,
		error,
	};
}
