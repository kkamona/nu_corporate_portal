import { columns } from './columns'
import { DataTable } from './data-table'
import { serverFetch } from '@/lib/api'
import { UserType } from '@/types/user/user.type'

const PhoneBookPage = async () => {
	const response = await serverFetch('/phonebook')
	const phonebook: UserType[] = (await response.json()).content
	console.log(phonebook)
	return (
		<div className=''>
			<DataTable data={phonebook} columns={columns} />
		</div>
	)
}

export default PhoneBookPage
