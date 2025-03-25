package edu.nu.corporate_portal.controllers;

import edu.nu.corporate_portal.DTO.User.UserGetDTO;
import edu.nu.corporate_portal.DTO.User.UserPatchDTO;
import edu.nu.corporate_portal.DTO.User.UserPostDTO;
import edu.nu.corporate_portal.models.User;
import edu.nu.corporate_portal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/email/{email}")
    public ResponseEntity<UserGetDTO> getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email)
                .map(UserGetDTO::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserGetDTO> getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username)
                .map(UserGetDTO::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserGetDTO> createUser(@RequestBody UserPostDTO dto) {
        User created = userService.createUser(dto);
        return ResponseEntity.ok(new UserGetDTO(created));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserGetDTO> updateUser(@PathVariable Long id, @RequestBody UserPatchDTO dto) {
        User updated = userService.updateUser(id, dto);
        return ResponseEntity.ok(new UserGetDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/email/{email}")
    public ResponseEntity<Void> deleteUserByEmail(@PathVariable String email) {
        userService.deleteUserByEmail(email);
        return ResponseEntity.noContent().build();
    }
}
