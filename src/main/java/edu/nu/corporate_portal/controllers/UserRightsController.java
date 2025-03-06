package edu.nu.corporate_portal.controllers;

import edu.nu.corporate_portal.models.UserRights;
import edu.nu.corporate_portal.services.UserRightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-rights")
public class UserRightsController {

    private final UserRightsService userRightsService;

    @Autowired
    public UserRightsController(UserRightsService userRightsService) {
        this.userRightsService = userRightsService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserRights> getUserRights(@PathVariable Long userId) {
        return ResponseEntity.ok(userRightsService.getUserRightsByUserId(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserRights> updateUserRights(@PathVariable Long userId, @RequestBody UserRights updatedRights) {
        return ResponseEntity.ok(userRightsService.updateUserRights(userId, updatedRights));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserRights(@PathVariable Long userId) {
        userRightsService.deleteUserRights(userId);
        return ResponseEntity.noContent().build();
    }
}
