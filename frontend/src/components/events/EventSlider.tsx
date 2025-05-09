'use client'

import 'keen-slider/keen-slider.min.css'
import { useKeenSlider } from 'keen-slider/react'
import { ChevronLeft, ChevronRight } from 'lucide-react'
import { useRef } from 'react'

import { EventType } from '@/types/event/event.type'

type Props = {
	events: EventType[]
}

export default function EventSlider({ events }: Props) {
	const sliderRef = useRef<HTMLDivElement>(null)
	const [sliderInstanceRef, slider] = useKeenSlider<HTMLDivElement>({
		loop: true,
		slides: {
			perView: 1,
			spacing: 15
		}
	})

	return (
		<div className='relative'>
			<div
				ref={sliderInstanceRef}
				className='keen-slider overflow-hidden rounded'
			>
				{events.map(event => (
					<div
						key={event.id}
						className='keen-slider__slide rounded bg-gray-100 py-4 px-20 shadow-md'
					>
						<h3 className='mb-1 text-xl font-semibold'>
							{event.title}
						</h3>
						<p className='mb-2'>{event.description}</p>
						<p className='text-sm text-gray-500'>
							{new Date(event.startDate).toLocaleDateString()} —{' '}
							{new Date(event.endDate).toLocaleDateString()}
						</p>
					</div>
				))}
			</div>

			{/* Navigation Buttons */}
			{events.length > 0 && (
				<>
					<button
						onClick={() => slider.current?.prev()}
						className='absolute top-1/2 left-2 z-10 -translate-y-1/2 rounded-full bg-white p-2 shadow transition hover:bg-gray-100'
					>
						<ChevronLeft className='h-5 w-5' />
					</button>
					<button
						onClick={() => slider.current?.next()}
						className='absolute top-1/2 right-2 z-10 -translate-y-1/2 rounded-full bg-white p-2 shadow transition hover:bg-gray-100'
					>
						<ChevronRight className='h-5 w-5' />
					</button>
				</>
			)}
		</div>
	)
}
