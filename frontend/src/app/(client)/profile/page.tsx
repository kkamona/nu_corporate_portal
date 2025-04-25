import UserCard from '@/components/profile_page/UserCard'
import { getCurrentUser } from '@/lib/auth'

const ProfilePage = async () => {
	const user = await getCurrentUser()

	return (
		<div>
			{/* <h1>Profile page</h1> */}
			<h2 className='mt-4 mb-2.5'>Your profile card</h2>
			<div className='my-5'>
				<UserCard user={user} />
			</div>
		</div>
	)
}

export default ProfilePage
