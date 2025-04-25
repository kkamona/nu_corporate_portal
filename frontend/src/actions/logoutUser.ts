import { cookies } from "next/headers"
import { redirect } from "next/navigation"

export const logoutUser = async () => {
    try {
        (await cookies()).delete("auth-token")
    } catch (error) {
        console.error(error)
    }
    redirect("/login");
}