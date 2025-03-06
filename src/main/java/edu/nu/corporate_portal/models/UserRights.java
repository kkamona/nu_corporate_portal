package edu.nu.corporate_portal.models;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnTransformer;
import java.util.Map;

@Entity
@Table(name = "user_rights")
public class UserRights {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_right_id")
    private Long id;

    @Column(nullable = false)
    private String assignedRole;

    @Column(nullable = false)
    private Boolean canEditCalendar = false;

    @Column(nullable = false)
    private Boolean canManageUsers = false;

    @Column(nullable = false)
    private Boolean canViewStaffInfo = false;

    @Column(nullable = false)
    private Boolean canViewStudentInfo = false;

    @Convert(converter = PermissionsConverter.class)
    @Column(name = "permissions", columnDefinition = "jsonb", nullable = false)
    @ColumnTransformer(write = "?::jsonb")
    private Map<String, Object> permissions;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Default constructor
    public UserRights() {}

    // Constructor to assign a user
    public UserRights(User user) {
        this.user = user;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssignedRole() {
        return assignedRole;
    }

    public void setAssignedRole(String assignedRole) {
        this.assignedRole = assignedRole;
    }

    public Boolean getCanEditCalendar() {
        return canEditCalendar;
    }

    public void setCanEditCalendar(Boolean canEditCalendar) {
        this.canEditCalendar = canEditCalendar;
    }

    public Boolean getCanManageUsers() {
        return canManageUsers;
    }

    public void setCanManageUsers(Boolean canManageUsers) {
        this.canManageUsers = canManageUsers;
    }

    public Boolean getCanViewStaffInfo() {
        return canViewStaffInfo;
    }

    public void setCanViewStaffInfo(Boolean canViewStaffInfo) {
        this.canViewStaffInfo = canViewStaffInfo;
    }

    public Boolean getCanViewStudentInfo() {
        return canViewStudentInfo;
    }

    public void setCanViewStudentInfo(Boolean canViewStudentInfo) {
        this.canViewStudentInfo = canViewStudentInfo;
    }

    public Map<String, Object> getPermissions() {
        return permissions;
    }

    public void setPermissions(Map<String, Object> permissions) {
        this.permissions = permissions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
