import LoginForm from '@/components/login_page/LoginForm'
import { Suspense } from 'react'

export default async function LoginPage() {
	return (
		<div
			className='relative flex min-h-screen w-full items-center justify-center bg-cover bg-center'
			style={{
				backgroundImage: "url('/turan_view.jpg?height=1080&width1920')",
				backgroundSize: 'cover',
				backgroundPosition: 'center'
			}}
		>
			<Suspense fallback={<div>Loading...</div>}>
				<LoginForm />
			</Suspense>
		</div>
	)
}
