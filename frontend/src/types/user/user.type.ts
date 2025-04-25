import { SchoolType } from "../schools/school.type"

export type UserRole = 'STUDENT' | 'ADMIN' | 'PROFESSOR' | 'STAFF'

export type UserType = {
    id: number,
    email: string,
    firstName: string,
    lastName: string,
    contactInfo: string,
    dateOfBirth: string,
    profilePicture: string,
    school: SchoolType,
    major: string,
    role: UserRole
    createdAt: string,
    updatedAt: string,
    showName: boolean,
    showContactInfo: boolean,
    showDateOfBirth: boolean,
    showSchool: boolean,
    showMajor: boolean,
    showProfilePicture: boolean
}
export const ROLES = ['STUDENT', 'ADMIN', 'PROFESSOR', 'STAFF']
