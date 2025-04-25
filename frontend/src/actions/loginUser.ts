'use server'

import { FormState } from "@/types/formState/formState.type";
import { fromErrorToFormState, toFormState } from "@/utils/to-form-state"
import { cookies } from "next/headers";
import { z } from 'zod'

const loginUserSchema = z.object({
    // email: z.string().email({ message: "Should be email" }),
    // password: z.string().min(4, { message: "Minimum 6 symbols" }),
    email: z.string(),
    password: z.string()

})

const loginUser = async (formState: FormState, formData: FormData) => {
    try {
        const validatedFields = loginUserSchema.parse({
            email: formData.get("email"),
            password: formData.get("password")
        })
        const response = await fetch(`${process.env.INTERNAL_SERVER_URL}/auth/login`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(validatedFields)
        })
        if (!response.ok) {
            throw new Error("Login failed")
        }
        const { token } = await response.json()
        const cookieStore = cookies();
        (await cookieStore).set("auth-token", token, {
            httpOnly: true,
            secure: process.env.NODE_ENV === "production",
            maxAge: 60 * 60, // 1 hour
            path: "/",
            sameSite: "lax",
        })
        return toFormState("SUCCESS", "Login successful")
    } catch (error) {
        return fromErrorToFormState(error)
    }
}

export default loginUser