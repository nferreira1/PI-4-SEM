import createClient from "openapi-fetch";
import { paths } from "swagger/v1/api-docs";

export const BASE_URL = process.env.API_URL;

export const api = createClient<paths>({
	baseUrl: BASE_URL,
});
