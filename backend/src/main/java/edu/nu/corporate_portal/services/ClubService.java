package edu.nu.corporate_portal.services;

import edu.nu.corporate_portal.DTO.Club.ClubPostDTO;
import edu.nu.corporate_portal.DTO.Club.ClubPatchDTO;
import edu.nu.corporate_portal.models.Club;
import edu.nu.corporate_portal.models.User;
import edu.nu.corporate_portal.repository.ClubRepository;
import edu.nu.corporate_portal.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ClubService {

    private final ClubRepository clubRepository;
    private final UserRepository userRepository;
    private final AzureBlobStorageService storage;

    @Autowired
    public ClubService(ClubRepository clubRepository, UserRepository userRepository, AzureBlobStorageService storage) {
        this.clubRepository = clubRepository;
        this.userRepository = userRepository;
        this.storage = storage;
    }

    public List<Club> getAllClubs() {
        return clubRepository.findAll();
    }

    public Club getClubById(Long id) {
        return clubRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Club not found"));
    }

    @Transactional
    public Club createClub(ClubPostDTO dto) {
        Club club = new Club();
        club.setName(dto.getName());
        club.setDescription(dto.getDescription());
        club.setFoundationDate(dto.getFoundationDate());
        if (dto.getShowMembers() != null) club.setShowMembers(dto.getShowMembers());
        club.setLocation(dto.getLocation());

        User president = userRepository.findById(dto.getPresidentId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        club.setPresident(president);

        if (dto.getMemberIds() != null) {
            List<User> members = userRepository.findAllById(dto.getMemberIds());
            club.setMembers(members);
        }

        return clubRepository.save(club);
    }

    @Transactional
    public Club updateClub(Long id, ClubPatchDTO dto) {
        Club club = clubRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Club not found"));

        if (dto.getName() != null) club.setName(dto.getName());
        if (dto.getDescription() != null) club.setDescription(dto.getDescription());
        if (dto.getFoundationDate() != null) club.setFoundationDate(dto.getFoundationDate());
        if (dto.getShowMembers() != null) club.setShowMembers(dto.getShowMembers());
        if (dto.getLocation() != null) club.setLocation(dto.getLocation());
        if (dto.getPresidentId() != null) {
            User president = userRepository.findById(dto.getPresidentId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));
            club.setPresident(president);
        }
        if (dto.getMemberIds() != null) {
            List<User> members = userRepository.findAllById(dto.getMemberIds());
            club.setMembers(members);
        }

        return clubRepository.save(club);
    }

    @Transactional
    public void deleteClub(Long id) {
        if (!clubRepository.existsById(id)) {
            throw new EntityNotFoundException("Club not found");
        }
        clubRepository.deleteById(id);
    }

    @Transactional
    public Club addMemberByEmail(Long clubId, String email) {
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new EntityNotFoundException("Club not found"));

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));

        club.getMembers().add(user);
        return clubRepository.save(club);
    }

    @Transactional
    public Club uploadProfilePhoto(Long clubId, MultipartFile photo) {
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        String url = storage.uploadFile(photo);

        club.setProfilePicture(url);

        return clubRepository.save(club);
    }

}