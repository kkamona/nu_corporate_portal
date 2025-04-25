'use server'

import {
	Card,
	CardContent,
	CardFooter,
	CardHeader,
	CardTitle
} from '../ui/card'

import { serverFetch } from '@/lib/api'
import { PostType } from '@/types/post/post.type'
import { UserType } from '@/types/user/user.type'

const PostCard = async ({ post }: { post: PostType }) => {
	const response = await serverFetch(`/users/${post.userId}`)
	const post_author: UserType = await response.json()
	// console.log(post_author)
	return (
		<Card>
			<CardHeader>
				<CardTitle className='flex gap-2 items-center'>
					<img
						className='h-8 w-8 rounded-full object-cover'
						src={
							post_author.profilePicture
								? post_author.profilePicture +
									'?sp=rl&st=2025-04-19T12:00:17Z&se=2025-09-16T20:00:17Z&spr=https&sv=2024-11-04&sr=c&sig=KYbm3kp%2FOaCpvDyzu6Hvh8OBkfb%2Fm04JESmEMqt9y3Q%3D'
								: '/no-profile.png'
						}
						alt='User profile'
					/>
					{post.title}
				</CardTitle>
			</CardHeader>
			<CardContent>{post.text}</CardContent>
			{post.attachments?.length > 0 && (
				<CardFooter>
					{post.attachments.map((file, idx) => (
						<div key={idx}>{file}</div>
					))}
				</CardFooter>
			)}
		</Card>
	)
}
export default PostCard
