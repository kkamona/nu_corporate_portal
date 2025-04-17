
import { cookies } from "next/headers"

// Base API URL
const API_URL = process.env.SERVER_URL || "http://localhost:8080/api"

// Client-side fetch function that includes the auth token
export async function fetchWithAuth(endpoint: string, options: RequestInit = {}) {
  // For client components, we need to get the token from the cookie in a different way
  // This is a simplified example - you might need to use a cookie library
  const token = document.cookie
    .split("; ")
    .find((row) => row.startsWith("auth-token="))
    ?.split("=")[1]

  const headers = {
    "Content-Type": "application/json",
    ...(token && { Authorization: `Bearer ${token}` }),
    ...options.headers,
  }

  const response = await fetch(`${API_URL}${endpoint}`, {
    ...options,
    headers,
  })

  // Handle token expiration
  if (response.status === 401) {
    // Redirect to login or refresh token
    window.location.href = "/login"
    return Promise.reject(new Error("Session expired"))
  }

  return response
}

// Server-side fetch function that includes the auth token
export async function serverFetch(endpoint: string, options: RequestInit = {}) {
  const cookieStore = cookies()
  const token = (await cookieStore).get("auth-token")?.value

  const headers = {
    "Content-Type": "application/json",
    ...(token && { Authorization: `Bearer ${token}` }),
    ...options.headers,
  }

  return fetch(`${API_URL}${endpoint}`, {
    ...options,
    headers,
  })
}
