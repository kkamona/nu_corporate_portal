"use server"

import { serverFetch } from "@/lib/api"
import { FormState } from "@/types/formState/formState.type"
import { fromErrorToFormState, toFormState } from "@/utils/to-form-state"

export const createClub = async (formState: FormState, formData: FormData) => {
    try {
        const validatedFields = {
            name: formData.get("name"),
            description: formData.get("description") as string,
            location: formData.get("location") as string,
            foundationDate: formData.get("foundationDate") as string,
            presidentId: Number(formData.get("presidentId")),
            showMembers: true,
            memberIds: formData.getAll("memberIds").map(id => Number(id)),
        }
        // console.log(validatedFields)
        const response = await serverFetch("/clubs", {
            method: "POST",
            body: JSON.stringify(validatedFields)
        })
        return toFormState("SUCCESS", "Club created")
    } catch (error) {
        return fromErrorToFormState(error)
    }
}