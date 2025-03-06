package edu.nu.corporate_portal.services;

import edu.nu.corporate_portal.models.UserRights;
import edu.nu.corporate_portal.repository.UserRightsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserRightsService {

    private final UserRightsRepository userRightsRepository;

    @Autowired
    public UserRightsService(UserRightsRepository userRightsRepository) {
        this.userRightsRepository = userRightsRepository;
    }

    public UserRights getUserRightsByUserId(Long userId) {
        return userRightsRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User rights not found"));
    }

    public boolean userRightsExist(Long userId) {
        return userRightsRepository.findByUserId(userId).isPresent();
    }

    public UserRights createUserRights(UserRights userRights) {
        if (userRightsExist(userRights.getUser().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User rights already exist");
        }
        return userRightsRepository.save(userRights);
    }

    public UserRights updateUserRights(Long userId, UserRights updatedRights) {
        UserRights existingRights = getUserRightsByUserId(userId);
        existingRights.setAssignedRole(updatedRights.getAssignedRole());
        existingRights.setCanEditCalendar(updatedRights.getCanEditCalendar());
        existingRights.setCanManageUsers(updatedRights.getCanManageUsers());
        existingRights.setCanViewStaffInfo(updatedRights.getCanViewStaffInfo());
        existingRights.setCanViewStudentInfo(updatedRights.getCanViewStudentInfo());
        existingRights.setPermissions(updatedRights.getPermissions());
        return userRightsRepository.save(existingRights);
    }

    public void deleteUserRights(Long userId) {
        UserRights existingRights = getUserRightsByUserId(userId);
        userRightsRepository.delete(existingRights);
    }
}
