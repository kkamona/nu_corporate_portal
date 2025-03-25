package edu.nu.corporate_portal.services;

import edu.nu.corporate_portal.models.NuCalendar;
import edu.nu.corporate_portal.repository.NuCalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class NuCalendarService {

    private final NuCalendarRepository nuCalendarRepository;

    @Autowired
    public NuCalendarService(NuCalendarRepository nuCalendarRepository) {
        this.nuCalendarRepository = nuCalendarRepository;
    }

    public List<NuCalendar> getAllEvents() {
        return nuCalendarRepository.findAll();
    }

    public NuCalendar getEventById(Long id) {
        return nuCalendarRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));
    }

    public List<NuCalendar> getEventsByDate(LocalDate date) {
        return nuCalendarRepository.findByEventDate(date);
    }

    public NuCalendar createEvent(NuCalendar event) {
        return nuCalendarRepository.save(event);
    }

    public NuCalendar updateEvent(Long id, NuCalendar updatedEvent) {
        NuCalendar existingEvent = getEventById(id);
        existingEvent.setEventName(updatedEvent.getEventName());
        existingEvent.setEventDate(updatedEvent.getEventDate());
        existingEvent.setStartTime(updatedEvent.getStartTime());
        existingEvent.setEndTime(updatedEvent.getEndTime());
        existingEvent.setEventDescription(updatedEvent.getEventDescription());
        // Optionally update createdBy if needed:
        existingEvent.setCreatedBy(updatedEvent.getCreatedBy());
        // Update the updatedAt timestamp
        existingEvent.setUpdatedAt(LocalDateTime.now());
        return nuCalendarRepository.save(existingEvent);
    }

    public void deleteEvent(Long id) {
        NuCalendar existingEvent = getEventById(id);
        nuCalendarRepository.delete(existingEvent);
    }

    public List<NuCalendar> getEventsByCreatedBy(Long id) {
        return nuCalendarRepository.findByCreatedBy(id);
    }

}
