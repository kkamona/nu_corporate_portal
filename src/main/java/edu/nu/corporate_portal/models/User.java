package edu.nu.corporate_portal.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(name = "unique_email", columnNames = "email"),
        @UniqueConstraint(name = "unique_username", columnNames = "username"),
        @UniqueConstraint(name = "unique_azure_sso_id", columnNames = "azure_sso_id")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "contact_info")
    private String contactInfo;

    @Column(name = "profile_picture")
    private String profilePicture;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "azure_sso_id", unique = true)
    private String azureSsoId;

    @Enumerated(EnumType.STRING)
    @Column(name = "authentication_provider", nullable = false)
    private AuthenticationProvider authenticationProvider = AuthenticationProvider.LOCAL;

    @Column(name = "year")
    private Integer year;

    @Enumerated(EnumType.STRING)
    @Column(name = "school")
    private School school;

    @Column(name = "major")
    private String major;

    @Column(name = "department")
    private String department;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

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
        student, admin, professor, staff
    }


    public enum School {
        SEDS, SSH, SMG, NUSOM, NUFYP, GSB, GSPP, GSE
    }

    public enum AuthenticationProvider {
        LOCAL, AZURE
    }

    public User() {
    }

    public User(String email, String username, String passwordHash, Role role) {
        this.email = email;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getAzureSsoId() {
        return azureSsoId;
    }

    public void setAzureSsoId(String azureSsoId) {
        this.azureSsoId = azureSsoId;
    }

    public AuthenticationProvider getAuthenticationProvider() {
        return authenticationProvider;
    }

    public void setAuthenticationProvider(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                '}';
    }
}
