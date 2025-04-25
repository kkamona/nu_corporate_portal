import { columns } from './columns'
import { DataTable } from './data-table'
import { serverFetch } from '@/lib/api'
import { UserType } from '@/types/user/user.type'

const UsersList = async () => {
	const response = await serverFetch('/users', {
		method: 'GET'
	})
	const users: UserType[] = await response.json()
	// console.log(users);
	return (
		<div className=''>
			<h2>Users List</h2>
			<DataTable columns={columns} data={users} />
		</div>
	)
}

export default UsersList
