import UserCard from '@/components/profile_page/UserCard'
import UserInterestsCard from '@/components/profile_page/UserInterestsCard'
import { getCurrentUser } from '@/lib/auth'

const ProfilePage = async () => {
	const user = await getCurrentUser()

	return (
		<div>
			{/* <h1>Profile page</h1> */}
			<h2 className='mt-4 mb-2.5'>Your profile card</h2>
			<div className='flex flex-col gap-5 my-5'>
				<UserCard user={user} />
				<UserInterestsCard user={user} />
			</div>
		</div>
	)
}

export default ProfilePage
