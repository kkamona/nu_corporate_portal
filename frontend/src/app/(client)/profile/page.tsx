import UserCard from '@/components/profile_page/UserCard'
import { getCurrentUser } from '@/lib/auth'

const ProfilePage = async () => {
	const user = await getCurrentUser()
	// const token = (await cookies()).get("auth-token")?.value
	console.log(user)
	return (
		<div>
			<h1>Profile page</h1>
			<UserCard user={user}/>
		</div>
	)
}

export default ProfilePage
