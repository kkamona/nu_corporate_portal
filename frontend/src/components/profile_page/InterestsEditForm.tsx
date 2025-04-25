'use client'

import { useActionState, useEffect } from 'react'

import SubmitButton from '../form_elements/SubmitButton'
import { Label } from '../ui/label'
import { Textarea } from '../ui/textarea'

import { updateUserInterests } from '@/actions/updateUserInterests'
import { useToastMessage } from '@/hooks/use-toast-message'
import { EMPTY_FORM_STATE } from '@/types/formState/formState.type'
import { UserType } from '@/types/user/user.type'

const InterestsEditForm = ({
	user,
	onSuccess
}: {
	user: UserType
	onSuccess: () => void
}) => {
	const [formState, action] = useActionState(
		updateUserInterests.bind(null, user.id),
		EMPTY_FORM_STATE
	)

	useToastMessage(formState)
	useEffect(() => {
		if (formState.status === 'SUCCESS') {
			onSuccess?.()
		}
	}, [formState.status, onSuccess])

	return (
		<form action={action}>
			<div className='flex flex-col gap-1 p-2'>
				<Label>My interests</Label>
				<Textarea
					name='interests'
					placeholder='I love swimming...'
					defaultValue={user.interests || ''}
				/>
			</div>
			<div className='flex justify-end'>
				<SubmitButton label='Update' loading='Updating...' />
			</div>
		</form>
	)
}

export default InterestsEditForm
