import {
	Card,
	CardContent,
	CardFooter,
	CardHeader,
	CardTitle
} from '../ui/card'

import { PostType } from '@/types/post/post.type'

const PostCard = ({ post }: { post: PostType }) => {
	return (
		<Card>
			<CardHeader>
				<CardTitle>{post.title}</CardTitle>
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
