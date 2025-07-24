package fr.ghisswill.agenda.event.application.service.impl;

import fr.ghisswill.agenda.event.application.command.CreateEventCommand;
import fr.ghisswill.agenda.event.application.command.UpdateEventCommand;
import fr.ghisswill.agenda.event.application.service.EventService;
import fr.ghisswill.agenda.event.domain.model.Event;
import fr.ghisswill.agenda.event.domain.repository.EventRepository;
import fr.ghisswill.agenda.user.domain.model.User;
import fr.ghisswill.agenda.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Override
    public Event createEvent(CreateEventCommand command) {
        User user = userRepository.findById(command.userId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Event event = Event.builder()
                .title(command.title())
                .description(command.description())
                .startDate(command.startDate())
                .endDate(command.endDate())
                .user(user)
                .build();

        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(UpdateEventCommand command, UUID userId) {
        Event event = eventRepository.findById(command.eventId())
                .orElseThrow(() -> new RuntimeException("Événement introuvable"));

        if (!event.getUser().getId().equals(userId)) {
            throw new RuntimeException("Accès refusé");
        }

        event.update(command.title(), command.description(), command.startTime(), command.endTime());
        return eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Long eventId, UUID userId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Événement introuvable"));

        if(!event.getUser().getId().equals(userId)) {
            throw new RuntimeException("Accès refusé");
        }
        eventRepository.delete(event);
    }

    @Override
    public List<Event> getEventsForUser(UUID userId) {
        return eventRepository.findAllByUserId(userId);
    }

    @Override
    public Event getEventById(Long id, UUID userId) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Événement non trouvé"));

        if (!event.getUser().getId().equals(userId)) {
            throw new RuntimeException("Accès refusé");
        }

        return event;
    }

}
