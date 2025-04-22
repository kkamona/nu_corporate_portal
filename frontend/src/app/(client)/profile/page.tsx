import UserCard from '@/components/profile_page/UserCard'
import { Card, CardHeader, CardTitle } from '@/components/ui/card'
import { getCurrentUser } from '@/lib/auth'
import { cookies } from 'next/headers'

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
