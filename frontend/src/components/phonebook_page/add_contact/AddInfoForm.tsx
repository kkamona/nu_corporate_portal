'use client'

import { useRouter } from 'next/navigation'
import { useState } from 'react'

import { Button } from '@/components/ui/button'
import { Input } from '@/components/ui/input'
import {
	Select,
	SelectContent,
	SelectItem,
	SelectTrigger,
	SelectValue
} from '@/components/ui/select'

type ContactType = 'Emergency' | 'Service' | 'Other'

const AddInfoForm = () => {
	const router = useRouter()
	const [name, setName] = useState('')
	const [description, setDescription] = useState('')
	const [phone, setPhone] = useState('')
	const [email, setEmail] = useState('')
	const [type, setType] = useState<ContactType>('Emergency')

	const handleSubmit = (e: React.FormEvent) => {
		e.preventDefault()
		// Placeholder for saving logic
		console.log({ name, description, phone, email, type })
		alert('Contact added (check console)')
	}

	return (
		<form onSubmit={handleSubmit} className='mx-auto max-w-md space-y-4'>
			<button
				onClick={() => router.push('/phonebook')}
				className='mb-4 text-sm font-medium text-[#ddaf53] hover:underline'
			>
				← Back to Phonebook
			</button>
			<h2 className='text-xl font-semibold'>Add New Contact</h2>
			<Input
				placeholder='Name'
				value={name}
				onChange={e => setName(e.target.value)}
				required
			/>
			<Input
				placeholder='Description (e.g. Campus Security)'
				value={description}
				onChange={e => setDescription(e.target.value)}
				required
			/>
			<Input
				placeholder='Phone Number'
				value={phone}
				onChange={e => setPhone(e.target.value)}
				required
			/>
			<Input
				placeholder='Email (optional)'
				value={email}
				onChange={e => setEmail(e.target.value)}
			/>
			<Select
				value={type}
				onValueChange={value => setType(value as ContactType)}
			>
				<SelectTrigger>
					<SelectValue placeholder='Select Type' />
				</SelectTrigger>
				<SelectContent>
					<SelectItem value='Emergency'>Emergency</SelectItem>
					<SelectItem value='Service'>Service</SelectItem>
					<SelectItem value='Other'>Other</SelectItem>
				</SelectContent>
			</Select>

			<Button type='submit' className='bg-[#ddaf53] text-white'>
				Add Contact
			</Button>
		</form>
	)
}

export default AddInfoForm
