package edu.nu.corporate_portal.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(name = "unique_email", columnNames = "email"),
        @UniqueConstraint(name = "unique_azure_sso_id", columnNames = "azure_sso_id")
})
public class User {

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false, unique = true)
    private String email;

    @Setter
    @Column(name = "first_name")
    private String firstName;

    @Setter
    @Column(name = "last_name")
    private String lastName;

    @Setter
    @Column(name = "contact_info")
    private String contactInfo;

    @Setter
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Setter
    @Column(name = "azure_sso_id", unique = true)
    private String azureSsoId;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "authentication_provider", nullable = false)
    private AuthenticationProvider authenticationProvider = AuthenticationProvider.AZURE;

    @Setter
    @Enumerated(EnumType.STRING)
    private School school;

    @Setter
    private String major;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public enum Role {
        STUDENT, ADMIN, PROFESSOR, STAFF
    }

    public enum School {
        SEDS, SSH, SMG, NUSOM, NUFYP, GSB, GSPP, GSE
    }

    public enum AuthenticationProvider {
        AZURE
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                ", school=" + school +
                '}';
    }
}
