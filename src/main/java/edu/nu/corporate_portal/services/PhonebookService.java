package edu.nu.corporate_portal.services;

import edu.nu.corporate_portal.DTO.Phonebook.PhonebookPatchDTO;
import edu.nu.corporate_portal.DTO.Phonebook.PhonebookPostDTO;
import edu.nu.corporate_portal.models.Phonebook;
import edu.nu.corporate_portal.models.User;
import edu.nu.corporate_portal.repository.PhonebookRepository;
import edu.nu.corporate_portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PhonebookService {

    private final PhonebookRepository phonebookRepository;
    private final UserRepository userRepository;

    @Autowired
    public PhonebookService(PhonebookRepository phonebookRepository, UserRepository userRepository) {
        this.phonebookRepository = phonebookRepository;
        this.userRepository = userRepository;
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

    public Phonebook createPhonebookEntry(PhonebookPostDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Phonebook entry = new Phonebook(
                user,
                dto.getName(),
                dto.getPhoneNumber(),
                dto.getEmail(),
                dto.getDepartment(),
                dto.getType()
        );

        return phonebookRepository.save(entry);
    }

    public Phonebook updatePhonebookEntry(Long id, PhonebookPatchDTO dto) {
        Phonebook existingEntry = getPhonebookEntryById(id);

        if (dto.getName() != null) existingEntry.setName(dto.getName());
        if (dto.getPhoneNumber() != null) existingEntry.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getEmail() != null) existingEntry.setEmail(dto.getEmail());
        if (dto.getDepartment() != null) existingEntry.setDepartment(dto.getDepartment());
        if (dto.getType() != null) existingEntry.setType(dto.getType());

        existingEntry.setUpdatedAt(LocalDateTime.now());

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

