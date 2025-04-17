'use client'

import { PencilIcon, UploadIcon } from 'lucide-react'
import { useRouter } from 'next/navigation'
import { useState } from 'react'

import { Button } from '../ui/button'
import {
	Card,
	CardContent,
	CardDescription,
	CardHeader,
	CardTitle
} from '../ui/card'
import {
	Dialog,
	DialogContent,
	DialogDescription,
	DialogHeader,
	DialogTitle,
	DialogTrigger
} from '../ui/dialog'

import ProfileEditForm from './ProfileEditForm'
import { UserType } from '@/types/user/user.type'

const UserCard = ({ user }: { user: UserType }) => {
	const [dialogState, setDialogState] = useState(false)
	const router = useRouter()
	return (
		<Card>
			<CardHeader>
				<CardTitle className='flex justify-between'>
					<h3>Your profile</h3>
					<Dialog open={dialogState} onOpenChange={setDialogState}>
						<DialogTrigger
							asChild
							onClick={() => setDialogState(true)}
						>
							<Button size='icon' className='cursor-pointer'>
								<PencilIcon className='size-6' />
							</Button>
						</DialogTrigger>
						<DialogContent>
							<DialogHeader>
								<DialogTitle>Profile Edit Form</DialogTitle>
								<DialogDescription></DialogDescription>
							</DialogHeader>
							<ProfileEditForm
								user={user}
								onSuccess={() => {
									setDialogState(false)
									router.refresh()
								}}
							/>
						</DialogContent>
					</Dialog>
				</CardTitle>
				<CardDescription></CardDescription>
			</CardHeader>
			<CardContent>
				<div className='flex gap-4'>
					<div className='group relative h-24 w-24'>
						<img
							className='h-24 w-24 object-cover'
							src={
								user.profilePicture
									? user.profilePicture
									: '/no-profile.png'
							}
							alt='User profile'
						/>
						<div className='absolute inset-0 flex cursor-pointer items-center justify-center rounded-full bg-black/40 opacity-0 transition-opacity group-hover:opacity-100'>
							<UploadIcon className='h-6 w-6 text-white' />
						</div>
						<input
							type='file'
							accept='image/*'
							className='absolute inset-0 cursor-pointer opacity-0'
							onChange={e => {
								// handle file upload here
								console.log(e.target.files?.[0])
							}}
						/>
					</div>
					<div>
						<div>{`Full name: ${user.firstName} ${user.lastName}`}</div>
						<div>{`Email: ${user.email}`}</div>
						<div>{`School ${user.school}`}</div>
						<div>{`Date of birth: ${user.dateOfBirth}`}</div>
					</div>
				</div>
			</CardContent>
		</Card>
	)
}

export default UserCard
