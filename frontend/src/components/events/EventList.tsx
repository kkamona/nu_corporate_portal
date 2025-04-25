import EventCard from './EventCard'
import { EventType } from '@/types/event/event.type'

const EventsList = ({ eventsList }: { eventsList: EventType[] }) => {
	return (
		<>
			{eventsList.map(event => (
				<EventCard event={event} key={event.id} />
			))}
		</>
	)
}

export default EventsList
