import { NextResponse, type NextRequest } from "next/server";
import { verifySession } from "./server/session.server";

export async function middleware(request: NextRequest) {
	const currentPath = request.nextUrl.pathname;

	const protectedRoutes = ["/meus-pedidos"];
	const isProtectedRoute = protectedRoutes.includes(currentPath);

	const signinAndSignupRoutes = ["/login", "/cadastro"];
	const isSigninOrSignupRoute = signinAndSignupRoutes.includes(currentPath);

	const session = await verifySession();

	if (session?.token?.sub && isSigninOrSignupRoute) {
		return NextResponse.redirect(new URL("/", request.nextUrl.origin));
	}

	if (!session?.token?.sub && isProtectedRoute) {
		return NextResponse.redirect(new URL("/login", request.nextUrl.origin));
	}

	return NextResponse.next();
}

export const config = {
	matcher: ["/((?!api|_next/static|_next/image|favicon.ico).*)"],
};
