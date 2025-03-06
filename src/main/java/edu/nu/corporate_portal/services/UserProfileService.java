package edu.nu.corporate_portal.services;

import edu.nu.corporate_portal.models.UserProfile;
import edu.nu.corporate_portal.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    @Autowired
    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public UserProfile getUserProfileByUserId(Long userId) {
        return userProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User profile not found"));
    }

    public UserProfile createUserProfile(UserProfile userProfile) {
        // Optionally, check if the user already has a profile
        return userProfileRepository.save(userProfile);
    }

    public UserProfile updateUserProfile(Long id, UserProfile updatedProfile) {
        UserProfile profile = userProfileRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User profile not found"));
        profile.setAdditionalInfo(updatedProfile.getAdditionalInfo());
        profile.setPhoneNumber(updatedProfile.getPhoneNumber());
        return userProfileRepository.save(profile);
    }

    public void deleteUserProfile(Long id) {
        if (!userProfileRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User profile not found");
        }
        userProfileRepository.deleteById(id);
    }

    public boolean userProfileExists(Long id) {
        return userProfileRepository.findByUserId(id).isPresent();
    }

}
