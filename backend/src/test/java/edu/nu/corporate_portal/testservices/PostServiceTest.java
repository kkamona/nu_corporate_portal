package edu.nu.corporate_portal.testservices;

import edu.nu.corporate_portal.DTO.Post.PostGetDTO;
import edu.nu.corporate_portal.DTO.Post.PostPostDTO;
import edu.nu.corporate_portal.models.Post;
import edu.nu.corporate_portal.models.User;
import edu.nu.corporate_portal.repository.PostRepository;
import edu.nu.corporate_portal.repository.UserRepository;
import edu.nu.corporate_portal.services.AzureBlobStorageService;
import edu.nu.corporate_portal.services.PostService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @Mock
    private PostRepository postRepo;

    @Mock
    private UserRepository userRepo;

    @Mock
    private AzureBlobStorageService storage;

    @InjectMocks
    private PostService postService;

    private PostPostDTO postPostDTO;
    private User user;
    private Post post;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setFirstName("Emily");
        user.setLastName("Paris");

        postPostDTO = new PostPostDTO();
        postPostDTO.setTitle("Test Post Title");
        postPostDTO.setText("Test Post Text");

        post = new Post();
        post.setId(1L);
        post.setTitle("Test Post Title");
        post.setText("Test Post Text");
        post.setUser(user);
    }
    @Test
    void testCreateContent() {
        MultipartFile mockFile = Mockito.mock(MultipartFile.class);
        Mockito.when(mockFile.isEmpty()).thenReturn(false);
        Mockito.when(storage.uploadFile(mockFile)).thenReturn("http://example.com/file1");
        List<MultipartFile> attachments = List.of(mockFile);

        Mockito.when(userRepo.findById(1L)).thenReturn(Optional.of(user));

        // Return the actual saved post (with attachments) instead of your dummy `post`
        Mockito.when(postRepo.save(Mockito.any(Post.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        PostGetDTO postGetDTO = postService.createContent(postPostDTO, attachments, 1L);

        assertNotNull(postGetDTO);
        assertEquals("Test Post Title", postGetDTO.getTitle());
        assertTrue(postGetDTO.getAttachments().contains("http://example.com/file1"));
    }


    @Test
    void testGetContent() {
        // Prepare mock behavior
        Mockito.when(postRepo.findById(1L)).thenReturn(Optional.of(post));

        // Call the method to test
        PostGetDTO postGetDTO = postService.getContent(1L);

        // Assert the results
        Assertions.assertNotNull(postGetDTO);
        Assertions.assertEquals("Test Post Title", postGetDTO.getTitle());

        Mockito.verify(postRepo, Mockito.times(1)).findById(1L);
    }

    @Test
    void testListContents() {
        // Prepare mock behavior
        Pageable pageable = Mockito.mock(Pageable.class);
        Page<Post> postPage = Mockito.mock(Page.class);
        Mockito.when(postRepo.findAll(pageable)).thenReturn(postPage);
        Mockito.when(postPage.map(ArgumentMatchers.any())).thenReturn(Mockito.mock(Page.class));

        // Call the method to test
        Page<PostGetDTO> result = postService.listContents(pageable);

        // Assert the results
        Assertions.assertNotNull(result);
        Mockito.verify(postRepo, Mockito.times(1)).findAll(pageable);
    }

    @Test
    void testUpdateContent() {
        // Prepare mock behavior
        MultipartFile mockFile = Mockito.mock(MultipartFile.class);
        Mockito.when(mockFile.isEmpty()).thenReturn(false);
        Mockito.when(storage.uploadFile(mockFile)).thenReturn("http://example.com/file2");
        List<MultipartFile> attachments = List.of(mockFile);

        // Return the post when searching by ID
        Mockito.when(postRepo.findById(1L)).thenReturn(Optional.of(post));
        Mockito.when(postRepo.save(Mockito.any(Post.class))).thenAnswer(invocation -> invocation.getArgument(0));

        PostPostDTO updatedPostDTO = new PostPostDTO();
        updatedPostDTO.setTitle("Updated Title");
        updatedPostDTO.setText("Updated Text");

        // Call the method
        PostGetDTO updatedPostGetDTO = postService.updateContent(1L, updatedPostDTO, attachments);

        // Assertions
        Assertions.assertNotNull(updatedPostGetDTO);
        Assertions.assertEquals("Updated Title", updatedPostGetDTO.getTitle());
        Assertions.assertTrue(updatedPostGetDTO.getAttachments().contains("http://example.com/file2"));

        Mockito.verify(postRepo, Mockito.times(1)).save(Mockito.any(Post.class));
    }


    @Test
    void testDeleteContent() {
        // Prepare mock behavior
        Mockito.when(postRepo.findById(1L)).thenReturn(Optional.of(post));

        // Call the method to test
        postService.deleteContent(1L);

        // Verify the interaction with the repository
        Mockito.verify(postRepo, Mockito.times(1)).deleteById(1L);
    }
}
