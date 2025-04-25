'use server'

// import { serverFetch } from "@/lib/api"
import { FormState } from "@/types/formState/formState.type"
import { fromErrorToFormState, toFormState } from "@/utils/to-form-state"
import { cookies } from "next/headers"

export const uploadAvatar = async (userId: number, formState: FormState, formData: FormData) => {
    try {

        const token = (await cookies()).get("auth-token")?.value

        const response = await fetch(`${process.env.INTERNAL_SERVER_URL}/users/${userId}/photo`, {
            method: "POST",
            body: formData,
            headers: {
                Authorization: `Bearer ${token}`
            }
        })
        if (!response.ok) {
            throw new Error("Failed to upload avatar image")
        }
        return toFormState("SUCCESS", "Avatar image uploaded")
    } catch (error) {
        return fromErrorToFormState(error)
    }
}
