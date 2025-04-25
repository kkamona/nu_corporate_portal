'use client'

import { useActionState, useEffect } from 'react'
import { Switch } from '@/components/ui/switch'
import { Label } from '@/components/ui/label'
import SubmitButton from '../form_elements/SubmitButton'
import { useToastMessage } from '@/hooks/use-toast-message'
import { EMPTY_FORM_STATE } from '@/types/formState/formState.type'
import { UserType } from '@/types/user/user.type'
import { updateUserPrivacySettings } from '@/actions/updateUserPrivacySettings'

const ProfileConfigForm = ({
  user,
  onSuccess
}: {
  user: UserType
  onSuccess: () => void
}) => {
  const [formState, action] = useActionState(
    updateUserPrivacySettings.bind(null, user.id),
    EMPTY_FORM_STATE
  )

  useToastMessage(formState)
  
  useEffect(() => {
    if (formState.status === 'SUCCESS') {
      onSuccess?.()
    }
  }, [formState.status, onSuccess])

  return (
    <form action={action} className="space-y-6">
      <div className="space-y-4">
        <div className="flex items-center justify-between">
          <Label htmlFor="showName">Display Name</Label>
          <Switch 
            id="showName" 
            name="showName"
            defaultChecked={user.showName ?? true}
          />
        </div>

        <div className="flex items-center justify-between">
          <Label htmlFor="showContactInfo">Display Contact Info</Label>
          <Switch 
            id="showContactInfo" 
            name="showContactInfo"
            defaultChecked={user.showContactInfo ?? true}
          />
        </div>

        <div className="flex items-center justify-between">
          <Label htmlFor="showDateOfBirth">Display Date of Birth</Label>
          <Switch 
            id="showDateOfBirth" 
            name="showDateOfBirth"
            defaultChecked={user.showDateOfBirth ?? true}
          />
        </div>

        <div className="flex items-center justify-between">
          <Label htmlFor="showSchool">Display School</Label>
          <Switch 
            id="showSchool" 
            name="showSchool"
            defaultChecked={user.showSchool ?? true}
          />
        </div>

        <div className="flex items-center justify-between">
          <Label htmlFor="showMajor">Display Major</Label>
          <Switch 
            id="showMajor" 
            name="showMajor"
            defaultChecked={user.showMajor ?? true}
          />
        </div>

        <div className="flex items-center justify-between">
          <Label htmlFor="showProfilePicture">Display Profile Picture</Label>
          <Switch 
            id="showProfilePicture" 
            name="showProfilePicture"
            defaultChecked={user.showProfilePicture ?? true}
          />
        </div>
      </div>

      <div className="flex justify-end">
        <SubmitButton label="Save Settings" loading="Saving..." />
      </div>
    </form>
  )
}

export default ProfileConfigForm