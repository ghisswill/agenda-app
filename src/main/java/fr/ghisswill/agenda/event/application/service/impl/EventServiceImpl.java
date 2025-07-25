package fr.ghisswill.agenda.event.application.service.impl;

import fr.ghisswill.agenda.event.application.command.CreateEventCommand;
import fr.ghisswill.agenda.event.application.command.UpdateEventCommand;
import fr.ghisswill.agenda.event.application.dto.EventResponse;
import fr.ghisswill.agenda.event.application.mapper.EventMapper;
import fr.ghisswill.agenda.event.application.service.EventService;
import fr.ghisswill.agenda.event.domain.model.Event;
import fr.ghisswill.agenda.event.domain.repository.EventRepository;
import fr.ghisswill.agenda.user.domain.model.User;
import fr.ghisswill.agenda.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Override
    public EventResponse createEvent(CreateEventCommand command) {
        User user = userRepository.findById(command.userId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Event event = Event.builder()
                .title(command.title())
                .description(command.description())
                .startTime(command.startDate())
                .endTime(command.endDate())
                .user(user)
                .build();

        return EventMapper.INSTANCE.toDto(eventRepository.save(event));
    }

    @Override
    public EventResponse updateEvent(UpdateEventCommand command, UUID userId) {
        Event event = eventRepository.findById(command.eventId())
                .orElseThrow(() -> new RuntimeException("Événement introuvable"));

        if (!event.getUser().getId().equals(userId)) {
            throw new RuntimeException("Accès refusé");
        }

        event.update(command.title(), command.description(), command.startTime(), command.endTime());
        return EventMapper.INSTANCE.toDto(eventRepository.save(event));
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
    public List<EventResponse> getEventsForUser(UUID userId) {
        return eventRepository.findAllByUserId(userId)
                .stream().map(EventMapper.INSTANCE::toDto).collect(Collectors.toList());
    }

    @Override
    public EventResponse getEventById(Long id, UUID userId) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Événement non trouvé"));

        if (!event.getUser().getId().equals(userId)) {
            throw new RuntimeException("Accès refusé");
        }

        return EventMapper.INSTANCE.toDto(event);
    }

    @Override
    public List<EventResponse> getEventsInDateRange(UUID userId, LocalDateTime start, LocalDateTime end, Pageable pageable) {
        return eventRepository.findByUserIdAndDateRange(userId, start, end, pageable)
                .stream().map(EventMapper.INSTANCE::toDto).collect(Collectors.toList());
    }

    @Override
    public List<EventResponse> getTodayEvents(UUID userId) {
        LocalDateTime start = LocalDate.now().atStartOfDay();
        LocalDateTime end = start.plusDays(1).minusNanos(1);
        return eventRepository.findByUserIdAndDateRange(userId, start, end, null)
                .stream().map(EventMapper.INSTANCE::toDto).collect(Collectors.toList());
    }

    @Override
    public List<EventResponse> getThisWeekEvents(UUID userId) {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = today.with(DayOfWeek.SUNDAY);
        LocalDateTime start = startOfWeek.atStartOfDay();
        LocalDateTime end = endOfWeek.atTime(LocalTime.MAX);
        return eventRepository.findByUserIdAndDateRange(userId, start, end, null)
                .stream().map(EventMapper.INSTANCE::toDto).collect(Collectors.toList());
    }

}
