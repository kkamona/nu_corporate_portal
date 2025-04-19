'use client'

import Form from 'next/form'
import Image from 'next/image'
import Link from 'next/link'
import { useRouter, useSearchParams } from 'next/navigation'
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

import loginUser from '@/actions/loginUser'
import { useToastMessage } from '@/hooks/use-toast-message'
import { EMPTY_FORM_STATE } from '@/types/formState/formState.type'

const LoginForm = () => {
	const searchParams = useSearchParams()
	const nextUrl = searchParams.get('next')
	const router = useRouter()
	const [formState, action] = useActionState(loginUser, EMPTY_FORM_STATE)
	useToastMessage(formState)

	useEffect(() => {
		if (formState.status === 'SUCCESS') {
			if (nextUrl) {
				router.replace(nextUrl)
			} else {
				router.replace('/')
			}
		}
	}, [formState.status, nextUrl, router])

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
							// type='email'
							placeholder='example@nu.edu.kz'
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
					<SubmitButton label='Login' loading='Login...' />
					<div className='mt-4 text-center text-sm'>
						Don&apos;t have an account?{' '}
						<Link
							href='/register'
							className='underline underline-offset-4'
						>
							Sign up
						</Link>
					</div>
				</Form>
			</CardContent>
		</Card>
	)
}

export default LoginForm
