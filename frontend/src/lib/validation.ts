import { z } from "zod";

export function validateSchema<T extends z.ZodTypeAny, U>(schema: T, data: U) {
	const { success, error, data: result } = schema.safeParse(data);

	if (!success) {
		return {
			success,
			data: result as z.infer<typeof schema>,
			error: error.flatten().fieldErrors,
		};
	}

	return {
		success,
		data: result as z.infer<typeof schema>,
		error,
	};
}
