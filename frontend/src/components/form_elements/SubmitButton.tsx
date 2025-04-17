import { useFormStatus } from 'react-dom'

import { Button } from '../ui/button'

const SubmitButton = ({
	label,
	loading
}: {
	label: string
	loading: string
}) => {
	const { pending } = useFormStatus()
	return <Button className='cursor-pointer' disabled={pending}>{pending ? loading : label}</Button>
}
export default SubmitButton
