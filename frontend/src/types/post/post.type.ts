export type PostType = {
    id: number
    userId: number
    title: string
    text: string
    attachments: string[]
    newsThumbnail: string
    news: boolean
}

export type PostResponseType = {
    content: PostType[]
    pageable: {
        pageNumber: number
        pageSize: number
    }
    totalPages: number
    last: boolean
}