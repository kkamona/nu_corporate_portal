'use client'

// Import Button component
import React, { useState } from 'react'

import { Button } from '@/components/ui/button'
import { EventType } from '@/types/event/event.type'

// Define the Event interface
// interface Event {
// 	id: number
// 	title: string
// 	description: string
// 	startDate: string
// 	endDate: string
// 	startTime: string
// 	endTime: string
// 	isPublic: boolean
// 	targetRoles?: string[]
// 	targetSchools?: string[]
// 	location: string // Add location here
// }

interface CalendarWrapperProps {
	events: EventType[] // Array of events
	currentMonth: Date // Current month passed from the parent component
}

const CalendarWrapper: React.FC<CalendarWrapperProps> = ({
	events,
	currentMonth
}) => {
	const [hoveredDay, setHoveredDay] = useState<Date | null>(null) // Track hovered day
	const [clickedEvent, setClickedEvent] = useState<EventType | null>(null) // Track clicked event

	// Function to get all days in the selected month
	const getDaysInMonth = (month: number, year: number): Date[] => {
		const date = new Date(year, month, 1)
		const daysInMonth: Date[] = []

		while (date.getMonth() === month) {
			daysInMonth.push(new Date(date))
			date.setDate(date.getDate() + 1)
		}
		return daysInMonth
	}

	// Function to render events for a given day
	const renderEvents = (day: Date) => {
		const dayEvents = events.filter(
			event =>
				new Date(event.startDate).toDateString() === day.toDateString()
		)
		return dayEvents.map(event => (
			<div key={event.id} className='text-xs text-blue-500'>
				{event.title}
			</div>
		))
	}

	// Function to handle event click and show event details
	const handleEventClick = (event: EventType) => {
		setClickedEvent(event)
	}

	// Get the days of the selected month
	const daysInMonth = getDaysInMonth(
		currentMonth.getMonth(),
		currentMonth.getFullYear()
	)

	// Get the first day of the month to determine which weekday it falls on (0: Sun, 1: Mon, etc.)
	const firstDayOfMonth = new Date(
		currentMonth.getFullYear(),
		currentMonth.getMonth(),
		1
	)
	const startingDay = firstDayOfMonth.getDay() // Get the starting day of the week (0-6)

	// Create an array of empty cells for the leading empty days before the 1st day of the month
	const leadingEmptyDays = Array(
		startingDay === 0 ? 6 : startingDay - 1
	).fill(null) // Start on Monday

	// Combine leading empty days with actual days of the month
	const allDays = [...leadingEmptyDays, ...daysInMonth]

	// Get the current day
	const currentDay = new Date().toDateString() // Get today's date as a string

	// Month name in English
	const monthName = currentMonth.toLocaleString('en-US', {
		month: 'long',
		year: 'numeric'
	})

	// Function to close the event details popup
	const closeEventDetails = () => {
		setClickedEvent(null) // Clear the clicked event
	}

	return (
		<div className='w-full max-w-5xl rounded-xl bg-white p-6 shadow'>
			{/* Centered month name */}
			<h2 className='mb-6 text-center text-2xl font-semibold'>
				{monthName}
			</h2>

			<div className='grid grid-cols-7 gap-2'>
				{/* Weekdays starting from Monday */}
				{['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'].map(
					(day, index) => (
						<div key={index} className='text-center font-bold'>
							{day}
						</div>
					)
				)}

				{/* Render days */}
				{allDays.map((day, index) => (
					<div
						key={index}
						className={`border p-2 ${day instanceof Date && day.toDateString() === currentDay ? 'bg-yellow-200' : ''}`} // Highlight current day
						onMouseEnter={() =>
							setHoveredDay(day instanceof Date ? day : null)
						} // Hover event to show details
						onClick={() => {
							const event =
								day instanceof Date
									? events.find(
											event =>
												new Date(
													event.startDate
												).toDateString() ===
												day.toDateString()
										)
									: null
							if (event) {
								handleEventClick(event) // Pass event only if it exists
							}
						}}
					>
						{day instanceof Date ? (
							<>
								<p>{day.getDate()}</p>
								{renderEvents(day)}{' '}
								{/* Render events for this day */}
							</>
						) : (
							<div className='h-12'></div> // Empty space for leading days
						)}
					</div>
				))}
			</div>

			{/* Event Details Popup on Click */}
			{clickedEvent && (
				<div className='bg-opacity-50 fixed top-0 left-0 flex h-full w-full items-center justify-center bg-gray-700'>
					<div className='w-full max-w-lg rounded-lg bg-white p-6 shadow-xl'>
						<h3 className='font-prata text-primary mb-4 text-2xl'>
							{clickedEvent.title}
						</h3>
						<p className='font-inter mb-2 text-lg'>
							{clickedEvent.description}
						</p>
						<p className='font-inter mb-2 text-lg'>
							<strong>Time:</strong> {clickedEvent.startTime} -{' '}
							{clickedEvent.endTime}
						</p>
						<p className='font-inter mb-2 text-lg'>
							<strong>Date:</strong>{' '}
							{new Date(
								clickedEvent.startDate
							).toLocaleDateString()}{' '}
							-{' '}
							{new Date(
								clickedEvent.endDate
							).toLocaleDateString()}
						</p>
						<p className='font-inter mb-4 text-lg'>
							<strong>Location:</strong> {clickedEvent.location}
						</p>
						<Button
							variant='outline'
							size='lg'
							onClick={closeEventDetails}
						>
							Close
						</Button>
					</div>
				</div>
			)}
		</div>
	)
}

export default CalendarWrapper
