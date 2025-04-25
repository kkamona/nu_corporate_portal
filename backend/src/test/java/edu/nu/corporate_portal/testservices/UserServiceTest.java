package edu.nu.corporate_portal.testservices;

import edu.nu.corporate_portal.DTO.User.UserPatchDTO;
import edu.nu.corporate_portal.models.User;
import edu.nu.corporate_portal.repository.UserRepository;
import edu.nu.corporate_portal.services.AzureBlobStorageService;
import edu.nu.corporate_portal.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

import java.io.IOException;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AzureBlobStorageService storage;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserPatchDTO userPatchDTO;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setFirstName("Rick");
        user.setLastName("Hash");
        user.setEmail("rick.hash@example.com");

        userPatchDTO = new UserPatchDTO();
        userPatchDTO.setFirstName("Jennifer");
        userPatchDTO.setLastName("Vol");
    }

    @Test
    void testGetAllUsers() {
        // Prepare mock behavior
        Mockito.when(userRepository.findAll()).thenReturn(List.of(user));

        // Call the method to test
        var users = userService.getAllUsers();

        // Assert the results
        Assertions.assertNotNull(users);
        Assertions.assertFalse(users.isEmpty());
        Assertions.assertEquals(1, users.size());
        Assertions.assertEquals("Rick", users.get(0).getFirstName());

        Mockito.verify(userRepository, Mockito.times(1)).findAll();
    }

    @Test
    void testGetUserById() {
        // Prepare mock behavior
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Call the method to test
        Optional<User> result = userService.getUserById(1L);

        // Assert the results
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("Rick", result.get().getFirstName());

        Mockito.verify(userRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    void testUpdateUser() throws IOException {
        // Prepare mock behavior
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(user);

        // Call the method to test
        User updatedUser = userService.updateUser(1L, userPatchDTO);

        // Assert the results
        Assertions.assertNotNull(updatedUser);
        Assertions.assertEquals("Jennifer", updatedUser.getFirstName());
        Assertions.assertEquals("Lawrence", updatedUser.getLastName());

        Mockito.verify(userRepository, Mockito.times(1)).save(ArgumentMatchers.any(User.class));
    }

    @Test
    void testUploadProfilePhoto() throws IOException {
        // Prepare mock behavior
        MultipartFile photo = Mockito.mock(MultipartFile.class);
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Mockito.when(storage.uploadFile(photo)).thenReturn("http://example.com/photo.jpg");
        Mockito.when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(user);

        // Call the method to test
        User updatedUser = userService.uploadProfilePhoto(1L, photo);

        // Assert the results
        Assertions.assertNotNull(updatedUser);
        Assertions.assertEquals("http://example.com/photo.jpg", updatedUser.getProfilePicture());

        Mockito.verify(userRepository, Mockito.times(1)).save(ArgumentMatchers.any(User.class));
    }
}
