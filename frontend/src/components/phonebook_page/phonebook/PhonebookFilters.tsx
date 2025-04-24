import { Search } from 'lucide-react'
import { useRouter } from 'next/navigation'

import { Input } from '@/components/ui/input'
import {
	Select,
	SelectContent,
	SelectItem,
	SelectTrigger,
	SelectValue
} from '@/components/ui/select'

const divisions = ['Faculty', 'Student']

const PhonebookFilters = ({
	searchTerm,
	onSearchChange,
	selectedDivision,
	onDivisionChange
}: {
	searchTerm: string
	onSearchChange: (value: string) => void
	selectedDivision: string
	onDivisionChange: (value: string) => void
}) => {
	const router = useRouter()

	return (
		<div className='flex items-center gap-4'>
			<div className='relative w-full max-w-md'>
				<Search className='absolute top-1/2 left-3 -translate-y-1/2 text-gray-400' />
				<Input
					type='text'
					placeholder='Search by name...'
					value={searchTerm}
					onChange={e => onSearchChange(e.target.value)}
					className='pl-10'
				/>
			</div>

			<Select value={selectedDivision} onValueChange={onDivisionChange}>
				<SelectTrigger className='w-[180px]'>
					<SelectValue placeholder='Filter by Division' />
				</SelectTrigger>
				<SelectContent>
					{divisions.map(division => (
						<SelectItem key={division} value={division}>
							{division}
						</SelectItem>
					))}
				</SelectContent>
			</Select>

			<button
				onClick={() => onDivisionChange('')}
				className='text-sm font-medium text-[#ddaf53] hover:underline'
			>
				Reset
			</button>
		</div>
	)
}

export default PhonebookFilters
