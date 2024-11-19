import "server-only";

export const client = {
	name: "client_session",
	options: {
		httpOnly: true,
		secure: false,
		sameSite: "lax" as const,
		path: "/",
	},
};

export const admin = {};
