'use client'

import { useActionState, useState } from 'react'

import SubmitButton from '../form_elements/SubmitButton'
import { Checkbox } from '../ui/checkbox'
import { Input } from '../ui/input'
import { Label } from '../ui/label'
import { Textarea } from '../ui/textarea'

import { createEvent } from '@/actions/createEvent'
import { useToastMessage } from '@/hooks/use-toast-message'
import { EMPTY_FORM_STATE } from '@/types/formState/formState.type'
import { SCHOOLS } from '@/types/schools/school.type'
import { ROLES } from '@/types/user/user.type'

const NewsCreateForm = () => {
	const [formState, action] = useActionState(, EMPTY_FORM_STATE)
	useToastMessage(formState)

	const [selectedRoles, setSelectedRoles] = useState<string[]>([])
	const [selectedSchools, setSelectedSchools] = useState<string[]>([])

	const handleRoleToggle = (role: string) => {
		setSelectedRoles(prev =>
			prev.includes(role) ? prev.filter(r => r !== role) : [...prev, role]
		)
	}

	const handleSchoolToggle = (school: string) => {
		setSelectedSchools(prev =>
			prev.includes(school)
				? prev.filter(s => s !== school)
				: [...prev, school]
		)
	}

	return (
		<form action={action} className='space-y-6'>
			<div className='grid grid-cols-1 gap-6 lg:grid-cols-2'>
				<div className='space-y-4'>
					<div className='flex flex-col gap-2'>
						<Label htmlFor='title'>Title</Label>
						<Input type='text' name='title' id='title' required />
					</div>

					<div className='flex flex-col gap-2'>
						<Label htmlFor='description'>Description</Label>
						<Textarea name='description' id='description' />
					</div>

					<div className='flex flex-col gap-2'>
						<Label htmlFor='location'>Location</Label>
						<Input type='text' name='location' id='location' />
					</div>
				</div>
				<div className='space-y-4'>
					<div className='grid grid-cols-1 gap-4 sm:grid-cols-2'>
						<div className='flex flex-col gap-2'>
							<Label htmlFor='startDate'>Start Date*</Label>
							<Input
								type='date'
								name='startDate'
								id='startDate'
								required
							/>
						</div>
						<div className='flex flex-col gap-2'>
							<Label htmlFor='endDate'>End Date*</Label>
							<Input
								type='date'
								name='endDate'
								id='endDate'
								required
							/>
						</div>
						<div className='flex flex-col gap-2'>
							<Label htmlFor='startTime'>Start Time*</Label>
							<Input
								type='time'
								name='startTime'
								id='startTime'
								required
							/>
						</div>
						<div className='flex flex-col gap-2'>
							<Label htmlFor='endTime'>End Time*</Label>
							<Input
								type='time'
								name='endTime'
								id='endTime'
								required
							/>
						</div>
					</div>

					<div className='flex items-center gap-2'>
						<Checkbox id='isPublic' name='isPublic' />
						<Label htmlFor='isPublic'>Public Event?</Label>
					</div>

					<div className='space-y-2'>
						<Label>Target Roles</Label>
						<div className='flex flex-wrap gap-3'>
							{ROLES.map(role => (
								<div
									key={role}
									className='flex items-center gap-2'
								>
									<Checkbox
										id={`role-${role}`}
										name='targetRoles[]'
										value={role}
										checked={selectedRoles.includes(role)}
										onCheckedChange={() =>
											handleRoleToggle(role)
										}
									/>
									<Label htmlFor={`role-${role}`}>
										{role}
									</Label>
								</div>
							))}
						</div>
					</div>

					<div className='space-y-2'>
						<Label>Target Schools</Label>
						<div className='flex flex-wrap gap-3'>
							{SCHOOLS.map(school => (
								<div
									key={school}
									className='flex items-center gap-2'
								>
									<Checkbox
										id={`school-${school}`}
										name='targetSchools[]'
										value={school}
										checked={selectedSchools.includes(
											school
										)}
										onCheckedChange={() =>
											handleSchoolToggle(school)
										}
									/>
									<Label htmlFor={`school-${school}`}>
										{school}
									</Label>
								</div>
							))}
						</div>
					</div>
				</div>
			</div>
			<div className='flex justify-end'>
				<SubmitButton label='Create' loading='Creating...' />
			</div>
		</form>
	)
}

export default NewsCreateForm
