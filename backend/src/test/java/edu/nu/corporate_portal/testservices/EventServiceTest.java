package edu.nu.corporate_portal.testservices;

import edu.nu.corporate_portal.models.Event;
import edu.nu.corporate_portal.repository.EventRepository;
import edu.nu.corporate_portal.services.EventService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;


import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    @Mock
    private EventRepository eventRepository;  // Mock the EventRepository

    @InjectMocks
    private EventService eventService;  // The service to be tested

    private Event event;

    @BeforeEach
    void setUp() {
        event = new Event();
        //event.setId(1L);
        event.setTitle("Test Event");
        event.setDescription("Test Description");
        event.setLocation("Test Location");
        event.setStartDate(LocalDate.now());
        event.setEndDate(LocalDate.now().plusDays(1));
        event.setStartTime("10:00");
        event.setEndTime("12:00");
        event.setPublic(true);
    }

    @Test
    void testListEvents_withDateRange() {
        // Setup mock repository behavior
        LocalDate start = LocalDate.now().minusDays(5);
        LocalDate end = LocalDate.now().plusDays(5);

        // Ensure the stubbing matches the logic in EventService (end, start)
        Mockito.when(eventRepository.findByStartDateBetween(end, start)).thenReturn(List.of(event));

        // Call the method to test
        var events = eventService.listEvents(start, end);

        // Assert the results
        Assertions.assertNotNull(events);
        Assertions.assertEquals(1, events.size());
        Assertions.assertEquals("Test Event", events.get(0).getTitle());

        // Verify that the method was called with the correct arguments (end, start)
        Mockito.verify(eventRepository, Mockito.times(1)).findByStartDateBetween(end, start);
    }



    @Test
    void testListEvents_noDateRange() {
        // Setup mock repository behavior
        Mockito.when(eventRepository.findAll()).thenReturn(List.of(event));

        // Call the method to test
        var events = eventService.listEvents(null, null);

        // Assert the results
        Assertions.assertNotNull(events);
        Assertions.assertEquals(1, events.size());
        Assertions.assertEquals("Test Event", events.get(0).getTitle());

        Mockito.verify(eventRepository, Mockito.times(1)).findAll();
    }

    @Test
    void testCreateEvent() {
        // Setup mock repository behavior
        Mockito.when(eventRepository.save(event)).thenReturn(event);

        // Call the method to test
        Event createdEvent = eventService.createEvent(event);

        // Assert the results
        Assertions.assertNotNull(createdEvent);
        Assertions.assertEquals("Test Event", createdEvent.getTitle());

        Mockito.verify(eventRepository, Mockito.times(1)).save(event);
    }

    @Test
    void testPatchEvent() {
        Event patch = new Event();
        patch.setTitle("Updated Title");

        // Setup mock repository behavior
        Mockito.when(eventRepository.findById(1L)).thenReturn(Optional.of(event));
        Mockito.when(eventRepository.save(event)).thenReturn(event);

        // Call the method to test
        Optional<Event> updatedEvent = eventService.patchEvent(1L, patch);

        // Assert the results
        Assertions.assertTrue(updatedEvent.isPresent());
        Assertions.assertEquals("Updated Title", updatedEvent.get().getTitle());

        Mockito.verify(eventRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(eventRepository, Mockito.times(1)).save(event);
    }

    @Test
    void testDeleteEvent() {
        // Call the method to test
        eventService.deleteEvent(1L);

        // Verify the interaction with the repository
        Mockito.verify(eventRepository, Mockito.times(1)).deleteById(1L);
    }
}
