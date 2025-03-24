package edu.nu.corporate_portal.controllers;

import edu.nu.corporate_portal.DTO.BirthdayWidget.BirthdayWidgetGetDTO;
import edu.nu.corporate_portal.DTO.BirthdayWidget.BirthdayWidgetPatchDTO;
import edu.nu.corporate_portal.DTO.BirthdayWidget.BirthdayWidgetPostDTO;
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
    public ResponseEntity<List<BirthdayWidgetGetDTO>> getAllWidgets() {
        List<BirthdayWidgetGetDTO> list = birthdayWidgetService.getAllWidgets()
                .stream()
                .map(BirthdayWidgetGetDTO::new)
                .toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BirthdayWidgetGetDTO> getWidgetById(@PathVariable Long id) {
        return ResponseEntity.ok(new BirthdayWidgetGetDTO(birthdayWidgetService.getWidgetById(id)));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<BirthdayWidgetGetDTO>> getWidgetsByBirthDate(@PathVariable String date) {
        LocalDate birthDate = LocalDate.parse(date);
        List<BirthdayWidgetGetDTO> list = birthdayWidgetService.getWidgetsByBirthDate(birthDate)
                .stream()
                .map(BirthdayWidgetGetDTO::new)
                .toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BirthdayWidgetGetDTO>> getWidgetsByUserId(@PathVariable Long userId) {
        List<BirthdayWidgetGetDTO> list = birthdayWidgetService.getWidgetsByUserId(userId)
                .stream()
                .map(BirthdayWidgetGetDTO::new)
                .toList();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<BirthdayWidgetGetDTO> createWidget(@RequestBody BirthdayWidgetPostDTO dto) {
        BirthdayWidget created = birthdayWidgetService.createWidget(dto);
        return ResponseEntity.ok(new BirthdayWidgetGetDTO(created));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BirthdayWidgetGetDTO> updateWidget(@PathVariable Long id, @RequestBody BirthdayWidgetPatchDTO dto) {
        BirthdayWidget updated = birthdayWidgetService.updateWidget(id, dto);
        return ResponseEntity.ok(new BirthdayWidgetGetDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWidget(@PathVariable Long id) {
        birthdayWidgetService.deleteWidget(id);
        return ResponseEntity.noContent().build();
    }
}

