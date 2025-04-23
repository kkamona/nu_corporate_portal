'use server'

import { serverFetch } from "@/lib/api"
import { FormState } from "@/types/formState/formState.type"
import { fromErrorToFormState, toFormState } from "@/utils/to-form-state"

export const updateUserPrivacySettings = async (userId: number, formState: FormState, formData: FormData) => {
    try {
        const requestBody = {
            showName: formData.get('showName') === 'on',
            showContactInfo: formData.get('showContactInfo') === 'on',
            showDateOfBirth: formData.get('showDateOfBirth') === 'on',
            showSchool: formData.get('showSchool') === 'on',
            showMajor: formData.get('showMajor') === 'on',
            showProfilePicture: formData.get('showProfilePicture') === 'on'
        }

        // console.log(requestBody)
        const response = await serverFetch(`/users/${userId}`, {
            method: "PATCH",
            body: JSON.stringify(requestBody)
        })
        if (!response.ok) {
            throw new Error("Failed to update privacy settings")
        }
        return toFormState("SUCCESS", "Privacy settings updated")
    } catch (error) {
        return fromErrorToFormState(error)
    }
} 