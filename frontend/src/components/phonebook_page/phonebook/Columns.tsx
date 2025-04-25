// Columns.tsx
import { ColumnDef } from '@tanstack/react-table'

import { UserType } from '@/types/user/user.type'

export const columns: ColumnDef<UserType>[] = [
	{
		id: 'fullName',
		header: 'Name',
		cell: ({ row }) => {
			const { firstName, lastName } = row.original
			return `${firstName} ${lastName}`
		}
	},
	{
		accessorKey: 'email',
		header: 'Email'
	},
	{
		accessorKey: 'phoneNumber',
		header: 'Phone Number'
	},
	{
		id: 'birthday',
		header: 'Birthday',
		cell: ({ row }) => {
			const { dateOfBirth, showDateOfBirth } = row.original
			return showDateOfBirth && dateOfBirth
				? new Date(dateOfBirth).toLocaleDateString()
				: '-'
		}
	}
]
