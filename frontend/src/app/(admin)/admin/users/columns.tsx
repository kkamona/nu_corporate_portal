'use client'

import { ColumnDef } from '@tanstack/react-table'
import { MoreHorizontal } from 'lucide-react'

import { Button } from '@/components/ui/button'
import {
	DropdownMenu,
	DropdownMenuContent,
	DropdownMenuItem,
	DropdownMenuLabel,
	DropdownMenuSeparator,
	DropdownMenuTrigger
} from '@/components/ui/dropdown-menu'
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
		accessorKey: 'year',
		header: 'Year'
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
		cell: ({ row }) => {
			const user = row.original

			return (
				<DropdownMenu>
					<DropdownMenuTrigger asChild>
						<Button variant='ghost' className='h-8 w-8 p-0'>
							<span className='sr-only'>Open menu</span>
							<MoreHorizontal className='h-4 w-4' />
						</Button>
					</DropdownMenuTrigger>
					<DropdownMenuContent align='end'>
						<DropdownMenuLabel>Actions</DropdownMenuLabel>
						<DropdownMenuItem>Apply role</DropdownMenuItem>
						
					</DropdownMenuContent>
				</DropdownMenu>
			)
		}
	}
]
