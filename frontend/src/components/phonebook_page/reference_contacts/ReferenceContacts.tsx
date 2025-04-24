'use client'

import { useRouter } from 'next/navigation'

import ReferenceCard from './ReferenceCard'

type ReferenceContact = {
	id: number
	name: string
	description: string // e.g., "Campus Security"
	phone: string
	email?: string
	type: 'Emergency' | 'Service' | 'Other'
}

const ReferenceContacts = () => {
	const router = useRouter()
	const referenceContacts: ReferenceContact[] = [
		{
			id: 1,
			name: 'Campus Security',
			description: 'Emergency response and campus safety',
			phone: '+777 000 0000',
			email: 'security@nu.edu.kz',
			type: 'Emergency'
		},
		{
			id: 2,
			name: 'IT Help Desk',
			description: 'Technical support for students and staff',
			phone: '+777 111 1111',
			email: 'helpdesk@nu.edu.kz',
			type: 'Service'
		}
	]

	return (
		<div className='p-6'>
			<button
				onClick={() => router.push('/phonebook')}
				className='mb-4 text-sm font-medium text-[#ddaf53] hover:underline'
			>
				← Back to Phonebook
			</button>
			<h2 className='mb-4 text-2xl font-bold'>Reference Contacts</h2>
			<div className='grid grid-cols-1 gap-4 md:grid-cols-2 lg:grid-cols-3'>
				{referenceContacts.map(item => (
					<ReferenceCard key={item.id} reference={item} />
				))}
			</div>
		</div>
	)
}

export default ReferenceContacts
