package edu.nu.corporate_portal.services;

import edu.nu.corporate_portal.models.Phonebook;
import edu.nu.corporate_portal.repository.PhonebookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PhonebookService {

    private final PhonebookRepository phonebookRepository;

    @Autowired
    public PhonebookService(PhonebookRepository phonebookRepository) {
        this.phonebookRepository = phonebookRepository;
    }

    public List<Phonebook> getAllPhonebookEntries() {
        return phonebookRepository.findAll();
    }

    public Phonebook getPhonebookEntryById(Long id) {
        return phonebookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Phonebook entry not found"));
    }

    public List<Phonebook> getPhonebookEntriesByUserId(Long userId) {
        return phonebookRepository.findByUserId(userId);
    }

    public List<Phonebook> getPhonebookEntriesByType(String type) {
        return phonebookRepository.findByType(type);
    }

    public Phonebook createPhonebookEntry(Phonebook phonebook) {
        return phonebookRepository.save(phonebook);
    }

    public Phonebook updatePhonebookEntry(Long id, Phonebook updatedEntry) {
        Phonebook existingEntry = getPhonebookEntryById(id);
        existingEntry.setUser(updatedEntry.getUser());
        existingEntry.setName(updatedEntry.getName());
        existingEntry.setDepartment(updatedEntry.getDepartment());
        existingEntry.setPhoneNumber(updatedEntry.getPhoneNumber());
        existingEntry.setEmail(updatedEntry.getEmail());
        existingEntry.setType(updatedEntry.getType());
        return phonebookRepository.save(existingEntry);
    }

    public void deletePhonebookEntry(Long id) {
        Phonebook existingEntry = getPhonebookEntryById(id);
        phonebookRepository.delete(existingEntry);
    }

    public boolean userPhonebookExists(Long userId) {
        List<Phonebook> entries = phonebookRepository.findByUserId(userId);
        return entries != null && !entries.isEmpty();
    }
}
