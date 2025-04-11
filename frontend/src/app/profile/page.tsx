import { useSession } from 'next-auth/react'

import { auth } from '@/auth'

export default async function ProfilePage() {
	const session = await auth()
	const token = session?.user?.accessToken
	console.log(token)
	return <div className=''>Hello</div>
}
