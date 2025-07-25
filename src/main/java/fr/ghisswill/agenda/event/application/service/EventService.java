package fr.ghisswill.agenda.event.application.service;

import fr.ghisswill.agenda.event.application.command.CreateEventCommand;
import fr.ghisswill.agenda.event.application.command.UpdateEventCommand;
import fr.ghisswill.agenda.event.application.dto.EventResponse;
import fr.ghisswill.agenda.event.domain.model.Event;

import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface EventService {

    EventResponse createEvent(CreateEventCommand command);
    EventResponse updateEvent(UpdateEventCommand command, UUID userId);
    void deleteEvent(Long eventId, UUID userId);
    List<EventResponse> getEventsForUser(UUID userId);
    EventResponse getEventById(Long id, UUID userId);
    List<EventResponse> getEventsInDateRange(UUID userId, LocalDateTime start, LocalDateTime end, Pageable pageable);
    List<EventResponse> getTodayEvents(UUID userId);
    List<EventResponse> getThisWeekEvents(UUID userId);
}
