import EventsList from '@/components/events/EventList'
import { serverFetch } from '@/lib/api'
import { EventType } from '@/types/event/event.type'

const EventsListPage = async () => {
	const response = await serverFetch('/events', {
		method: 'GET'
	})
	const events: EventType[] = await response.json()
	console.log(events)
	return (
		<div className=''>
			<h2>All events</h2>
			<EventsList eventsList={events} />
		</div>
	)
}

export default EventsListPage
