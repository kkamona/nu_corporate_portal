package edu.nu.corporate_portal.controllers;

import edu.nu.corporate_portal.models.NuCalendar;
import edu.nu.corporate_portal.services.NuCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/calendar")
public class NuCalendarController {

    private final NuCalendarService nuCalendarService;

    @Autowired
    public NuCalendarController(NuCalendarService nuCalendarService) {
        this.nuCalendarService = nuCalendarService;
    }

    @GetMapping
    public ResponseEntity<List<NuCalendar>> getAllEvents() {
        return ResponseEntity.ok(nuCalendarService.getAllEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NuCalendar> getEventById(@PathVariable Long id) {
        return ResponseEntity.ok(nuCalendarService.getEventById(id));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<NuCalendar>> getEventsByDate(@PathVariable String date) {
        // Expects date in ISO format: yyyy-MM-dd
        LocalDate eventDate = LocalDate.parse(date);
        return ResponseEntity.ok(nuCalendarService.getEventsByDate(eventDate));
    }

    @PostMapping
    public ResponseEntity<NuCalendar> createEvent(@RequestBody NuCalendar event) {
        return ResponseEntity.ok(nuCalendarService.createEvent(event));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NuCalendar> updateEvent(@PathVariable Long id, @RequestBody NuCalendar event) {
        return ResponseEntity.ok(nuCalendarService.updateEvent(id, event));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        nuCalendarService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
