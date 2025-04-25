'use server'

import { FormState } from "@/types/formState/formState.type";
import { fromErrorToFormState, toFormState } from "@/utils/to-form-state"
import { cookies } from "next/headers";

// const registerUserSchema = z.object({
//     email: z.string().email({ message: "Should be email" }),
//     name: z.string().min(1, { message: "Minimum 1 symbol" }),
//     password: z.string().min(6, { message: "Minimum 6 symbols" })
// })

const registerUser = async (formState: FormState, formData: FormData) => {
    try {
        // const validatedFields = registerUserSchema.parse({
        //     email: formData.get("email"),
        //     name: formData.get("name"),
        //     password: formData.get("password")
        // })
        const validatedFields = {
            email: formData.get("email"),
            firstName: formData.get("firstName"),
            lastName: formData.get("lastName"),
            password: formData.get("password"),
            gender: formData.get("gender"),
            role: "STUDENT"
        }
        const response = await fetch(`${process.env.INTERNAL_SERVER_URL}/auth/register`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(validatedFields)
        })
        if (!response.ok) {
            throw new Error("Registration failed")
        }
        const login_response = await fetch(`${process.env.INTERNAL_SERVER_URL}/auth/login`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(validatedFields)
        })
        const { token } = await login_response.json()
        const cookieStore = cookies();
        (await cookieStore).set("auth-token", token, {
            httpOnly: true,
            secure: process.env.NODE_ENV === "production",
            maxAge: 60 * 60, // 1 hour
            path: "/",
            sameSite: "lax",
        })
        return toFormState("SUCCESS", "Successful register")
    } catch (error) {
        return fromErrorToFormState(error)
    }
}

export default registerUser