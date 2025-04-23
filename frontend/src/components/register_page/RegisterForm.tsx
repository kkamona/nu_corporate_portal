'use client'

import Form from 'next/form'
import Image from 'next/image'
import Link from 'next/link'
import { useRouter } from 'next/navigation'
import { useActionState, useEffect } from 'react'

import SubmitButton from '../form_elements/SubmitButton'
import {
	Card,
	CardContent,
	CardDescription,
	CardHeader,
	CardTitle
} from '../ui/card'
import { Input } from '../ui/input'
import { Label } from '../ui/label'

import registerUser from '@/actions/registerUser'
import { useToastMessage } from '@/hooks/use-toast-message'
import { EMPTY_FORM_STATE } from '@/types/formState/formState.type'

const RegisterForm = () => {
	const router = useRouter()
	const [formState, action] = useActionState(registerUser, EMPTY_FORM_STATE)
	useToastMessage(formState)

	useEffect(() => {
		if (formState.status === 'SUCCESS') {
			router.replace('/profile')
		}
	}, [formState.status, router])

	return (
		<Card>
			<CardHeader>
				<CardTitle>
					<div className='relative block h-[40px] w-[200px] overflow-hidden'>
						<Image
							src={'/NU horizontal.png'}
							alt='Logo image'
							fill
							className='object-cover object-[center_50%]'
							sizes='200px'
						/>
					</div>
				</CardTitle>
				<CardDescription className='text-center'>
					Welcome to <br /> NU Corporate Portal
				</CardDescription>
			</CardHeader>
			<CardContent>
				<Form action={action} className='flex flex-col'>
					<div className='flex flex-col gap-1 p-2'>
						<Label>Email</Label>

						<Input
							name='email'
							type='text'
							// type='email'
							placeholder='example@nu.edu.kz'
						/>
					</div>
					<div className='flex flex-col gap-1 p-2'>
						<Label>First Name</Label>
						<Input
							name='firstName'
							type='text'
							placeholder='Alikhan'
						/>
					</div>
					<div className='flex flex-col gap-1 p-2'>
						<Label>Last Name</Label>
						<Input
							name='lastName'
							type='text'
							placeholder='Alikhanov'
						/>
					</div>
					<div className='flex flex-col gap-1 p-2'>
						<Label>Password</Label>
						<Input
							name='password'
							type='password'
							placeholder='*******'
						/>
					</div>
					<SubmitButton label='Register' loading='Resiter...' />
					<div className='mt-4 text-center text-sm'>
						Already have an account?{' '}
						<Link
							href='/login'
							className='underline underline-offset-4'
						>
							Log in
						</Link>
					</div>
				</Form>
			</CardContent>
		</Card>
	)
}

export default RegisterForm
