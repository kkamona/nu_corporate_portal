'use client'

import Image from 'next/image'

import { Card } from '@/components/ui/card'
import { UserType } from '@/types/user/user.type'

const ContactCard = ({ user }: { user: UserType }) => {
	return (
		<Card className='flex items-center gap-6 rounded-lg p-4 shadow-sm'>
			{/* Profile Picture (Left Side) */}
			<div className='relative h-24 w-24 flex-shrink-0 overflow-hidden rounded-full border border-gray-300'>
				<Image
					src={user.profilePicture || '/no-profile.png'}
					alt={`${user.firstName} ${user.lastName}`}
					fill
					className='object-cover'
				/>
			</div>

			{/* Contact Details (Right Side) */}
			<div className='flex flex-col justify-center text-left'>
				<p className='text-lg font-semibold'>
					{user.firstName} {user.lastName}
				</p>
				<p className='text-muted-foreground text-sm'> {user.email}</p>
				<p className='text-muted-foreground text-sm'>
					{user.phoneNumber}
				</p>
				<p className='text-muted-foreground text-sm'>{user.school}</p>
				<p className='text-muted-foreground text-sm'>{user.division}</p>
			</div>
		</Card>
	)
}

export default ContactCard
