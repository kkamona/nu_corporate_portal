'use server'

import { FormState } from "@/types/formState/formState.type";
import { fromErrorToFormState, toFormState } from "@/utils/to-form-state";
import { cookies } from "next/headers";

export const createNews = async (formState: FormState, formData: FormData) => {
    try {

        for (const [key, value] of formData) {
            console.log(`${key}: ${value}`);
        }
        const title = formData.get("title") as string
        const text = formData.get("text") as string
        await formData.delete("title")
        await formData.delete("text")
        const url = new URL(`${process.env.INTERNAL_SERVER_URL}/posts`);
        url.searchParams.append("title", title);
        url.searchParams.append("text", text);
        url.searchParams.append("is_news", "true");
        const token = (await cookies()).get("auth-token")?.value
        const response = await fetch(url.toString(), {
            method: "POST",
            body: formData,
            headers: {
                Authorization: `Bearer ${token}`
            }
        })
        if (!response.ok) {
            throw new Error("News creation failed")
        }
        // console.log(formData)
        return toFormState("SUCCESS", "News created")
    } catch (error) {
        return fromErrorToFormState(error)
    }
}