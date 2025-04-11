import LoginButton from './LoginButton'

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
			<div className='absolute inset-0 bg-black/40' />
			<div className='relative z-10 mx-auto w-full max-w-md rounded-xl border border-white/20 bg-white/10 p-8 shadow-lg backdrop-blur-md'>
				<div className='text-center'>
					<h1 className='text-3xl font-bold'>
						Sign in to your account
					</h1>
					<p className='mt-2 text-gray-600'>
						Use your Microsoft Entra ID account
					</p>
				</div>
				<LoginButton />
			</div>
		</div>
	)
}
