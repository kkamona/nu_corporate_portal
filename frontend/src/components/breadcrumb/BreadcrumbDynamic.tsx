'use client'

import Link from 'next/link'
import { usePathname } from 'next/navigation'
import { Fragment } from 'react'

import {
	Breadcrumb,
	BreadcrumbItem,
	BreadcrumbLink,
	BreadcrumbList,
	BreadcrumbPage,
	BreadcrumbSeparator
} from '@/components/ui/breadcrumb'

const customNames: Record<string, string> = {
	admin: 'Dashboard',
	users: 'Users',
	add: 'Add User',
	settings: 'Settings'
}

const formatSegment = (segment: string) =>
	customNames[segment] ||
	segment.replace(/-/g, ' ').replace(/\b\w/g, char => char.toUpperCase())

export default function BreadcrumbDynamic() {
	const pathname = usePathname()
	const segments = pathname
		.replace(/^\/|\/$/g, '')
		.split('/')
		.filter(Boolean)

	return (
		<Breadcrumb>
			<BreadcrumbList>
				{segments.map((segment, index) => {
					const href = '/' + segments.slice(0, index + 1).join('/')
					const isLast = index === segments.length - 1

					return (
						<Fragment key={index}>
							<BreadcrumbItem>
								{!isLast ? (
									<BreadcrumbLink asChild>
										<Link href={href}>
											{formatSegment(segment)}
										</Link>
									</BreadcrumbLink>
								) : (
									<BreadcrumbPage>
										{formatSegment(segment)}
									</BreadcrumbPage>
								)}
							</BreadcrumbItem>
							{!isLast && <BreadcrumbSeparator />}
						</Fragment>
					)
				})}
			</BreadcrumbList>
		</Breadcrumb>
	)
}
