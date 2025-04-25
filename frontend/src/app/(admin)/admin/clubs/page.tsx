import { columns } from './columns'
import { DataTable } from './data-table'
import { serverFetch } from '@/lib/api'

const ClubsListPage = async () => {
	const response = await serverFetch('/clubs')
	const clubs = await response.json()
	console.log(clubs)
	return (
		<div>
			<h2>All clubs</h2>
			<DataTable columns={columns} data={clubs} />
		</div>
	)
}

export default ClubsListPage
