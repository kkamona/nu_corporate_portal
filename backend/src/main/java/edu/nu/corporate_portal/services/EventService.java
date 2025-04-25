package edu.nu.corporate_portal.services;

import edu.nu.corporate_portal.models.Event;
import edu.nu.corporate_portal.repository.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EventService {

    private final EventRepository repo;

    public EventService(EventRepository repo) {
        this.repo = repo;
    }

    public List<Event> listEvents(LocalDate start, LocalDate end) {
        if (start != null && end != null) {
            return repo.findByStartDateBetween(end, start);
        }
        return repo.findAll();
    }

    public Event createEvent(Event e) {
        return repo.save(e);
    }

    public Optional<Event> patchEvent(Long id, Event patch) {
        return repo.findById(id).map(ev -> {
            if (patch.getTitle() != null)        ev.setTitle(patch.getTitle());
            if (patch.getDescription() != null)  ev.setDescription(patch.getDescription());
            if (patch.getLocation() != null)     ev.setLocation(patch.getLocation());
            if (patch.getStartDate() != null)    ev.setStartDate(patch.getStartDate());
            if (patch.getEndDate() != null)      ev.setEndDate(patch.getEndDate());
            if (patch.getStartTime() != null) ev.setStartTime(patch.getStartTime());
            if (patch.getEndTime()   != null) ev.setEndTime(patch.getEndTime());
            ev.setPublic(patch.isPublic());
            if (!patch.getTargetRoles().isEmpty())   ev.setTargetRoles(patch.getTargetRoles());
            if (!patch.getTargetSchools().isEmpty()) ev.setTargetSchools(patch.getTargetSchools());
            return repo.save(ev);
        });
    }

    public void deleteEvent(Long id) {
        repo.deleteById(id);
    }
}
