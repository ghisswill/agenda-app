package fr.ghisswill.agenda.event.application.service;

import fr.ghisswill.agenda.event.application.command.CreateEventCommand;
import fr.ghisswill.agenda.event.application.command.UpdateEventCommand;
import fr.ghisswill.agenda.event.domain.model.Event;

import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface EventService {

    Event createEvent(CreateEventCommand command);
    Event updateEvent(UpdateEventCommand command, UUID userId);
    void deleteEvent(Long eventId, UUID userId);
    List<Event> getEventsForUser(UUID userId);
    Event getEventById(Long id, UUID userId);
    List<Event> getEventsInDateRange(UUID userId, LocalDateTime start, LocalDateTime end, Pageable pageable);
}
