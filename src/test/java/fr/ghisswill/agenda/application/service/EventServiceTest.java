package fr.ghisswill.agenda.application.service;

import fr.ghisswill.agenda.event.application.command.CreateEventCommand;
import fr.ghisswill.agenda.event.application.dto.EventResponse;
import fr.ghisswill.agenda.event.application.service.impl.EventServiceImpl;
import fr.ghisswill.agenda.event.domain.model.Event;
import fr.ghisswill.agenda.event.domain.repository.EventRepository;
import fr.ghisswill.agenda.user.domain.model.User;
import fr.ghisswill.agenda.user.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EventServiceTest {

    private EventRepository eventRepository;
    private UserRepository userRepository;
    private EventServiceImpl eventServiceImpl;

    @BeforeEach
    void setUp() {
        eventRepository = mock(EventRepository.class);
        userRepository = mock(UserRepository.class);
        eventServiceImpl = new EventServiceImpl(eventRepository,userRepository);
    }

    @Test
    void shouldCreateEvent() {
        // Given
        UUID userId = UUID.randomUUID();
        User usr = new User();
        usr.setId(userId);

        CreateEventCommand command = new CreateEventCommand(
                "Reunion",
                "Reunion Projet",
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(1),
                userId
        );

        when(userRepository.findById(command.userId())).thenReturn(Optional.of(usr));
        when(eventRepository.save(any(Event.class))).thenAnswer(i -> i.getArgument(0));

        // when
        EventResponse event = eventServiceImpl.createEvent(command);

        // then
        assertThat(event).isNotNull();
        assertThat(event.title()).isEqualTo(command.title());
        assertThat(event.userId()).isEqualTo(usr.getId());
        verify(eventRepository).save(any(Event.class));
    }

    @Test
    void shouldDeleteEvent() {
        // given
        UUID userId = UUID.randomUUID();
        Long eventId = 42L;

        User user = new User();
        user.setId(userId);

        Event event = new Event();
        event.setId(eventId);
        event.setUser(user);

        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));

        // when
        eventServiceImpl.deleteEvent(eventId, userId);

        // then
        verify(eventRepository).delete(event);
    }

    @Test
    void shouldReturnAllEventsForUser() {
        UUID userId = UUID.randomUUID();

        Event e1 = new Event(); e1.setId(1L);
        Event e2 = new Event(); e2.setId(2L);

        when(eventRepository.findAllByUserId(userId)).thenReturn(Arrays.asList(e1, e2));

        List<EventResponse> events = eventServiceImpl.getEventsForUser(userId);

        assertThat(events).hasSize(2);
        assertThat(events.get(0).id()).isEqualTo(1L);
    }
}
