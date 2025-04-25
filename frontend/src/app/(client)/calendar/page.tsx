import CalendarWrapper from '@/components/calendar/CalendarWrapper'
import { serverFetch } from '@/lib/api'

export default async function HomaPage() {
	const response = await serverFetch('/events')
	const events = await response.json()
	const date = new Date()
	return (
		<div className='flex items-center justify-center'>
			<CalendarWrapper events={events} currentMonth={date} />
		</div>
	)
}
