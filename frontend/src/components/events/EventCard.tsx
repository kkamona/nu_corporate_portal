import { Badge } from '../ui/badge'
import {
	Card,
	CardContent,
	CardDescription,
	CardHeader,
	CardTitle
} from '../ui/card'

import { EventType } from '@/types/event/event.type'

const EventCard = ({ event }: { event: EventType }) => {
	return (
		<Card>
			<CardHeader>
				<CardTitle>{event.title}</CardTitle>
				<CardDescription>{event.description}</CardDescription>
			</CardHeader>
			<CardContent>
				<div>Location: {event.location}</div>
				<div>
					Duration: {event.startDate} - {event.endDate}
				</div>
				<div>
					<span>Visibility:</span>{' '}
					{event.isPublic ? 'Public' : 'Private'}
				</div>
				<div>
					<span>Target Roles:</span>{' '}
					{event.targetRoles.map(role => (
						<Badge key={role} className='mr-1'>
							{role}
						</Badge>
					))}
				</div>
				<div>
					<span>Target Schools:</span>{' '}
					{event.targetSchools.map(school => (
						<Badge key={school} className='mr-1'>
							{school}
						</Badge>
					))}
				</div>
			</CardContent>
		</Card>
	)
}

export default EventCard
