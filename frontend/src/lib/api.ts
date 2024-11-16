import createFetchClient, { Middleware } from "openapi-fetch";
import createClient from "openapi-react-query";
import { paths } from "swagger/v1/api-docs";

export const BASE_URL = process.env.NEXT_PUBLIC_API_URL;

const middleware: Middleware = {
	async onResponse() {},
	async onRequest() {},
};

export const api = createFetchClient<paths>({
	baseUrl: BASE_URL,
});

export const $api = createClient<paths>(api);

api.use(middleware);
