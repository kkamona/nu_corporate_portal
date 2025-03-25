package edu.nu.corporate_portal.services;

import edu.nu.corporate_portal.DTO.UserProfile.UserProfilePatchDTO;
import edu.nu.corporate_portal.DTO.UserProfile.UserProfilePostDTO;
import edu.nu.corporate_portal.models.User;
import edu.nu.corporate_portal.models.UserProfile;
import edu.nu.corporate_portal.repository.UserProfileRepository;
import edu.nu.corporate_portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserProfileService(UserProfileRepository userProfileRepository, UserRepository userRepository) {
        this.userProfileRepository = userProfileRepository;
        this.userRepository = userRepository;
    }

    public UserProfile getUserProfileByUserId(Long userId) {
        return userProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User profile not found"));
    }

    public UserProfile createUserProfile(UserProfilePostDTO postDTO) {
        User user = userRepository.findById(postDTO.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        UserProfile profile = new UserProfile();
        profile.setUser(user);
        profile.setAdditionalInfo(postDTO.getAdditionalInfo());
        profile.setPhoneNumber(postDTO.getPhoneNumber());

        return userProfileRepository.save(profile);
    }

    public UserProfile updateUserProfile(Long id, UserProfilePatchDTO patchDTO) {
        UserProfile profile = userProfileRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User profile not found"));

        if (patchDTO.getAdditionalInfo() != null) {
            profile.setAdditionalInfo(patchDTO.getAdditionalInfo());
        }

        if (patchDTO.getPhoneNumber() != null) {
            profile.setPhoneNumber(patchDTO.getPhoneNumber());
        }

        return userProfileRepository.save(profile);
    }

    public void deleteUserProfile(Long id) {
        if (!userProfileRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User profile not found");
        }
        userProfileRepository.deleteById(id);
    }

    public boolean userProfileExists(Long userId) {
        return userProfileRepository.findByUserId(userId).isPresent();
    }
}
