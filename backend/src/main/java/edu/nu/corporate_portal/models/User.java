package edu.nu.corporate_portal.models;

import com.nimbusds.openid.connect.sdk.claims.Gender;
import edu.nu.corporate_portal.DTO.Auth.RegistrationDTO;
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
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Setter
    @Enumerated(EnumType.STRING)
    private School school;

    @Setter
    private String major;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Setter
    @Column(name = "hashed_password", nullable = false)
    private String hashedPassword;

    @Setter
    @Column(name = "profile_picture")
    private String profilePicture;

    @Setter
    @Column(name = "show_name", nullable = false)
    private boolean showName = true;

    @Setter
    @Column(name = "show_contact_info", nullable = false)
    private boolean showContactInfo = false;

    @Setter
    @Column(name = "show_date_of_birth", nullable = false)
    private boolean showDateOfBirth = true;

    @Setter
    @Column(name = "show_school", nullable = false)
    private boolean showSchool = true;

    @Setter
    @Column(name = "show_major", nullable = false)
    private boolean showMajor = true;

    @Setter
    @Column(name = "show_profile_picture", nullable = false)
    private boolean showProfilePicture = true;

    @Setter
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Setter
    @Column(name = "interests")
    private String interests;

    @Setter
    @Column(name = "refresh_token")
    private String refreshToken;

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

    public void fromDto(RegistrationDTO registrationDTO, String hashedPassword) {
        this.email = registrationDTO.getEmail();
        this.firstName = registrationDTO.getFirstName();
        this.lastName = registrationDTO.getLastName();
        this.contactInfo = registrationDTO.getContactInfo();
        this.dateOfBirth = registrationDTO.getDateOfBirth();
        this.school = registrationDTO.getSchool();
        this.major = registrationDTO.getMajor();
        this.role = registrationDTO.getRole();
        this.profilePicture = registrationDTO.getProfilePicture();
        this.hashedPassword = hashedPassword;
    }

}
