import { z } from "zod";

export function validateSchema<T extends z.ZodTypeAny>(
	schema: T,
	data: unknown,
) {
	const { success, error, data: result } = schema.safeParse(data);

	if (!success) {
		return {
			success,
			result: null as z.infer<typeof schema>,
			error,
		};
	}

	return {
		success,
		result: result as z.infer<typeof schema>,
		error,
	};
}
