'use client'

import { ColumnDef } from '@tanstack/react-table'

import UserListActionsCell from '@/components/userslist_page/UserListActionCell'
import { UserType } from '@/types/user/user.type'
import { ClubType } from '@/types/club/club.type'

export const columns: ColumnDef<ClubType>[] = [    
    {
        accessorKey: 'name',
        header: 'Club name'
    },
    {
        accessorKey: 'location',
        header: 'Location'
    }
]
