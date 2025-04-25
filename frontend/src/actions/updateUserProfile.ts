'use server'

import { serverFetch } from "@/lib/api"
import { FormState } from "@/types/formState/formState.type"
import { fromErrorToFormState, toFormState } from "@/utils/to-form-state"

export const updateUserProfile = async (userId: number, formState: FormState, formData: FormData) => {
    try {
        const validatedFields = {
            firstName: formData.get("firstName"),
            lastName: formData.get("lastName"),
            school: formData.get("school"),
            dateOfBirth: formData.get("dateOfBirth"),
            contactInfo: formData.get("contactInfo")
        }
        // console.log(validatedFields)
        const response = await serverFetch(`/users/${userId}`, {
            method: "PATCH",
            body: JSON.stringify(validatedFields)
        })
        if (!response.ok) {
            throw new Error("Failed to update user information")
        }
        return toFormState("SUCCESS", "User information updated")
    } catch (error) {
        return fromErrorToFormState(error)
    }
} 