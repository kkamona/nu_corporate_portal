import Image from 'next/image'
import Link from 'next/link'

const Header = () => {
	const navItems = [
		{
			label: 'Home',
			url: '/'
		},
		{
			label: 'Calendar',
			url: '/calendar'
		},
		{
			label: 'Phonebook',
			url: '/phonebook'
		},
		{
			label: 'Profile',
			url: '/profile'
		}
	]
	return (
		<header className='flex items-center justify-between px-10 py-5'>
			<Link
				href='/'
				className='relative block h-[40px] w-[200px] overflow-hidden'
			>
				<Image
					src={'/NU horizontal.png'}
					alt='Logo image'
					fill
					className='object-cover object-[center_50%]'
					sizes='200px'
				/>
			</Link>
			<nav className='flex gap-4'>
				{navItems.map((item, index) => (
					<a key={index} href={item.url}>
						{item.label}
					</a>
				))}
			</nav>
		</header>
	)
}

export default Header
