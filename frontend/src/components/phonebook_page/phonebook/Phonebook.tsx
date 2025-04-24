'use client'

import { Search } from 'lucide-react'
import { useRouter } from 'next/navigation'
import { useEffect, useState } from 'react'

import ReferenceCard from '../reference_contacts/ReferenceCard'

import ContactCard from './ContactCard'
import PhonebookFilters from './PhonebookFilters'
import Sidebar from './Sidebar'
import { Card, CardContent } from '@/components/ui/card'
import { Input } from '@/components/ui/input'
import {
	Select,
	SelectContent,
	SelectItem,
	SelectTrigger,
	SelectValue
} from '@/components/ui/select'
import { UserType } from '@/types/user/user.type'

const divisions = ['Faculty', 'Students', 'Admin']
const schools = [
	'Center for Preparatory Studies',
	'SEDS',
	'SSH',
	'GSB',
	'SMG',
	'NUSOM'
]

const Phonebook = () => {
	const router = useRouter()
	const [searchTerm, setSearchTerm] = useState('')
	const [selectedDivision, setSelectedDivision] = useState('')
	const [selectedSchool, setSelectedSchool] = useState('')
	const [contacts, setContacts] = useState<UserType[]>([])
	const [filtered, setFiltered] = useState<UserType[]>([])

	// actual code

	// useEffect(() => {
	// 	const fetchContacts = async () => {
	// 		try {
	// 			const res = await fetch('/api/users')
	// 			const data = await res.json()
	// 			setContacts(data)
	// 			setFiltered(data)
	// 		} catch (err) {
	// 			console.error('Failed to fetch contacts', err)
	// 		}
	// 	}

	// 	fetchContacts()
	// }, [])

	//temporary dummy contacts
	useEffect(() => {
		const dummyContacts: UserType[] = [
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
				showDateOfBirth: false,
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

		setContacts(dummyContacts)
		setFiltered(dummyContacts)
	}, [])

	useEffect(() => {
		const filteredData = contacts.filter(user => {
			const nameMatches = `${user.firstName} ${user.lastName}`
				.toLowerCase()
				.includes(searchTerm.toLowerCase())
			const divisionMatches = selectedDivision
				? user.division === selectedDivision
				: true
			const schoolMatches = selectedSchool
				? user.school === selectedSchool
				: true
			return nameMatches && divisionMatches && schoolMatches
		})

		setFiltered(filteredData)
	}, [searchTerm, selectedDivision, selectedSchool, contacts])

	return (
		<div className='flex'>
			{/* Sidebar */}
			<Sidebar
				selectedSchool={selectedSchool}
				onSelectSchool={setSelectedSchool}
				onReset={() => setSelectedSchool('')}
			/>

			{/* Main Content */}
			<div className='flex-1 space-y-4 p-6'>
				<h2 className='text-2xl font-bold'>Phonebook</h2>

				{/* Filters */}
				<PhonebookFilters
					searchTerm={searchTerm}
					onSearchChange={setSearchTerm}
					selectedDivision={selectedDivision}
					onDivisionChange={setSelectedDivision}
				/>

				<div className='flex justify-end gap-3'>
					{/* Add contact */}
					<button
						onClick={() => router.push('/phonebook/add')}
						className='rounded bg-[#ddaf53] px-4 py-2 font-semibold text-white'
					>
						Add Contact
					</button>

					{/* Reference contacts */}
					<button
						onClick={() => router.push('/phonebook/reference')}
						className='rounded bg-[#ddaf53] px-4 py-2 font-semibold text-white'
					>
						Reference Contacts
					</button>
				</div>

				{/* Results */}
				{filtered.length === 0 ? (
					<p className='text-muted-foreground mt-4'>
						No contacts found.
					</p>
				) : (
					<div className='grid grid-cols-1 gap-4 md:grid-cols-2 lg:grid-cols-3'>
						{filtered.map(user => (
							<ContactCard key={user.id} user={user} />
						))}
					</div>
				)}
			</div>
		</div>
	)
}

export default Phonebook
