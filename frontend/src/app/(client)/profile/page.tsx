import UserCard from '@/components/profile_page/UserCard'
import { getCurrentUser } from '@/lib/auth'

const ProfilePage = async () => {
	const user = await getCurrentUser()

	return (
		<div>
			{/* <h1>Profile page</h1> */}
			<h2 className='mb-2.5 mt-4'>Your profile card</h2>
			<UserCard user={user}/>
		</div>
	)
}

export default ProfilePage
