import Footer from '@/components/footer/Footer'
import Header from '@/components/header/Header'

export default function ClintPageLayout({
	children
}: {
	children: React.ReactNode
}) {
	return (
		<>
			<div className='h-10 w-full bg-[var(--nu-gray)]'></div>
			<Header />
			<main className="px-4 sm:px-6 lg:px-20">{children}</main>
			<Footer />
		</>
	)
}
