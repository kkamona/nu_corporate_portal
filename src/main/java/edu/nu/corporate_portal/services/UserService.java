package edu.nu.corporate_portal.services;

import edu.nu.corporate_portal.DTO.User.UserPatchDTO;
import edu.nu.corporate_portal.DTO.User.UserPostDTO;
import edu.nu.corporate_portal.models.User;
import edu.nu.corporate_portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> getUserByAzureSsoId(String azureSsoId) {
        return userRepository.findByAzureSsoId(azureSsoId);
    }

    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean usernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    public User createUser(UserPostDTO dto) {
        if (emailExists(dto.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists!");
        }
        if (usernameExists(dto.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists!");
        }

        User user = new User();
        applyPostDtoToUser(dto, user);
        return userRepository.save(user);
    }

    public User updateUser(Long id, UserPatchDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        applyPatchDtoToUser(dto, user);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!");
        }
        userRepository.deleteById(id);
    }

    public void deleteUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        userRepository.delete(user);
    }


    private void applyPostDtoToUser(UserPostDTO dto, User user) {
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setPasswordHash(dto.getPasswordHash());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setContactInfo(dto.getContactInfo());
        user.setProfilePicture(dto.getProfilePicture());
        user.setDateOfBirth(dto.getDateOfBirth());
        user.setAzureSsoId(dto.getAzureSsoId());
        user.setAuthenticationProvider(User.AuthenticationProvider.valueOf(String.valueOf(dto.getAuthenticationProvider())));
        user.setYear(dto.getYear());
        user.setSchool(dto.getSchool() != null ? User.School.valueOf(String.valueOf(dto.getSchool())) : null);
        user.setMajor(dto.getMajor());
        user.setDepartment(dto.getDepartment());
        user.setRole(User.Role.valueOf(String.valueOf(dto.getRole())));
    }

    private void applyPatchDtoToUser(UserPatchDTO dto, User user) {
        if (dto.getEmail() != null) user.setEmail(dto.getEmail());
        if (dto.getUsername() != null) user.setUsername(dto.getUsername());
        if (dto.getPasswordHash() != null) user.setPasswordHash(dto.getPasswordHash());
        if (dto.getFirstName() != null) user.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null) user.setLastName(dto.getLastName());
        if (dto.getContactInfo() != null) user.setContactInfo(dto.getContactInfo());
        if (dto.getProfilePicture() != null) user.setProfilePicture(dto.getProfilePicture());
        if (dto.getDateOfBirth() != null) user.setDateOfBirth(dto.getDateOfBirth());
        if (dto.getAzureSsoId() != null) user.setAzureSsoId(dto.getAzureSsoId());
        if (dto.getAuthenticationProvider() != null)
            user.setAuthenticationProvider(User.AuthenticationProvider.valueOf(String.valueOf(dto.getAuthenticationProvider())));
        if (dto.getYear() != null) user.setYear(dto.getYear());
        if (dto.getSchool() != null) user.setSchool(User.School.valueOf(String.valueOf(dto.getSchool())));
        if (dto.getMajor() != null) user.setMajor(dto.getMajor());
        if (dto.getDepartment() != null) user.setDepartment(dto.getDepartment());
        if (dto.getRole() != null) user.setRole(User.Role.valueOf(String.valueOf(dto.getRole())));
    }
}

