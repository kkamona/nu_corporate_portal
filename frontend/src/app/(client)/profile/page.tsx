import UserCard from '@/components/profile_page/UserCard'
import { Card, CardHeader, CardTitle } from '@/components/ui/card'
import { getCurrentUser } from '@/lib/auth'

const ProfilePage = async () => {
	const user = await getCurrentUser()
	console.log(user)
	return (
		<div>
			<h1>Profile page</h1>
			<UserCard user={user} />
		</div>
	)
}

export default ProfilePage
