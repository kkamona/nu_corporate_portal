// PhonebookPage.tsx
'use client'

import { useState } from 'react'

import { columns } from './Columns'
import { DataTable } from './Data-table'
import { UserType } from '@/types/user/user.type'

// PhonebookPage.tsx

// PhonebookPage.tsx

const dummyUsers: UserType[] = [
	{
		id: 1,
		email: 'john.doe@nu.edu.kz',
		firstName: 'John',
		lastName: 'Doe',
		contactInfo: 'SEDS Building, Room 101',
		dateOfBirth: '1990-01-01',
		profilePicture: '/dummy1.jpg',
		school: 'SEDS',
		major: 'Computer Science',
		role: 'PROFESSOR',
		createdAt: '2024-04-01T12:00:00Z',
		updatedAt: '2024-05-01T08:30:00Z',
		showName: true,
		showContactInfo: true,
		showDateOfBirth: true,
		showSchool: true,
		showMajor: true,
		showProfilePicture: true,
		division: 'Faculty',
		phoneNumber: '+7 (777) 123-4567'
	},
	{
		id: 2,
		email: 'jane.smith@nu.edu.kz',
		firstName: 'Jane',
		lastName: 'Smith',
		contactInfo: 'Dorm A, Room 303',
		dateOfBirth: '2002-05-10',
		profilePicture: '/dummy2.jpg',
		school: 'SSH',
		major: 'Sociology',
		role: 'STUDENT',
		createdAt: '2024-04-01T12:00:00Z',
		updatedAt: '2024-05-01T08:30:00Z',
		showName: true,
		showContactInfo: true,
		showDateOfBirth: true,
		showSchool: true,
		showMajor: true,
		showProfilePicture: true,
		division: 'Student',
		phoneNumber: '+7 (701) 987-6543'
	},
	{
		id: 3,
		email: 'alice.brown@nu.edu.kz',
		firstName: 'Alice',
		lastName: 'Brown',
		contactInfo: 'Main Office',
		dateOfBirth: '1985-03-15',
		profilePicture: '/no-profile.png',
		school: 'GSB',
		major: 'Business Administration',
		role: 'ADMIN',
		createdAt: '2024-04-01T12:00:00Z',
		updatedAt: '2024-05-01T08:30:00Z',
		showName: true,
		showContactInfo: true,
		showDateOfBirth: true,
		showSchool: true,
		showMajor: true,
		showProfilePicture: true,
		division: 'Faculty',
		phoneNumber: '+7 (700) 654-3210'
	},
	{
		id: 4,
		email: 'ningning.isthemaknae@nu.edu.kz',
		firstName: 'NingNing',
		lastName: 'Maknae',
		contactInfo: 'Dorm A',
		dateOfBirth: '1985-03-15',
		profilePicture: '/dummy3.jpg',
		school: 'NUSOM',
		major: 'Business Administration',
		role: 'STUDENT',
		createdAt: '2024-04-01T12:00:00Z',
		updatedAt: '2024-05-01T08:30:00Z',
		showName: true,
		showContactInfo: true,
		showDateOfBirth: true,
		showSchool: true,
		showMajor: true,
		showProfilePicture: true,
		division: 'Student',
		phoneNumber: '+7 (700) 213-4256'
	}
]

const PhonebookPage = () => {
	const [searchQuery, setSearchQuery] = useState('')

	const filteredUsers = dummyUsers.filter(user => {
		const fullName = `${user.firstName} ${user.lastName}`.toLowerCase()
		return fullName.includes(searchQuery.toLowerCase())
	})

	return (
		<div className='space-y-4 p-6'>
			<h2 className='text-2xl font-bold'>Phonebook</h2>

			<input
				type='text'
				placeholder='Search by name...'
				value={searchQuery}
				onChange={e => setSearchQuery(e.target.value)}
				className='w-full max-w-sm rounded-md border border-gray-300 p-2 focus:ring-2 focus:ring-blue-500 focus:outline-none'
			/>

			<DataTable columns={columns} data={filteredUsers} />
		</div>
	)
}

export default PhonebookPage
