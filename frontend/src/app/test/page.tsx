import { fetchWithAuth } from '../lib/auth-fetch'

const TestPage = () => {
	const res = fetchWithAuth('/auth/azure-login')
	return <div className=''>This is the test page</div>
}

export default TestPage
