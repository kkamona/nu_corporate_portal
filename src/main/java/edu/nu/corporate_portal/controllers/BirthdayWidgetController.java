package edu.nu.corporate_portal.controllers;

import edu.nu.corporate_portal.models.BirthdayWidget;
import edu.nu.corporate_portal.services.BirthdayWidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/birthday-widget")
public class BirthdayWidgetController {

    private final BirthdayWidgetService birthdayWidgetService;

    @Autowired
    public BirthdayWidgetController(BirthdayWidgetService birthdayWidgetService) {
        this.birthdayWidgetService = birthdayWidgetService;
    }

    @GetMapping
    public ResponseEntity<List<BirthdayWidget>> getAllWidgets() {
        return ResponseEntity.ok(birthdayWidgetService.getAllWidgets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BirthdayWidget> getWidgetById(@PathVariable Long id) {
        return ResponseEntity.ok(birthdayWidgetService.getWidgetById(id));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<BirthdayWidget>> getWidgetsByBirthDate(@PathVariable String date) {
        LocalDate birthDate = LocalDate.parse(date); // Expecting format "yyyy-MM-dd"
        return ResponseEntity.ok(birthdayWidgetService.getWidgetsByBirthDate(birthDate));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BirthdayWidget>> getWidgetsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(birthdayWidgetService.getWidgetsByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<BirthdayWidget> createWidget(@RequestBody BirthdayWidget widget) {
        return ResponseEntity.ok(birthdayWidgetService.createWidget(widget));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BirthdayWidget> updateWidget(@PathVariable Long id, @RequestBody BirthdayWidget updatedWidget) {
        return ResponseEntity.ok(birthdayWidgetService.updateWidget(id, updatedWidget));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWidget(@PathVariable Long id) {
        birthdayWidgetService.deleteWidget(id);
        return ResponseEntity.noContent().build();
    }
}
