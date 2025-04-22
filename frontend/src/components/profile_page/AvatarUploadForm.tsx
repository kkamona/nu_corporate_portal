'use client'

import { useActionState, useRef } from 'react'

import { uploadAvatar } from '@/actions/uploadAvatar'
import { useToastMessage } from '@/hooks/use-toast-message'
import { EMPTY_FORM_STATE } from '@/types/formState/formState.type'
import { UserType } from '@/types/user/user.type'

const AvatarUploadForm = ({ user }: { user: UserType }) => {
	const formRef = useRef<HTMLFormElement>(null)
	const [formState, action] = useActionState(
		uploadAvatar.bind(null, user.id),
		EMPTY_FORM_STATE
	)
	useToastMessage(formState)
	return (
		<form ref={formRef} action={action} encType='multipart/form-data'>
			<input
				type='file'
				name='profilePhoto'
				accept='image/*'
				className='absolute inset-0 z-10 cursor-pointer opacity-0'
				onChange={() => {
					formRef.current?.requestSubmit()
				}}
			/>
		</form>
	)
}

export default AvatarUploadForm
