'use client'

import 'keen-slider/keen-slider.min.css'
import { useKeenSlider } from 'keen-slider/react'

import { EventType } from '@/types/event/event.type'

type Props = {
	events: EventType[]
}

export default function EventSlider({ events }: Props) {
	const [sliderRef] = useKeenSlider<HTMLDivElement>({
		loop: true,
		slides: {
			perView: 1,
			spacing: 15
		}
	})

	return (
		<div ref={sliderRef} className='keen-slider'>
			{events.map(event => (
				<div
					key={event.id}
					className='keen-slider__slide rounded bg-gray-100 p-4 shadow-md'
				>
					<h3 className='text-xl font-semibold'>{event.title}</h3>
					<p>{event.description}</p>
					<p className='text-sm text-gray-500'>
						{new Date(event.startDate).toLocaleDateString()} —{' '}
						{new Date(event.endDate).toLocaleDateString()}
					</p>
				</div>
			))}
		</div>
	)
}
