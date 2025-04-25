'use server'

import { serverFetch } from "@/lib/api"
import { FormState } from "@/types/formState/formState.type"
import { fromErrorToFormState, toFormState } from "@/utils/to-form-state"

export const updateUserInterests = async (userId: number, formState: FormState, formData: FormData) => {
    try {
        const validatedFields = {
            interests: formData.get("interests")
        }
        const response = await serverFetch(`/users/${userId}`, {
            method: "PATCH",
            body: JSON.stringify(validatedFields)
        })
        if (!response.ok) {
            throw new Error("Failed to update user interests information")
        }
        return toFormState("SUCCESS", "User interests information updated")
    } catch (error) {
        return fromErrorToFormState(error)
    }
} 