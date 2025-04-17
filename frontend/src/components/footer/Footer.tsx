import Image from 'next/image'
import Link from 'next/link'

import { Icons } from '@/components/icons'

const Footer = () => {
	const socialMedias = [
		{
			src: Icons.facebook,
			alt: 'Facebook',
			url: 'https://www.facebook.com/nuedukz/#'
		},
		{
			src: Icons.vk,
			alt: 'VK',
			url: 'https://vk.com/club18829857'
		},
		{
			src: Icons.instgram,
			alt: 'Instagram',
			url: 'https://www.instagram.com/nuedukz/#'
		},
		{
			src: Icons.linkedin,
			alt: 'LinkedIn',
			url: 'https://www.linkedin.com/edu/nazarbayev-university-153043'
		},
		{
			src: Icons.x,
			alt: 'X(twitter)',
			url: 'https://x.com/#!/NUedukz'
		}
	]

	return (
		<footer className='bg-[var(--nu-gray)] text-white'>
			<div className='container mx-auto px-4 py-12 md:py-16'>
				<div className='flex flex-col gap-12 md:flex-row md:justify-between md:gap-0'>
					<div className='space-y-6'>
						<h3 className='text-xl font-semibold md:text-2xl'>
							NAZARBAYEV UNIVERSITY
						</h3>
						<address className='text-base not-italic md:text-lg'>
							53 Kabanbay Batyr Ave, Nur-Sultan city, <br />{' '}
							Republic of Kazakhstan, 010000 <br /> Nazarbayev
							University
						</address>
					</div>
					<div className='space-y-6'>
						<h3 className='text-xl font-semibold md:text-2xl'>
							SOCIAL MEDIA
						</h3>
						<div className='flex gap-6'>
							{socialMedias.map((media, index) => (
								<Link
									key={index}
									href={media.url}
									target='_blank'
									rel='noopener noreferrer'
									className='transition-opacity hover:opacity-80'
									aria-label={media.alt}
								>
									<Image
										src={media.src || '/placeholder.svg'}
										alt={media.alt}
										width={24}
										height={24}
										className='h-6 w-6'
									/>
								</Link>
							))}
						</div>
					</div>
				</div>
				<div className='mt-20 text-center text-base md:mt-24 md:text-lg'>
					© 2025 Nazarbayev University
				</div>
			</div>
		</footer>
	)
}

export default Footer
