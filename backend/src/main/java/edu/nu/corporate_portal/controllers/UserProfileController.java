package edu.nu.corporate_portal.controllers;

import edu.nu.corporate_portal.DTO.UserProfile.UserProfileGetDTO;
import edu.nu.corporate_portal.DTO.UserProfile.UserProfilePatchDTO;
import edu.nu.corporate_portal.DTO.UserProfile.UserProfilePostDTO;
import edu.nu.corporate_portal.models.UserProfile;
import edu.nu.corporate_portal.services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-profiles")
public class UserProfileController {

    private final UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserProfileGetDTO> getUserProfileByUserId(@PathVariable Long userId) {
        UserProfile profile = userProfileService.getUserProfileByUserId(userId);
        return ResponseEntity.ok(new UserProfileGetDTO(profile));
    }

    @PostMapping
    public ResponseEntity<UserProfileGetDTO> createUserProfile(@RequestBody UserProfilePostDTO postDTO) {
        UserProfile profile = userProfileService.createUserProfile(postDTO);
        return new ResponseEntity<>(new UserProfileGetDTO(profile), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserProfileGetDTO> updateUserProfile(@PathVariable Long id, @RequestBody UserProfilePatchDTO patchDTO) {
        UserProfile updatedProfile = userProfileService.updateUserProfile(id, patchDTO);
        return ResponseEntity.ok(new UserProfileGetDTO(updatedProfile));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserProfile(@PathVariable Long id) {
        userProfileService.deleteUserProfile(id);
        return ResponseEntity.noContent().build();
    }
}
