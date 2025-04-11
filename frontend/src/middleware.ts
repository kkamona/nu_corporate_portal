import { auth } from "@/auth";
import { NextResponse } from "next/server";

const protectedRoutes = ["/dashboard", "/profile"];
const authPageRoutes = ["/login"];
const apiAuthPrefix = "/api/auth";

export default auth(async (req) => {
    const { nextUrl } = req;
    const isLoggedIn = !!req.auth;

    const path = nextUrl.pathname;
    const isApiAuthRoute = nextUrl.pathname.startsWith(apiAuthPrefix);
    const isProtectedRoute = protectedRoutes.includes(path);
    const isAuthPageRoute = authPageRoutes.includes(path);

    if (process.env.NODE_ENV !== 'production') {
      console.log({ auth: req.auth });
    }

    if (isApiAuthRoute) {
        return NextResponse.next();
    }

    if (isProtectedRoute && !isLoggedIn) {
        const returnUrl = encodeURIComponent(nextUrl.pathname);
        return NextResponse.redirect(new URL(`/login?returnUrl=${returnUrl}`, req.nextUrl));
    }

    if (isLoggedIn && isAuthPageRoute) {
        return NextResponse.redirect(new URL("/dashboard", req.nextUrl));
    }

    return NextResponse.next();
});

// Optionally, don't invoke Middleware on some paths
export const config = {
    matcher: ["/((?!api|_next/static|_next/image|favicon.ico).*)"],
};