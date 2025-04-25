import NewsCreateForm from '@/components/news/NewsCreateForm'

const CreateNewsPage = async () => {
	return (
		<div className=''>
			<h2>News Create Form</h2>
			<div className='rounded-md border p-2'>
				<NewsCreateForm />
			</div>
		</div>
	)
}

export default CreateNewsPage
