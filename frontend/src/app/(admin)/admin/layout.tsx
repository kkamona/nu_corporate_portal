import { AppSidebar } from '@/components/side_bar/app-sidebar'
import { SidebarProvider } from '@/components/ui/sidebar'

export default function AdminPageLayout({
	children
}: {
	children: React.ReactNode
}) {
	return (
		<>
			<SidebarProvider>
				<AppSidebar />
				<main>{children}</main>
			</SidebarProvider>
		</>
	)
}
