import EventSlider from '@/components/events/EventSlider'
import { serverFetch } from '@/lib/api'
import { EventType } from '@/types/event/event.type'

export default async function HomaPage() {
	const response = await serverFetch(`/events`)
	const events: EventType[] = await response.json()
	console.log(events)
	return (
		<div className=''>
			<h2>News</h2>
			<h2 className='mb-4 text-2xl font-bold'>Upcoming Events</h2>
			<EventSlider events={events} />
		</div>
	)
}
