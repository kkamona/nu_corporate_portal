'use client'

import 'keen-slider/keen-slider.min.css'
import { useKeenSlider } from 'keen-slider/react'
import { ChevronLeft, ChevronRight } from 'lucide-react'
import { useRef } from 'react'

import { PostType } from '@/types/post/post.type'

type Props = {
	news: PostType[]
}

export default function NewsSlider({ news }: Props) {
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
				{news.map(news => (
					<div
						key={news.id}
						className='keen-slider__slide bg-gray-100 p-4 shadow-md'
					>
						{news.newsThumbnail && (
							<img
								src={`${news.newsThumbnail}?sp=rl&st=2025-04-19T12:00:17Z&se=2025-09-16T20:00:17Z&spr=https&sv=2024-11-04&sr=c&sig=KYbm3kp%2FOaCpvDyzu6Hvh8OBkfb%2Fm04JESmEMqt9y3Q%3D`}
								alt='News photo'
								className='mb-4 h-64 w-full rounded object-cover'
							/>
						)}
						<h3 className='mb-1 text-xl font-semibold'>
							{news.title}
						</h3>
						<p className='text-gray-700'>{news.text}</p>
					</div>
				))}
			</div>

			{/* Navigation Buttons */}
			{news.length > 0 && (
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
