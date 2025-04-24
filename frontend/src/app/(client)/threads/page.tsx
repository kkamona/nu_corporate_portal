// 'use client'

import { serverFetch } from "@/lib/api"

// import { useEffect, useRef, useState } from 'react'

// import PostCard from '@/components/threads_page/PostCard'
// import { PostResponseType, PostType } from '@/types/post/post.type'

// const PAGE_SIZE = 10

// const fetchPosts = async (page = 0, size = 10): Promise<PostResponseType> => {
// 	const res = await fetch(
// 		`${process.env.INTERNAL_SERVER_URL}/posts?page=${page}&size=${size}`
// 	)
// 	if (!res.ok) throw new Error('Failed to fetch')
// 	return res.json()
// }

// export default function PostsPage() {
// 	const [posts, setPosts] = useState<PostType[]>([])
// 	const [page, setPage] = useState(0)
// 	const [hasMore, setHasMore] = useState(true)
// 	const loaderRef = useRef(null)

// 	useEffect(() => {
// 		const load = async () => {
// 			try {
// 				const res = await fetchPosts(page, PAGE_SIZE)
// 				setPosts(prev => [...prev, ...res.content])
// 				setHasMore(!res.last)
// 			} catch (err) {
// 				console.error('Failed to fetch:', err)
// 			}
// 		}
// 		load()
// 	}, [page])

// 	useEffect(() => {
// 		const observer = new IntersectionObserver(entries => {
// 			if (entries[0].isIntersecting && hasMore) {
// 				setPage(prev => prev + 1)
// 			}
// 		})

// 		const current = loaderRef.current
// 		if (current) observer.observe(current)

// 		// ✅ Cleanup function must return a function
// 		return () => {
// 			if (current) observer.unobserve(current)
// 		}
// 	}, [hasMore])

// 	return (
// 		<div className='mx-auto max-w-xl px-4 py-6'>
// 			{posts.map(post => (
// 				<PostCard key={post.id} post={post} />
// 			))}
// 			<div ref={loaderRef} className='py-4 text-center text-gray-400'>
// 				{hasMore ? 'Loading more...' : 'No more posts to load'}
// 			</div>
// 		</div>
// 	)
// }


const ThreadsPage = async ()=>{
	const response = await serverFetch("/posts")
}
