'use client'

import { PencilIcon } from 'lucide-react'
import { useRouter } from 'next/navigation'
import { useState } from 'react'

import { Badge } from '../ui/badge'
import { Button } from '../ui/button'
import { Card, CardContent, CardHeader, CardTitle } from '../ui/card'
import {
	Dialog,
	DialogContent,
	DialogDescription,
	DialogHeader,
	DialogTitle,
	DialogTrigger
} from '../ui/dialog'

import InterestsEditForm from './InterestsEditForm'
import { UserType } from '@/types/user/user.type'

const UserInterestsCard = ({ user }: { user: UserType }) => {
	const router = useRouter()
	const [dialogState, setDialogState] = useState(false)
	return (
		<Card>
			<CardHeader>
				<CardTitle className='flex justify-between'>
					<h3>Your interests</h3>
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
								<DialogTitle>
									User interests update form
								</DialogTitle>
								<DialogDescription></DialogDescription>
							</DialogHeader>
							<InterestsEditForm
								user={user}
								onSuccess={() => {
									setDialogState(false)
									router.refresh()
								}}
							/>
						</DialogContent>
					</Dialog>
				</CardTitle>
			</CardHeader>
			<CardContent>
				<div className='flex flex-col gap-4'>
					<p>My interests: {user.interests || 'No information'}</p>
					<div className='flex items-center gap-2'>
						<span>Member of: </span>
						{user.clubs.length > 0
							? user.clubs.map(club => (
									<Badge key={club.id}>{club.name}</Badge>
								))
							: 'Not member of any club'}
					</div>
				</div>
			</CardContent>
		</Card>
	)
}

export default UserInterestsCard
