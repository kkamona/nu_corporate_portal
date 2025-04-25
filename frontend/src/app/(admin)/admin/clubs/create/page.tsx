import ClubCreateForm from '@/components/clubs/ClubCreateForm'
import { serverFetch } from '@/lib/api'

const ClubCreatePage = async () => {
	const response = await serverFetch('/users')
	const students_list = await response.json()
	return (
		<div className=''>
			<h2>Club Create Form</h2>
			<div className='rounded-md border p-2'>
				<ClubCreateForm students_list={students_list} />
			</div>
		</div>
	)
}

export default ClubCreatePage
