package edu.nu.corporate_portal.services;

import edu.nu.corporate_portal.models.User;
import edu.nu.corporate_portal.models.User.AuthenticationProvider;
import edu.nu.corporate_portal.models.User.Role;
import edu.nu.corporate_portal.models.User.School;
import edu.nu.corporate_portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public Optional<User> findByOid(String oid) {
        return userRepository.findByAzureSsoId(oid);
    }

    public User createAzureUser(String oid, String email, String fullName, String role,
                                String school, String phoneNumber, String major, String birthday) {

        User user = new User();
        user.setAzureSsoId(oid);
        user.setEmail(email);
        user.setFirstName(getFirstName(fullName));
        user.setLastName(getLastName(fullName));
        user.setContactInfo(phoneNumber);
        user.setMajor(major);
        user.setAuthenticationProvider(AuthenticationProvider.AZURE);
        user.setSchool(school != null ? School.valueOf(school.toUpperCase()) : null);
        user.setRole(Role.valueOf(role.toUpperCase()));
        user.setDateOfBirth(LocalDate.parse(birthday));

        return userRepository.save(user);
    }

    private String getFirstName(String fullName) {
        return fullName.split(" ")[0];
    }

    private String getLastName(String fullName) {
        int index = fullName.indexOf(" ");
        return index == -1 ? "" : fullName.substring(index + 1);
    }

}
