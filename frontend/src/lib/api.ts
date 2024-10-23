import createClient, { Middleware } from "openapi-fetch";
import { paths } from "swagger/v1/api-docs";

export const BASE_URL = process.env.API_URL;

const middleware: Middleware = {
	async onResponse({ response }) {
		if (!response.ok) {
			throw new Error(await response.text());
		}
	},
};

export const api = createClient<paths>({
	baseUrl: BASE_URL,
});

api.use(middleware);
