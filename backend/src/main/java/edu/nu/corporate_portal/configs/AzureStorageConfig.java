package edu.nu.corporate_portal.configs;

import com.azure.core.credential.AzureSasCredential;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureStorageConfig {

    @Value("${azure.storage.account-name}")
    private String accountName;

    @Value("${azure.storage.sas-token}")
    private String sasToken;

    @Value("${azure.storage.container-name}")
    private String containerName;

    @Bean
    public BlobContainerClient blobContainerClient() {
        String containerEndpoint = String.format(
                "https://%s.blob.core.windows.net/%s",
                accountName,
                containerName);

        return new BlobContainerClientBuilder()
                .endpoint(containerEndpoint)
                .credential(new AzureSasCredential(sasToken))
                .buildClient();
    }
}
