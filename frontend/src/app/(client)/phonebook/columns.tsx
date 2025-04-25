'use client'

import { ColumnDef } from '@tanstack/react-table'

import UserListActionsCell from '@/components/userslist_page/UserListActionCell'
import { UserType } from '@/types/user/user.type'

export const columns: ColumnDef<UserType>[] = [
	{
		accessorKey: 'firstName',
		header: 'First name'
	},
	{
		accessorKey: 'lastName',
		header: 'Last name'
	},
	{
		accessorKey: 'contactInfo',
		header: 'Phone number'
	}
]
