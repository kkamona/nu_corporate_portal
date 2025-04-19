package edu.nu.corporate_portal.services;

import edu.nu.corporate_portal.DTO.User.UserPatchDTO;
import edu.nu.corporate_portal.models.User;
import edu.nu.corporate_portal.models.User.Role;
import edu.nu.corporate_portal.models.User.School;
import edu.nu.corporate_portal.repository.UserRepository;
import edu.nu.corporate_portal.security.JwtUtil;

import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

        if (dto.getProfilePictureBase64() != null) {
            String uploadsDir = System.getProperty("user.dir") + "/uploaded-static/files/profilePictures";


            String fileName = "profile_" + id + "_" + System.currentTimeMillis() + ".jpg";
            Path path = Paths.get(uploadsDir, fileName);

            byte[] decodedBytes = Base64.getDecoder().decode(dto.getProfilePictureBase64());
            Files.createDirectories(path.getParent());
            Files.write(path, decodedBytes);

            user.setProfilePicture("/files/profilePictures/" + fileName);
        }

        if (dto.getDateOfBirth() != null) user.setDateOfBirth(dto.getDateOfBirth());
        if (dto.getSchool() != null) user.setSchool(dto.getSchool());
        if (dto.getMajor() != null) user.setMajor(dto.getMajor());
        if (dto.getRole() != null) user.setRole(dto.getRole());

        return userRepository.save(user);
    }
}
