'use client'

import { useActionState } from 'react'

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
import { Textarea } from '../ui/textarea'

import { MultiSelectMembers } from './MultiSelectMembers'
import { createClub } from '@/actions/createClub'
import { useToastMessage } from '@/hooks/use-toast-message'
import { EMPTY_FORM_STATE } from '@/types/formState/formState.type'
import { UserType } from '@/types/user/user.type'

const ClubCreateForm = ({ students_list }: { students_list: UserType[] }) => {
	const [formState, action] = useActionState(createClub, EMPTY_FORM_STATE)
	useToastMessage(formState)
	return (
		<form action={action}>
			<div className='grid grid-cols-1 gap-6 lg:grid-cols-2'>
				<div className='space-y-4'>
					<div className='flex flex-col gap-2'>
						<Label htmlFor='name'>Name of the club</Label>
						<Input type='text' name='name' id='name' required />
					</div>

					<div className='flex flex-col gap-2'>
						<Label htmlFor='description'>Description</Label>
						<Textarea name='description' id='description' />
					</div>

					<div className='flex flex-col gap-2'>
						<Label htmlFor='location'>Location</Label>
						<Input type='text' name='location' id='location' />
					</div>
					<div className='flex flex-col gap-2'>
						<Label htmlFor='startDate'>Foundation Date*</Label>
						<Input
							type='date'
							name='foundationDate'
							id='foundationDate'
							required
						/>
					</div>
					<div className='flex flex-col gap-2'>
						<Label htmlFor='startDate'>President of the club</Label>
						<Select name='presidentId'>
							<SelectTrigger>
								<SelectValue placeholder='President' />
							</SelectTrigger>
							<SelectContent>
								{students_list &&
									students_list.map(student => (
										<SelectItem
											value={String(student.id)}
											key={student.id}
										>
											{student.firstName}{' '}
											{student.lastName}
										</SelectItem>
									))}
							</SelectContent>
						</Select>
					</div>
					<MultiSelectMembers students={students_list} />
				</div>
			</div>
			<div className='flex justify-end'>
				<SubmitButton label='Create' loading='Creating...' />
			</div>
		</form>
	)
}

export default ClubCreateForm
