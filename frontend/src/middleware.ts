import type { NextRequest } from "next/server";

export function middleware(request: NextRequest) {
	console.log("Request URL:", request.url);
}

export const config = {
	matcher: ["/((?!api|_next/static|_next/image|favicon.ico).*)"],
};
