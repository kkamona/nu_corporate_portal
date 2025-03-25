package edu.nu.corporate_portal.DTO.UserRights;

import lombok.Data;

import java.util.Map;

@Data
public class UserRightsUpdateDTO {
    private String assignedRole;
    private Boolean canEditCalendar;
    private Boolean canManageUsers;
    private Boolean canViewStaffInfo;
    private Boolean canViewStudentInfo;
    private Map<String, Object> permissions;
}
