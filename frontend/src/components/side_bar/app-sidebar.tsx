import SidebarFooterContent from './SidebarFooter'
import {
	Sidebar,
	SidebarContent,
	SidebarFooter,
	SidebarGroup,
	SidebarHeader,
	SidebarMenu,
	SidebarMenuButton,
	SidebarMenuItem
} from '@/components/ui/sidebar'
import { serverFetch } from '@/lib/api'
import { UserType } from '@/types/user/user.type'

export async function AppSidebar() {
	// const user: UserType = {
	// 	id: 1,
	// 	email: 'john.doe@example.com',
	// 	username: 'johndoe',
	// 	firstName: 'John',
	// 	lastName: 'Doe',
	// 	contactInfo: '+1234567890',
	// 	profilePicture:
	// 		'https://www.gravatar.com/avatar/2c7d99fe281ecd3bcd65ab915bac6dd5?s=250',
	// 	dateOfBirth: '2000-05-20',
	// 	createdAt: '2025-04-14T06:19:48.819Z',
	// 	updatedAt: '2025-04-14T06:19:48.819Z',
	// 	azureSsoId: 'abc123xyz',
	// 	authenticationProvider: 'azure',
	// 	year: 3,
	// 	school: 'School of Engineering',
	// 	major: 'Computer Science',
	// 	department: 'Software Engineering',
	// 	role: 'student'
	// }
	const response = serverFetch('/users/me', {
		method: 'GET'
	})
	const user = await (await response).json()

	return (
		<Sidebar>
			<SidebarHeader />
			<SidebarContent>
				<SidebarGroup />
				<SidebarGroup />
			</SidebarContent>
			<SidebarFooter>
				<SidebarFooterContent user={user} />
			</SidebarFooter>
		</Sidebar>
	)
}
