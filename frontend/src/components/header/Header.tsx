'use client'

import { Menu, X } from 'lucide-react'
import Image from 'next/image'
import Link from 'next/link'
import { useState } from 'react'

import { useIsMobile } from '@/hooks/use-mobile'

// import { useMobile } from "@/hooks/use-mobile"

const Header = () => {
	const [isMenuOpen, setIsMenuOpen] = useState(false)
	const isMobile = useIsMobile()

	const toggleMenu = () => {
		setIsMenuOpen(!isMenuOpen)
	}

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
		<header className='relative z-50 w-full'>
			<div className='flex items-center justify-between px-4 py-4 md:px-10 md:py-5'>
				<Link
					href='/'
					className='relative block h-[30px] w-[150px] overflow-hidden md:h-[40px] md:w-[200px]'
				>
					<Image
						src={'/NU horizontal.png'}
						alt='Logo image'
						fill
						className='object-cover object-[center_50%]'
						sizes='(max-width: 768px) 150px, 200px'
						priority={true}
					/>
				</Link>

				{/* Desktop Navigation */}
				<nav className='hidden md:flex md:gap-6 lg:gap-8'>
					{navItems.map((item, index) => (
						<Link
							key={index}
							href={item.url}
							className='font-medium text-gray-700 transition-colors hover:text-gray-900'
						>
							{item.label}
						</Link>
					))}
				</nav>

				{/* Mobile Menu Button */}
				<button
					className='text-gray-700 md:hidden'
					onClick={toggleMenu}
					aria-label={isMenuOpen ? 'Close menu' : 'Open menu'}
				>
					{isMenuOpen ? <X size={24} /> : <Menu size={24} />}
				</button>
			</div>

			{/* Mobile Navigation */}
			{isMenuOpen && isMobile && (
				<div className='absolute top-full left-0 w-full bg-white shadow-lg md:hidden'>
					<nav className='flex flex-col py-4'>
						{navItems.map((item, index) => (
							<Link
								key={index}
								href={item.url}
								className='px-6 py-3 font-medium text-gray-700 transition-colors hover:bg-gray-50'
								onClick={() => setIsMenuOpen(false)}
							>
								{item.label}
							</Link>
						))}
					</nav>
				</div>
			)}
		</header>
	)
}

export default Header
