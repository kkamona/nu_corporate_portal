'use client'

import { Card } from '@/components/ui/card'

type Props = {
	reference: {
		name: string
		description: string
		phone: string
		email?: string
		type: string
	}
}

const ReferenceCard = ({ reference }: Props) => {
	return (
		<Card className='p-4'>
			<h3 className='text-lg font-semibold'>{reference.name}</h3>
			<p className='text-sm'>{reference.description}</p>
			<p className='text-sm'>📞 {reference.phone}</p>
			{reference.email && <p className='text-sm'>📧 {reference.email}</p>}
			<p className='text-muted-foreground mt-1 text-xs'>
				{reference.type}
			</p>
		</Card>
	)
}

export default ReferenceCard
