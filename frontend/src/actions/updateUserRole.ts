'use server'

import { serverFetch } from "@/lib/api"
import { FormState } from "@/types/formState/formState.type"
import { fromErrorToFormState, toFormState } from "@/utils/to-form-state"

export const updateUserRole = async (userId: number, formState: FormState, formData: FormData) => {
    try {
        const requestBody = {
            role: formData.get('role') as string
        }
        const response = await serverFetch(`/users/${userId}`, {
            method: "PATCH",
            body: JSON.stringify(requestBody)
        })
        if (!response.ok) {
            throw new Error("Failed to update role")
        }
        return toFormState("SUCCESS", "User role updated")
    } catch (error) {
        return fromErrorToFormState(error)
    }
} 