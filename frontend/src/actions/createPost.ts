import { FormState } from "@/types/formState/formState.type";
import { fromErrorToFormState, toFormState } from "@/utils/to-form-state";

export const createPost = async (formState: FormState, formData: FormData) => {
    try {
        // const validatedFields = {
        //     title: formData.get("title"),
        //     text: formData.get("text"),
        //     attachments: formData.get("attachments") as File[]
        // }
        
        return toFormState("SUCCESS", "Created")
    } catch (error) {
        return fromErrorToFormState(error)
    }
}