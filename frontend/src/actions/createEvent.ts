'use server'

import { serverFetch } from "@/lib/api"
import { FormState } from "@/types/formState/formState.type"
import { fromErrorToFormState, toFormState } from "@/utils/to-form-state"
import { cookies } from "next/headers"

export const createEvent = async (formState: FormState, formData: FormData) => {
    try {
        const validatedFields = {
            title: formData.get("title"),
            description: formData.get("description") as string,
            location: formData.get("location") as string,
            startDate: formData.get("startDate") as string,
            endDate: formData.get("endDate") as string,
            isPublic: formData.get("isPublic") === "on",
            targetRoles: formData.getAll("targetRoles[]") as string[],
            targetSchools: formData.getAll("targetSchools[]") as string[],
        }
        // console.log(validatedFields)
        const response = await serverFetch("/events", {
            method: "POST",
            body: JSON.stringify(validatedFields)
        })
        if (!response.ok) {
            throw new Error("Failed to create event");
        }
        return toFormState("SUCCESS", "Event created successfully")
    } catch (error) {
        return fromErrorToFormState(error)
    }
}