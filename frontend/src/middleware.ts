import { NextResponse } from "next/server"
import type { NextRequest } from "next/server"

// Define which routes require authentication
// const protectedRoutes = ["/admin", "/profile", "/settings"]
const protectedRoutes = ["/", "/profile", "/admin"]


export function middleware(request: NextRequest) {
  const token = request.cookies.get("auth-token")?.value
  const { pathname } = request.nextUrl

  // Check if the route requires authentication
  const isProtectedRoute = protectedRoutes.some((route) => pathname === route || pathname.startsWith(`${route}/`))

  // If it's a protected route and there's no token, redirect to login
  if (isProtectedRoute && !token) {
    const url = new URL("/login", request.url)
    // Add the current path as a "next" parameter to redirect after login
    url.searchParams.set("next", pathname)
    return NextResponse.redirect(url)
  }

  // If it's the login page and there's a token, redirect to dashboard
  if ((pathname === "/login" || pathname === "/register") && token) {
    return NextResponse.redirect(new URL("/", request.url))
  }

  return NextResponse.next()
}

export const config = {
  matcher: [
    "/((?!_next/static|_next/image|favicon.ico|public).*)",
  ],
}
