package edu.nu.corporate_portal.configs;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String externalPath = Paths
                .get(System.getProperty("user.dir"), "uploaded-static", "files", "profilePictures")
                .toUri()
                .toString();

        registry
                .addResourceHandler("/files/profilePictures/**")
                .addResourceLocations(externalPath);
    }


    @PostConstruct
    public void logPaths() {
        String wd = System.getProperty("user.dir");
        System.out.println("▶ Working dir: " + wd);
        Path pics = Paths.get(wd, "uploaded-static", "files", "profilePictures");
        System.out.println("▶ Serving from: " + pics.toAbsolutePath());
        System.out.println("▶ Exists? " + Files.exists(pics));
        try (Stream<Path> s = Files.list(pics)) {
            s.forEach(p -> System.out.println("   • " + p.getFileName()));
        } catch (IOException ignored) {
        }
    }

}

