package edu.nu.corporate_portal.controllers;

import edu.nu.corporate_portal.DTO.Club.ClubGetDTO;
import edu.nu.corporate_portal.DTO.Club.ClubPostDTO;
import edu.nu.corporate_portal.DTO.Club.ClubPatchDTO;
import edu.nu.corporate_portal.DTO.User.UserGetDTO;
import edu.nu.corporate_portal.models.Club;
import edu.nu.corporate_portal.models.User;
import edu.nu.corporate_portal.services.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/clubs")
public class ClubController {

    private final ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping
    public ResponseEntity<List<ClubGetDTO>> getAllClubs() {
        List<ClubGetDTO> dtos = clubService.getAllClubs().stream()
                .map(ClubGetDTO::new)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClubGetDTO> getClubById(@PathVariable Long id) {
        Club club = clubService.getClubById(id);
        return ResponseEntity.ok(new ClubGetDTO(club));
    }

    @PostMapping
    public ResponseEntity<ClubGetDTO> createClub(@RequestBody ClubPostDTO dto) {
        Club created = clubService.createClub(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ClubGetDTO(created));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClubGetDTO> updateClub(
            @PathVariable Long id,
            @RequestBody ClubPatchDTO dto) {
        Club updated = clubService.updateClub(id, dto);
        return ResponseEntity.ok(new ClubGetDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClub(@PathVariable Long id) {
        clubService.deleteClub(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/members")
    public ResponseEntity<ClubGetDTO> addMemberByEmail(
            @PathVariable Long id,
            @RequestParam("email") String email) {

        Club updated = clubService.addMemberByEmail(id, email);
        return ResponseEntity.ok(new ClubGetDTO(updated));
    }

    @PostMapping(value = "/{id}/photo", consumes = "multipart/form-data")
    public ResponseEntity<ClubGetDTO> setProfilePhoto(
            @PathVariable Long id,
            @RequestParam("profilePhoto") MultipartFile profilePhoto
    ) {
        Club updated = clubService.uploadProfilePhoto(id, profilePhoto);
        return ResponseEntity.ok(new ClubGetDTO(updated));
    }
}
