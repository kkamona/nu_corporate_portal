'use client'

import { ColumnDef } from '@tanstack/react-table'

import UserListActionsCell from '@/components/userslist_page/UserListActionCell'
import { UserType } from '@/types/user/user.type'

export const columns: ColumnDef<UserType>[] = [
	{
		accessorKey: 'email',
		header: 'Email'
	},
	{
		accessorKey: 'firstName',
		header: 'First name'
	},
	{
		accessorKey: 'lastName',
		header: 'Last name'
	},
	{
		accessorKey: 'school',
		header: 'School'
	},
	{
		accessorKey: 'role',
		header: 'Role'
	},
	{
		id: 'actions',
		cell: ({ row }) => <UserListActionsCell user={row.original} />
	}
]
