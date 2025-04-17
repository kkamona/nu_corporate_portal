'use client'

import { useActionState, useEffect, useState } from 'react'

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
import { UserType } from '@/types/user/user.type'

const schools = [
	{ value: 'NUFYP', label: 'Center for Preparatory Study' },
	{ value: 'GSB', label: 'Graduate School of Business' },
	{ value: 'GSE', label: 'Graduate School of Education' },
	{ value: 'GSPP', label: 'Graduate School of Public Policy' },
	{ value: 'SEDS', label: 'School of Engineering and Digital Science' },
	{ value: 'NUSOM', label: 'School of Medicine' },
	{ value: 'SMG', label: 'School of Mining and Geosciences' },
	{ value: 'SSH', label: 'School of Sciences and Humanities' }
]

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
	useToastMessage(formState)
	useEffect(() => {
		if (formState.status === 'SUCCESS') {
			onSuccess?.()
		}
	}, [formState.status])

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
