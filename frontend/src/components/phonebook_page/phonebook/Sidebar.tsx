'use client'

import React from 'react'

const schools = [
	'Center for Preparatory Studies',
	'SEDS',
	'SSH',
	'GSB',
	'SMG',
	'NUSOM',
	'GSE',
	'GSPP'
]

type SidebarProps = {
	selectedSchool: string
	onSelectSchool: (school: string) => void
	onReset: () => void
}

const Sidebar = ({ selectedSchool, onSelectSchool, onReset }: SidebarProps) => {
	return (
		<aside className='min-w-[250px] rounded-lg bg-[#ddaf53] p-4 text-white'>
			<h2 className='mb-4 text-lg font-semibold'>Schools</h2>
			<ul className='space-y-2'>
				{schools.map(school => (
					<li key={school}>
						<button
							onClick={() => onSelectSchool(school)}
							className={`w-full rounded px-3 py-2 text-left ${
								selectedSchool === school
									? 'bg-white text-[#ddaf53]'
									: 'hover:bg-[#c49e47]'
							}`}
						>
							{school}
						</button>
					</li>
				))}
			</ul>

			<button
				onClick={onReset}
				className='mt-4 rounded bg-white px-3 py-1 text-sm font-semibold text-[#ddaf53] hover:bg-[#c49e47] hover:text-white'
			>
				Reset
			</button>
		</aside>
	)
}

export default Sidebar
