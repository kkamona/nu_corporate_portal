'use client'

import { Check, ChevronsUpDown } from 'lucide-react'
import { useState } from 'react'

import { Button } from '../ui/button'
import { Command, CommandGroup, CommandItem } from '../ui/command'
import { Label } from '../ui/label'
import { Popover, PopoverContent, PopoverTrigger } from '../ui/popover'

import { cn } from '@/lib/utils'
import { UserType } from '@/types/user/user.type'

type MultiSelectMembersProps = {
	students: UserType[]
}

export function MultiSelectMembers({ students }: MultiSelectMembersProps) {
	const [open, setOpen] = useState(false)
	const [selectedIds, setSelectedIds] = useState<number[]>([])

	const toggleMember = (id: number) => {
		setSelectedIds(prev =>
			prev.includes(id) ? prev.filter(i => i !== id) : [...prev, id]
		)
	}

	return (
		<>
			{/* Hidden input for form submission */}
			{selectedIds.map(id => (
				<input key={id} type='hidden' name='memberIds' value={id} />
			))}

			<Label className='mb-2'>Club Members</Label>
			<Popover open={open} onOpenChange={setOpen}>
				<PopoverTrigger asChild>
					<Button
						variant='outline'
						role='combobox'
						className='w-full justify-between'
					>
						{selectedIds.length > 0
							? `Selected ${selectedIds.length} member(s)`
							: 'Select members'}
						<ChevronsUpDown className='ml-2 h-4 w-4 shrink-0 opacity-50' />
					</Button>
				</PopoverTrigger>
				<PopoverContent className='max-h-60 w-full overflow-y-auto p-0'>
					<Command>
						<CommandGroup>
							{students.map(student => (
								<CommandItem
									key={student.id}
									onSelect={() => toggleMember(student.id)}
									className='flex items-center gap-2'
								>
									<Check
										className={cn(
											'h-4 w-4',
											selectedIds.includes(student.id)
												? 'opacity-100'
												: 'opacity-0'
										)}
									/>
									{student.firstName} {student.lastName}
								</CommandItem>
							))}
						</CommandGroup>
					</Command>
				</PopoverContent>
			</Popover>
		</>
	)
}
