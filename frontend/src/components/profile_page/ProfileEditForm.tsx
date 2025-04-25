'use client'

import { useActionState, useCallback, useEffect, useState } from 'react'

import SubmitButton from '../form_elements/SubmitButton'
import { Input } from '../ui/input'
import { Label } from '../ui/label'
import {
	Select,
	SelectContent,
	SelectItem,
	SelectTrigger,
	SelectValue
} from '../ui/select'

import { updateUserProfile } from '@/actions/updateUserProfile'
import { useToastMessage } from '@/hooks/use-toast-message'
import { EMPTY_FORM_STATE } from '@/types/formState/formState.type'
import { SchoolType } from '@/types/schools/school.type'
import { UserType } from '@/types/user/user.type'

const schools: { value: SchoolType; label: string }[] = [
	{ value: 'CPS', label: 'Center for Preparatory Study' },
	{ value: 'GSB', label: 'Graduate School of Business' },
	{ value: 'GSE', label: 'Graduate School of Education' },
	{ value: 'GSPP', label: 'Graduate School of Public Policy' },
	{ value: 'SEDS', label: 'School of Engineering and Digital Science' },
	{ value: 'NUSOM', label: 'School of Medicine' },
	{ value: 'SMG', label: 'School of Mining and Geosciences' },
	{ value: 'SSH', label: 'School of Sciences and Humanities' }
]

const formatPhoneNumber = (value: string) => {
	const digits = value.replace(/\D/g, '').slice(0, 11)
	if (!digits.startsWith('7')) return ''
	const part1 = digits.slice(1, 4)
	const part2 = digits.slice(4, 7)
	const part3 = digits.slice(7, 9)
	const part4 = digits.slice(9, 11)

	let result = '+7'
	if (part1) result += `(${part1}`
	if (part1 && part1.length === 3) result += ')'
	if (part2) result += ` ${part2}`
	if (part3) result += ` ${part3}`
	if (part4) result += ` ${part4}`

	return result
}

const ProfileEditForm = ({
	user,
	onSuccess
}: {
	user: UserType
	onSuccess: () => void
}) => {
	const [formState, action] = useActionState(
		updateUserProfile.bind(null, user.id),
		EMPTY_FORM_STATE
	)
	const [school, setSchool] = useState(user.school || '')
	const [phone, setPhone] = useState(
		formatPhoneNumber(user.contactInfo) || ''
	)
	useToastMessage(formState)
	useEffect(() => {
		if (formState.status === 'SUCCESS') {
			onSuccess?.()
		}
	}, [formState.status, onSuccess])

	const handlePhoneChange = useCallback(
		(e: React.ChangeEvent<HTMLInputElement>) => {
			const digits = e.target.value.replace(/\D/g, '')
			if (digits.length <= 11 && digits.startsWith('7')) {
				setPhone(formatPhoneNumber(digits))
			}
		},
		[]
	)

	return (
		<form action={action}>
			<div className='flex flex-col gap-1 p-2'>
				<Label>First name</Label>
				<Input
					name='firstName'
					type='text'
					placeholder='Alikhan'
					defaultValue={user.firstName || ''}
				/>
			</div>
			<div className='flex flex-col gap-1 p-2'>
				<Label>Second name</Label>
				<Input
					name='lastName'
					type='text'
					placeholder='Alikhanov'
					defaultValue={user.lastName || ''}
				/>
			</div>

			<div className='flex flex-col gap-1 p-2'>
				<Label>Date of Birth</Label>
				<Input
					name='dateOfBirth'
					type='date'
					required
					defaultValue={
						user.dateOfBirth ? user.dateOfBirth.slice(0, 10) : ''
					}
				/>
			</div>
			<div className='flex flex-col gap-1 p-2'>
				<Label>Phone Number</Label>
				<Input
					type='tel'
					required
					value={phone}
					onChange={handlePhoneChange}
					placeholder='+7(708) 104 94 61'
				/>
				{/* Hidden input with raw digits only */}
				<input
					type='hidden'
					name='contactInfo'
					value={phone.replace(/\D/g, '')}
				/>
			</div>
			<div className='flex flex-col gap-1 p-2'>
				<Label>School</Label>
				<Select
					name='school'
					required
					value={school}
					onValueChange={setSchool}
				>
					<SelectTrigger className='w-full'>
						<SelectValue placeholder='School' />
					</SelectTrigger>
					<SelectContent>
						{schools.map(({ value, label }) => (
							<SelectItem key={value} value={value}>
								{label}
							</SelectItem>
						))}
					</SelectContent>
				</Select>
			</div>
			<div className='flex justify-end'>
				<SubmitButton label='Update' loading='Updating...' />
			</div>
		</form>
	)
}

export default ProfileEditForm
