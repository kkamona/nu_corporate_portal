package edu.nu.corporate_portal.DTO.UserRights;

import edu.nu.corporate_portal.models.UserRights;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class UserRightsGetDTO {
    private Long id;
    private String assignedRole;
    private Boolean canEditCalendar;
    private Boolean canManageUsers;
    private Boolean canViewStaffInfo;
    private Boolean canViewStudentInfo;
    private Map<String, Object> permissions;
    private Long userId;

    // Explicit constructor accepting UserRights
    public UserRightsGetDTO(UserRights userRights) {
        this.id = userRights.getId();
        this.assignedRole = userRights.getAssignedRole();
        this.canEditCalendar = userRights.getCanEditCalendar();
        this.canManageUsers = userRights.getCanManageUsers();
        this.canViewStaffInfo = userRights.getCanViewStaffInfo();
        this.canViewStudentInfo = userRights.getCanViewStudentInfo();
        this.permissions = userRights.getPermissions();
        this.userId = userRights.getUser().getId();
    }
}