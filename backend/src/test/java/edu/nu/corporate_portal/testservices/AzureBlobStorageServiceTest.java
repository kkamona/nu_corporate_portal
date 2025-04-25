package edu.nu.corporate_portal.testservices;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import edu.nu.corporate_portal.services.AzureBlobStorageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AzureBlobStorageServiceTest {

    private BlobContainerClient mockContainerClient;
    private BlobClient mockBlobClient;
    private AzureBlobStorageService storageService;

    @BeforeEach
    void setUp() {
        mockContainerClient = mock(BlobContainerClient.class);
        mockBlobClient = mock(BlobClient.class);
        storageService = new AzureBlobStorageService(mockContainerClient);
    }

    @Test
    void testUploadFile_success() throws IOException {
        MultipartFile mockFile = mock(MultipartFile.class);
        when(mockFile.getOriginalFilename()).thenReturn("test.txt");
        when(mockFile.getInputStream()).thenReturn(new ByteArrayInputStream("content for testing".getBytes()));
        when(mockFile.getSize()).thenReturn(12L);

        when(mockContainerClient.getBlobClient(Mockito.anyString())).thenReturn(mockBlobClient);
        when(mockBlobClient.getBlobUrl()).thenReturn("https://fake.blob.core.windows.net/container/test.txt");

        String resultUrl = storageService.uploadFile(mockFile);

        assertNotNull(resultUrl);
        assertTrue(resultUrl.contains("https://fake.blob.core.windows.net/container/"));

        verify(mockBlobClient, times(1)).upload(any(), eq(12L), eq(true));
    }

    @Test
    void testUploadFile_throwsException() throws IOException {
        MultipartFile mockFile = mock(MultipartFile.class);
        when(mockFile.getOriginalFilename()).thenReturn("test.txt");
        when(mockFile.getInputStream()).thenThrow(new IOException("Boom"));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            storageService.uploadFile(mockFile);
        });

        assertTrue(ex.getMessage().contains("Failed to upload file to Azure Blob Storage"));
    }
}
