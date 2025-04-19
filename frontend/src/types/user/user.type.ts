export type UserRole = 'STUDENT' | 'MANAGER' | 'ADMIN'

export type UserType = {
    id: number,
    email: string,
    username: string,
    firstName: string,
    lastName: string,
    contactInfo: string,
    profilePicture: string,
    dateOfBirth: string,
    createdAt: string,
    updatedAt: string,
    year: number,
    school: string,
    major: string,
    role: UserRole
}

export const ROLES = ['STUDENT', 'PROFESSOR', 'STAFF']