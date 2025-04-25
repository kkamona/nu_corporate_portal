// 'use server'

// import { cookies } from "next/headers"

// // Your API URL
// const API_URL = process.env.SERVER_URL || "http://localhost:8080/api"

// // Function to login user
// export async function login(email: string, password: string) {
//     const response = await fetch(`${API_URL}/auth/login`, {
//         method: "POST",
//         headers: {
//             "Content-Type": "application/json",
//         },
//         body: JSON.stringify({ email, password }),
//     })

//     if (!response.ok) {
//         const error = await response.json()
//         throw new Error(error.message || "Failed to login")
//     }

//     const data = await response.json()

//     // Store the token in an HTTP-only cookie
//     await setAuthCookie(data.accessToken)

//     return data
// }

// // Function to register user
// export async function register(email: string, password: string, name: string) {
//     const response = await fetch(`${API_URL}/auth/register`, {
//         method: "POST",
//         headers: {
//             "Content-Type": "application/json",
//         },
//         body: JSON.stringify({ email, password, name }),
//     })

//     if (!response.ok) {
//         const error = await response.json()
//         throw new Error(error.message || "Failed to register")
//     }

//     const data = await response.json()

//     // Store the token in an HTTP-only cookie
//     await setAuthCookie(data.accessToken)

//     return data
// }

// // Function to set the auth cookie
// export async function setAuthCookie(token: string) {
//     const url = `${process.env.APP_URL}/api/auth/set-cookie`;

//     const response = await fetch(url, {
//         method: "POST",
//         headers: {
//             "Content-Type": "application/json",
//         },
//         body: JSON.stringify({ token }),
//     });

//     if (!response.ok) {
//         throw new Error("Failed to set authentication cookie");
//     }
// }

// // Function to logout
// export async function logout() {
//     const response = await fetch("/api/auth/logout", {
//         method: "POST",
//     })

//     if (!response.ok) {
//         throw new Error("Failed to logout")
//     }
// }

// // Function to get the current user
// export async function getCurrentUser() {
//     // This would be a server-side function that reads the cookie
//     // and makes a request to your backend to get the current user
//     const cookieStore = cookies()
//     const token = (await cookieStore).get("auth-token")?.value

//     if (!token) {
//         return null
//     }

//     try {
//         const response = await fetch(`${API_URL}/auth/me`, {
//             headers: {
//                 Authorization: `Bearer ${token}`,
//             },
//         })

//         if (!response.ok) {
//             return null
//         }

//         return response.json()
//     } catch (error) {
//         return null
//     }
// }


'use server'

import { cookies } from "next/headers"

export async function getCurrentUser() {
    const cookieStore = cookies();
    const token = (await cookieStore).get('auth-token')?.value;
    if (!token) {
        return null
    }
    try {
        const response = await fetch(`${process.env.INTERNAL_SERVER_URL}/users/me`, {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        })

        if (!response.ok) {
            return null
        }

        return response.json()
    } catch (error) {
        return null
    }
}
