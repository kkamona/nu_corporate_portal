package edu.nu.corporate_portal.controllers;

import edu.nu.corporate_portal.DTO.Phonebook.PhonebookGetDTO;
import edu.nu.corporate_portal.DTO.Phonebook.PhonebookPatchDTO;
import edu.nu.corporate_portal.DTO.Phonebook.PhonebookPostDTO;
import edu.nu.corporate_portal.models.Phonebook;
import edu.nu.corporate_portal.services.PhonebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/phonebook")
public class PhonebookController {

    private final PhonebookService phonebookService;

    @Autowired
    public PhonebookController(PhonebookService phonebookService) {
        this.phonebookService = phonebookService;
    }

    @GetMapping
    public ResponseEntity<List<PhonebookGetDTO>> getAllPhonebookEntries() {
        List<Phonebook> entries = phonebookService.getAllPhonebookEntries();
        List<PhonebookGetDTO> dtoList = entries.stream()
                .map(PhonebookGetDTO::new)
                .toList();
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PhonebookGetDTO>> getPhonebookEntriesByUserId(@PathVariable Long userId) {
        List<Phonebook> entries = phonebookService.getPhonebookEntriesByUserId(userId);
        List<PhonebookGetDTO> dtoList = entries.stream()
                .map(PhonebookGetDTO::new)
                .toList();
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<PhonebookGetDTO>> getPhonebookEntriesByType(@PathVariable String type) {
        List<Phonebook> entries = phonebookService.getPhonebookEntriesByType(type);
        List<PhonebookGetDTO> dtoList = entries.stream()
                .map(PhonebookGetDTO::new)
                .toList();
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhonebookGetDTO> getPhonebookEntryById(@PathVariable Long id) {
        Phonebook entry = phonebookService.getPhonebookEntryById(id);
        return ResponseEntity.ok(new PhonebookGetDTO(entry));
    }

    @PostMapping
    public ResponseEntity<PhonebookGetDTO> createPhonebookEntry(@RequestBody PhonebookPostDTO dto) {
        Phonebook created = phonebookService.createPhonebookEntry(dto);
        return ResponseEntity.ok(new PhonebookGetDTO(created));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PhonebookGetDTO> updatePhonebookEntry(@PathVariable Long id, @RequestBody PhonebookPatchDTO dto) {
        Phonebook updated = phonebookService.updatePhonebookEntry(id, dto);
        return ResponseEntity.ok(new PhonebookGetDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhonebookEntry(@PathVariable Long id) {
        phonebookService.deletePhonebookEntry(id);
        return ResponseEntity.noContent().build();
    }
}

