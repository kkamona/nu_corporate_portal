package edu.nu.corporate_portal.controllers;

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
    public ResponseEntity<List<Phonebook>> getAllPhonebookEntries() {
        return ResponseEntity.ok(phonebookService.getAllPhonebookEntries());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Phonebook>> getPhonebookEntriesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(phonebookService.getPhonebookEntriesByUserId(userId));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Phonebook>> getPhonebookEntriesByType(@PathVariable String type) {
        return ResponseEntity.ok(phonebookService.getPhonebookEntriesByType(type));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Phonebook> getPhonebookEntryById(@PathVariable Long id) {
        return ResponseEntity.ok(phonebookService.getPhonebookEntryById(id));
    }

    @PostMapping
    public ResponseEntity<Phonebook> createPhonebookEntry(@RequestBody Phonebook phonebook) {
        return ResponseEntity.ok(phonebookService.createPhonebookEntry(phonebook));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Phonebook> updatePhonebookEntry(@PathVariable Long id, @RequestBody Phonebook updatedEntry) {
        return ResponseEntity.ok(phonebookService.updatePhonebookEntry(id, updatedEntry));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhonebookEntry(@PathVariable Long id) {
        phonebookService.deletePhonebookEntry(id);
        return ResponseEntity.noContent().build();
    }
}
