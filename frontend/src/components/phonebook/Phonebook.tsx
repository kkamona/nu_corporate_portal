// PhonebookPage.tsx
'use client'

import { useState } from 'react'

import { columns } from './Columns'
import { DataTable } from './Data-table'
import { UserType } from '@/types/user/user.type'


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