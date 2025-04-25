package edu.nu.corporate_portal.controllers;

import edu.nu.corporate_portal.DTO.Event.EventGetDTO;
import edu.nu.corporate_portal.DTO.Event.EventPatchDTO;
import edu.nu.corporate_portal.DTO.Event.EventPostDTO;
import edu.nu.corporate_portal.models.Event;
import edu.nu.corporate_portal.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public ResponseEntity<List<EventGetDTO>> list(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate start,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate end
    ) {
        List<EventGetDTO> dtos = eventService.listEvents(start, end)
                .stream()
                .map(EventGetDTO::new)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<EventGetDTO> create(@Valid @RequestBody EventPostDTO dto) {
        Event e = new Event();
        e.setTitle(dto.getTitle());
        e.setDescription(dto.getDescription());
        e.setLocation(dto.getLocation());
        e.setStartDate(dto.getStartDate());
        e.setEndDate(dto.getEndDate());
        e.setStartTime(dto.getStartTime());
        e.setEndTime(dto.getEndTime());
        e.setPublic(dto.getIsPublic());
        if (dto.getTargetRoles() != null) e.setTargetRoles(dto.getTargetRoles());
        if (dto.getTargetSchools() != null) e.setTargetSchools(dto.getTargetSchools());

        Event saved = eventService.createEvent(e);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new EventGetDTO(saved));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<EventGetDTO> patch(
            @PathVariable Long id,
            @Valid @RequestBody EventPatchDTO dto
    ) {
        Event patched = new Event();
        patched.applyPatch(dto);

        return eventService.patchEvent(id, patched)
                .map(EventGetDTO::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}