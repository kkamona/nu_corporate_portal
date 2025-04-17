package edu.nu.corporate_portal.controllers;

import edu.nu.corporate_portal.DTO.User.UserGetDTO;
import edu.nu.corporate_portal.DTO.User.UserPatchDTO;
import edu.nu.corporate_portal.models.User;
import edu.nu.corporate_portal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserGetDTO>> getAllUsers() {
        List<UserGetDTO> users = userService.getAllUsers()
                .stream()
                .map(UserGetDTO::new)
                .toList();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserGetDTO> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(UserGetDTO::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/me")
    public ResponseEntity<UserGetDTO> getCurrentUser(@AuthenticationPrincipal Jwt jwt) {
        Long id = jwt.getClaim("id");
        return userService.getUserById(id)
                .map(UserGetDTO::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    @PreAuthorize(
            "hasRole('ADMIN') or #id.toString() == authentication.name"
    )
    public ResponseEntity<UserGetDTO> patchUser(
            @PathVariable Long id,
            @RequestBody UserPatchDTO patchDTO) {
        User updated = userService.updateUser(id, patchDTO);
        return ResponseEntity.ok(new UserGetDTO(updated));
    }
}