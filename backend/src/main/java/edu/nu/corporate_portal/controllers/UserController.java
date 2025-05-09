package edu.nu.corporate_portal.controllers;

import edu.nu.corporate_portal.DTO.Club.ClubSummaryDTO;
import edu.nu.corporate_portal.DTO.User.UserGetDTO;
import edu.nu.corporate_portal.DTO.User.UserPatchDTO;
import edu.nu.corporate_portal.models.User;
import edu.nu.corporate_portal.services.ClubService;
import edu.nu.corporate_portal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ClubService clubService;

    @Autowired
    public UserController(UserService userService, ClubService clubService) {
        this.userService = userService;
        this.clubService = clubService;
    }

    @GetMapping
    public ResponseEntity<List<UserGetDTO>> getAllUsers() {
        List<UserGetDTO> users = userService.getAllUsers()
                .stream()
                .map(UserGetDTO::new)
                .toList();
        return ResponseEntity.ok(users);
    }

    //    @GetMapping("/{id}")
//    public ResponseEntity<UserGetDTO> getUserById(@PathVariable Long id) {
//        return userService.getUserById(id)
//                .map(UserGetDTO::new)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }

    @GetMapping("/{id}")
    public ResponseEntity<UserGetDTO> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(user -> {
                    UserGetDTO dto = new UserGetDTO(user);
                    var clubs = clubService.findClubsByMemberId(user.getId())
                            .stream()
                            .map(c -> new ClubSummaryDTO(c.getId(), c.getName()))
                            .toList();
                    dto.setClubs(clubs);
                    return ResponseEntity.ok(dto);
                })
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
            @RequestBody UserPatchDTO patchDTO) throws IOException {
        User updated = userService.updateUser(id, patchDTO);
        return ResponseEntity.ok(new UserGetDTO(updated));
    }

    @PostMapping(value = "/{id}/photo", consumes = "multipart/form-data")
    @PreAuthorize("hasRole('ADMIN') or #id.toString() == authentication.name")
    public ResponseEntity<UserGetDTO> setProfilePhoto(
            @PathVariable Long id,
            @RequestParam("profilePhoto") MultipartFile profilePhoto
    ) throws IOException {
        User updated = userService.uploadProfilePhoto(id, profilePhoto);
        return ResponseEntity.ok(new UserGetDTO(updated));
    }

}