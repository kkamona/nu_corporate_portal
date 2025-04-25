import EventCreateForm from '@/components/events/EventCreateForm'

const CreateEventPage = () => {
	return (
		<div>
			<h2>Event Create Form</h2>
			<div className='border p-2 rounded-md'>
				<EventCreateForm />
			</div>
		</div>
	)
}

export default CreateEventPage
