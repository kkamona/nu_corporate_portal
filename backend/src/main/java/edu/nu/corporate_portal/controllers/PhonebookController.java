package edu.nu.corporate_portal.controllers;

import edu.nu.corporate_portal.DTO.User.PhonebookUserDTO;
import edu.nu.corporate_portal.models.User;
import edu.nu.corporate_portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/phonebook")
public class PhonebookController {

    private final UserRepository userRepo;

    @Autowired
    public PhonebookController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    public ResponseEntity<Page<PhonebookUserDTO>> listPhonebook(
            @PageableDefault(size = 20) Pageable pageable
    ) {
        Page<User> users = userRepo.findByShowContactInfoTrue(pageable);

        Page<PhonebookUserDTO> dto = users.map(u ->
                new PhonebookUserDTO(
                        u.getId(),
                        u.getFirstName(),
                        u.getLastName(),
                        u.getContactInfo(),
                        u.getRole()
                )
        );

        return ResponseEntity.ok(dto);
    }
}
