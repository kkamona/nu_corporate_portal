import { School } from "../schools/school.type"
import { UserRole } from "../user/user.type"

export type EventType = {
    id: number
    title: string
    description: string
    location: string
    startDate: string
    endDate: string
    isPublic: boolean
    targetRoles: UserRole[]
    targetSchools: School[]
}