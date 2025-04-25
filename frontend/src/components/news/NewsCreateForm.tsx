'use client'

import { useActionState, useState } from 'react'

import SubmitButton from '../form_elements/SubmitButton'
import { Input } from '../ui/input'
import { Label } from '../ui/label'
import { Textarea } from '../ui/textarea'

import { createNews } from '@/actions/createNews'
import { useToastMessage } from '@/hooks/use-toast-message'
import { EMPTY_FORM_STATE } from '@/types/formState/formState.type'

const NewsCreateForm = () => {
	const [formState, action] = useActionState(createNews, EMPTY_FORM_STATE)
	useToastMessage(formState)

	return (
		<form action={action} className='space-y-6'>
			<div className='space-y-4'>
				<div className='flex flex-col gap-2'>
					<Label htmlFor='title'>Title</Label>
					<Input type='text' name='title' id='title' required />
				</div>

				<div className='flex flex-col gap-2'>
					<Label htmlFor='text'>Text</Label>
					<Textarea name='text' id='text' />
				</div>
				<div className='flex flex-col gap-2'>
					<Label htmlFor='news_thumbnail'>News Thumbnail</Label>
					<Input
						type='file'
						name='news_thumbnail'
						id='news_thumbnail'
						accept='image/*'
						required
					/>
				</div>

				<div className='flex flex-col gap-2'>
					<Label htmlFor='attachments'>Attachments</Label>
					<Input
						type='file'
						name='attachments'
						id='attachments'
						multiple
					/>
				</div>
			</div>

			<div className='flex justify-end'>
				<SubmitButton label='Create' loading='Creating...' />
			</div>
		</form>
	)
}

export default NewsCreateForm
