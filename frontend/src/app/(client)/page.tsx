import Link from 'next/link'

import EventSlider from '@/components/events/EventSlider'
import NewsSlider from '@/components/news/NewsSlider'
import { serverFetch } from '@/lib/api'
import { EventType } from '@/types/event/event.type'
import { PostType } from '@/types/post/post.type'

export default async function HomaPage() {
	const response = await serverFetch(`/events`)
	const events: EventType[] = await response.json()
	// console.log(events)
	const response_news = await serverFetch('/posts?size=20')
	const news_list: PostType[] = (await response_news.json()).content.filter(
		item => item.news === true
	)
	return (
		<div className='flex flex-col gap-5'>
			<h2 className='mt-5'>News</h2>
			<NewsSlider news={news_list} />

			<div className='mb-4 flex items-end gap-1'>
				<h2 className='text-2xl font-bold'>Upcoming Events</h2>
				<Link href='/calendar'>view all</Link>	
			</div>
			<EventSlider events={events} />
		</div>
	)
}
