package edu.nu.corporate_portal.controllers;

import edu.nu.corporate_portal.DTO.UserRights.UserRightsGetDTO;
import edu.nu.corporate_portal.DTO.UserRights.UserRightsUpdateDTO;
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
    public ResponseEntity<UserRightsGetDTO> getUserRights(@PathVariable Long userId) {
        UserRights rights = userRightsService.getUserRightsByUserId(userId);
        UserRightsGetDTO dto = new UserRightsGetDTO(rights);
        return ResponseEntity.ok(dto);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserRightsGetDTO> updateUserRights(
            @PathVariable Long userId,
            @RequestBody UserRightsUpdateDTO updatedRightsDTO) {

        UserRights rights = userRightsService.updateUserRights(userId, updatedRightsDTO);
        UserRightsGetDTO dto = new UserRightsGetDTO(rights);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserRights(@PathVariable Long userId) {
        userRightsService.deleteUserRights(userId);
        return ResponseEntity.noContent().build();
    }
}
