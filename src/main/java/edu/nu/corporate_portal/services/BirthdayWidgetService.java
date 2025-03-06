package edu.nu.corporate_portal.services;

import edu.nu.corporate_portal.models.BirthdayWidget;
import edu.nu.corporate_portal.repository.BirthdayWidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class BirthdayWidgetService {

    private final BirthdayWidgetRepository birthdayWidgetRepository;

    @Autowired
    public BirthdayWidgetService(BirthdayWidgetRepository birthdayWidgetRepository) {
        this.birthdayWidgetRepository = birthdayWidgetRepository;
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

    public BirthdayWidget createWidget(BirthdayWidget widget) {
        return birthdayWidgetRepository.save(widget);
    }

    public BirthdayWidget updateWidget(Long id, BirthdayWidget updatedWidget) {
        BirthdayWidget widget = getWidgetById(id);
        widget.setBirthDate(updatedWidget.getBirthDate());
        widget.setUser(updatedWidget.getUser());
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
