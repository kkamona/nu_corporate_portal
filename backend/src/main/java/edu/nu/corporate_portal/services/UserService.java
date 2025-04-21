package edu.nu.corporate_portal.services;

import edu.nu.corporate_portal.DTO.User.UserPatchDTO;
import edu.nu.corporate_portal.models.User;
import edu.nu.corporate_portal.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AzureBlobStorageService storage;

    @Autowired
    public UserService(
            UserRepository userRepository,
            AzureBlobStorageService storage
    ) {
        this.userRepository = userRepository;
        this.storage = storage;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public User updateUser(Long id, UserPatchDTO dto) throws IOException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (dto.getPasswordHash() != null) user.setHashedPassword(dto.getPasswordHash());
        if (dto.getFirstName() != null) user.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null) user.setLastName(dto.getLastName());
        if (dto.getContactInfo() != null) user.setContactInfo(dto.getContactInfo());
        if (dto.getDateOfBirth() != null) user.setDateOfBirth(dto.getDateOfBirth());
        if (dto.getSchool() != null) user.setSchool(dto.getSchool());
        if (dto.getMajor() != null) user.setMajor(dto.getMajor());
        if (dto.getRole() != null) user.setRole(dto.getRole());

        if (dto.getShowName() != null) user.setShowName(dto.getShowName());
        if (dto.getShowContactInfo() != null) user.setShowContactInfo(dto.getShowContactInfo());
        if (dto.getShowDateOfBirth() != null) user.setShowDateOfBirth(dto.getShowDateOfBirth());
        if (dto.getShowSchool() != null) user.setShowSchool(dto.getShowSchool());
        if (dto.getShowMajor() != null) user.setShowMajor(dto.getShowMajor());
        if (dto.getShowProfilePicture() != null) user.setShowProfilePicture(dto.getShowProfilePicture());

        return userRepository.save(user);
    }


    @Transactional
    public User uploadProfilePhoto(Long userId, MultipartFile photo) throws IOException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));


        String url = storage.uploadFile(photo);

        user.setProfilePicture(url);

        return userRepository.save(user);
    }

}
