'use client'

import { ColumnDef } from '@tanstack/react-table'
import clsx from 'clsx'
import { Check, MoreHorizontal } from 'lucide-react'
import { useRouter } from 'next/navigation'
import { useActionState, useEffect, useOptimistic } from 'react'

import { updateUserRole } from '@/actions/updateUserRole'
import { Button } from '@/components/ui/button'
import {
	DropdownMenu,
	DropdownMenuContent,
	DropdownMenuItem,
	DropdownMenuLabel,
	DropdownMenuSeparator,
	DropdownMenuSub,
	DropdownMenuSubContent,
	DropdownMenuSubTrigger,
	DropdownMenuTrigger
} from '@/components/ui/dropdown-menu'
import { useToastMessage } from '@/hooks/use-toast-message'
import { EMPTY_FORM_STATE } from '@/types/formState/formState.type'
import { ROLES, UserRole, UserType } from '@/types/user/user.type'

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
		cell: ({ row }) => {
			const router = useRouter()
			const user = row.original
			const [formState, action, isPending] = useActionState(
				updateUserRole.bind(null, user.id),
				EMPTY_FORM_STATE
			)
			const [optimisticRole, setOptimisticRole] = useOptimistic(user.role)
			useToastMessage(formState)
			useEffect(() => {
				if (formState.status === 'SUCCESS') {
					router.refresh()
				}
			}, [formState.timestamp, router])
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
						<DropdownMenuSub>
							<DropdownMenuSubTrigger>
								Apply role
							</DropdownMenuSubTrigger>
							<DropdownMenuSubContent>
								{ROLES.map(role => {
									const isCurrentRole = user.role === role
									return (
										<form key={role} action={async (formData) => {
											setOptimisticRole(formData.get('role') as UserRole	)
											await action(formData)
										  }}>
											<input
												type='hidden'
												name='role'
												value={role}
											/>
											<DropdownMenuItem
												asChild
												disabled={isCurrentRole}
											>
												<button
													type='submit'
													disabled={isPending || isCurrentRole}
												>
													{role}
													{isCurrentRole && (
														<Check className='ml-2 h-4 w-4' />
													)}
												</button>
											</DropdownMenuItem>
										</form>
									)
								})}
							</DropdownMenuSubContent>
						</DropdownMenuSub>
					</DropdownMenuContent>
				</DropdownMenu>
			)
		}
	}
]
