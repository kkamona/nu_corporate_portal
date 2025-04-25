import { FormState } from "@/types/formState/formState.type";
import { fromErrorToFormState, toFormState } from "@/utils/to-form-state";

export const createPost = async (formState: FormState, formData: FormData) => {
    try {
        return toFormState("SUCCESS", "Created")
    } catch (error) {
        return fromErrorToFormState(error)
    }
}