import { FormState } from '@/types/formState/formState.type';
import { useRef, useEffect } from 'react';
import { toast } from 'react-hot-toast';

const useToastMessage = (formState: FormState) => {
    const prevTimestamp = useRef(formState.timestamp);

    const showToast =
        formState.message &&
        formState.timestamp !== prevTimestamp.current;

    useEffect(() => {
        if (showToast) {
            if (formState.status === 'ERROR') {
                toast.error(formState.message);
            } else {
                toast.success(formState.message);
            }

            prevTimestamp.current = formState.timestamp;
        }
    }, [formState, showToast]);
};

export { useToastMessage };