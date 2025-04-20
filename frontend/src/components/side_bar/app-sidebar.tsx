import {
	BookOpen,
	Bot,
	Calendar,
	ChevronRight,
	Settings2,
	SquareTerminal,
	Users
} from 'lucide-react'
import Image from 'next/image'

import {
	Collapsible,
	CollapsibleContent,
	CollapsibleTrigger
} from '../ui/collapsible'

import SidebarFooterContent from './SidebarFooter'
import { NavMain } from './nav-main'
import {
	Sidebar,
	SidebarContent,
	SidebarFooter,
	SidebarGroup,
	SidebarGroupLabel,
	SidebarHeader,
	SidebarMenu,
	SidebarMenuButton,
	SidebarMenuItem,
	SidebarMenuSub,
	SidebarMenuSubButton,
	SidebarMenuSubItem
} from '@/components/ui/sidebar'
import { serverFetch } from '@/lib/api'
import { UserType } from '@/types/user/user.type'

export async function AppSidebar() {
	const response = serverFetch('/users/me', {
		method: 'GET'
	})
	const user = await (await response).json()

	const users_group = [
		{
			title: 'Users',
			isActive: true,
			url: '#',
			icon: Users,
			items: [{ title: 'Users list', url: '/admin/users' }]
		}
	]

	const event_group = [
		{
			title: 'Events',
			isActive: true,
			url: '#',
			icon: Calendar,
			items: [
				{ title: 'Create Event', url: '/admin/events/create' },
				{
					title: 'List Events',
					url: '/admin/events'
				}
			]
		}
	]

	return (
		<Sidebar>
			<SidebarHeader className='relative h-[100px]'>
				<Image
					src={'/NU horizontal.png'}
					alt='Logo image'
					fill
					className='object-cover object-[center_50%]'
					sizes='200px'
				/>
			</SidebarHeader>
			<SidebarContent>
				<SidebarGroup>
					<SidebarGroupLabel>Platform</SidebarGroupLabel>
					<SidebarMenu>
						{users_group.map(item => (
							<Collapsible
								key={item.title}
								asChild
								defaultOpen={item.isActive}
								className='group/collapsible'
							>
								<SidebarMenuItem>
									<CollapsibleTrigger asChild>
										<SidebarMenuButton tooltip={item.title}>
											{item.icon && <item.icon />}
											<span>{item.title}</span>
											<ChevronRight className='ml-auto transition-transform duration-200 group-data-[state=open]/collapsible:rotate-90' />
										</SidebarMenuButton>
									</CollapsibleTrigger>
									<CollapsibleContent>
										<SidebarMenuSub>
											{item.items?.map(subItem => (
												<SidebarMenuSubItem
													key={subItem.title}
												>
													<SidebarMenuSubButton
														asChild
													>
														<a href={subItem.url}>
															<span>
																{subItem.title}
															</span>
														</a>
													</SidebarMenuSubButton>
												</SidebarMenuSubItem>
											))}
										</SidebarMenuSub>
									</CollapsibleContent>
								</SidebarMenuItem>
							</Collapsible>
						))}
					</SidebarMenu>
					<SidebarMenu>
						{event_group.map(item => (
							<Collapsible
								key={item.title}
								asChild
								defaultOpen={item.isActive}
								className='group/collapsible'
							>
								<SidebarMenuItem>
									<CollapsibleTrigger asChild>
										<SidebarMenuButton tooltip={item.title}>
											{item.icon && <item.icon />}
											<span>{item.title}</span>
											<ChevronRight className='ml-auto transition-transform duration-200 group-data-[state=open]/collapsible:rotate-90' />
										</SidebarMenuButton>
									</CollapsibleTrigger>
									<CollapsibleContent>
										<SidebarMenuSub>
											{item.items?.map(subItem => (
												<SidebarMenuSubItem
													key={subItem.title}
												>
													<SidebarMenuSubButton
														asChild
													>
														<a href={subItem.url}>
															<span>
																{subItem.title}
															</span>
														</a>
													</SidebarMenuSubButton>
												</SidebarMenuSubItem>
											))}
										</SidebarMenuSub>
									</CollapsibleContent>
								</SidebarMenuItem>
							</Collapsible>
						))}
					</SidebarMenu>
				</SidebarGroup>
			</SidebarContent>
			<SidebarFooter>
				<SidebarFooterContent user={user} />
			</SidebarFooter>
		</Sidebar>
	)
}
