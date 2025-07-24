package fr.ghisswill.agenda.event.infrastructure;

import fr.ghisswill.agenda.event.application.command.CreateEventCommand;
import fr.ghisswill.agenda.event.application.command.UpdateEventCommand;
import fr.ghisswill.agenda.event.application.service.EventService;
import fr.ghisswill.agenda.event.domain.model.Event;
import fr.ghisswill.agenda.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static java.time.temporal.WeekFields.ISO;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Event> create(@RequestBody CreateEventCommand command) {
        Event event = eventService.createEvent(command);
        return ResponseEntity.created(URI.create("/api/events/" + event.getId())).body(event);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> update(
            @PathVariable Long id,
            @RequestBody UpdateEventCommand command,
            Authentication authentication) {
        UpdateEventCommand updateEventCommand = new UpdateEventCommand(
                id,
                command.title(),
                command.description(),
                command.startTime(),
                command.endTime()
        );

        return new ResponseEntity<>(eventService.updateEvent(updateEventCommand, getUserId(authentication)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, Authentication authentication) {
        eventService.deleteEvent(id, getUserId(authentication));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id, Authentication authentication) {
        return new ResponseEntity<>(eventService.getEventById(id, getUserId(authentication)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Event>> getEvents(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime start,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime end,
            Authentication authentication
            ) {
        List<Event> events;
        if(start != null && end != null) {
            events = eventService.getEventsInDateRange(getUserId(authentication), start, end);
        } else {
            events = eventService.getEventsForUser(getUserId(authentication));
        }
        return new ResponseEntity<>( events, HttpStatus.OK);
    }

    private UUID getUserId(Authentication authentication) {
       return UUID.fromString(authentication.getName());
    }
}
