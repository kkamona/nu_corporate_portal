package edu.nu.corporate_portal.controllers;

import edu.nu.corporate_portal.models.UserProfile;
import edu.nu.corporate_portal.services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
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

    // Get user profile by user id
    @GetMapping("/user/{userId}")
    public ResponseEntity<UserProfile> getUserProfileByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(userProfileService.getUserProfileByUserId(userId));
    }

    // Create a new user profile
    @PostMapping
    public ResponseEntity<UserProfile> createUserProfile(@RequestBody UserProfile userProfile) {
        return ResponseEntity.ok(userProfileService.createUserProfile(userProfile));
    }

    // Update a user profile by profile id
    @PutMapping("/{id}")
    public ResponseEntity<UserProfile> updateUserProfile(@PathVariable Long id, @RequestBody UserProfile updatedProfile) {
        return ResponseEntity.ok(userProfileService.updateUserProfile(id, updatedProfile));
    }

    // Delete a user profile by profile id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserProfile(@PathVariable Long id) {
        userProfileService.deleteUserProfile(id);
        return ResponseEntity.noContent().build();
    }
}
