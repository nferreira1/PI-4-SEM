import { components } from "swagger/v1/api-docs";

declare global {
	type Schema = components["schemas"];
}

export default {};
