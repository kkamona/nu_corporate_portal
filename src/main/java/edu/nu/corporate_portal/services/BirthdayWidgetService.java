package edu.nu.corporate_portal.services;

import edu.nu.corporate_portal.DTO.BirthdayWidget.BirthdayWidgetPatchDTO;
import edu.nu.corporate_portal.DTO.BirthdayWidget.BirthdayWidgetPostDTO;
import edu.nu.corporate_portal.models.BirthdayWidget;
import edu.nu.corporate_portal.models.User;
import edu.nu.corporate_portal.repository.BirthdayWidgetRepository;
import edu.nu.corporate_portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class BirthdayWidgetService {

    private final BirthdayWidgetRepository birthdayWidgetRepository;
    private final UserRepository userRepository;

    @Autowired
    public BirthdayWidgetService(BirthdayWidgetRepository birthdayWidgetRepository, UserRepository userRepository) {
        this.birthdayWidgetRepository = birthdayWidgetRepository;
        this.userRepository = userRepository;
    }

    public List<BirthdayWidget> getAllWidgets() {
        return birthdayWidgetRepository.findAll();
    }

    public BirthdayWidget getWidgetById(Long id) {
        return birthdayWidgetRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Birthday widget not found"));
    }

    public List<BirthdayWidget> getWidgetsByBirthDate(LocalDate birthDate) {
        return birthdayWidgetRepository.findByBirthDate(birthDate);
    }

    public List<BirthdayWidget> getWidgetsByUserId(Long userId) {
        return birthdayWidgetRepository.findByUser_Id(userId);
    }

    public BirthdayWidget createWidget(BirthdayWidgetPostDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        BirthdayWidget widget = new BirthdayWidget(user, dto.getBirthDate());
        return birthdayWidgetRepository.save(widget);
    }

    public BirthdayWidget updateWidget(Long id, BirthdayWidgetPatchDTO dto) {
        BirthdayWidget widget = getWidgetById(id);

        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
            widget.setUser(user);
        }

        if (dto.getBirthDate() != null) {
            widget.setBirthDate(dto.getBirthDate());
        }

        return birthdayWidgetRepository.save(widget);
    }

    public void deleteWidget(Long id) {
        BirthdayWidget widget = getWidgetById(id);
        birthdayWidgetRepository.delete(widget);
    }

    public boolean userBirthdayWidgetExists(Long id) {
        return !birthdayWidgetRepository.findByUser_Id(id).isEmpty();
    }
}

