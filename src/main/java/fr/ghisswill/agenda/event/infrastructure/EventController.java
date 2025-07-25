package fr.ghisswill.agenda.event.infrastructure;

import fr.ghisswill.agenda.event.application.command.CreateEventCommand;
import fr.ghisswill.agenda.event.application.command.UpdateEventCommand;
import fr.ghisswill.agenda.event.application.dto.EventResponse;
import fr.ghisswill.agenda.event.application.service.EventService;
import fr.ghisswill.agenda.event.domain.model.Event;
import fr.ghisswill.agenda.user.domain.model.CustomUserDetails;
import fr.ghisswill.agenda.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<EventResponse> create(@RequestBody CreateEventCommand command) {
        EventResponse event = eventService.createEvent(command);
        return ResponseEntity.created(URI.create("/api/events/" + event.id())).body(event);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventResponse> update(
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
    public ResponseEntity<EventResponse> getEventById(@PathVariable Long id, Authentication authentication) {
        return new ResponseEntity<>(eventService.getEventById(id, getUserId(authentication)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EventResponse>> getEvents(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime start,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime end,
            @PageableDefault(size = 10, sort = "startTime") Pageable pageable,
            Authentication authentication
            ) {
        List<EventResponse> events;
        if(start != null && end != null) {
            events = eventService.getEventsInDateRange(getUserId(authentication), start, end, pageable);
        } else {
            events = eventService.getEventsForUser(getUserId(authentication));
        }
        return new ResponseEntity<>( events, HttpStatus.OK);
    }

    @GetMapping("/today")
    public ResponseEntity<List<EventResponse>> getTodayEvents(Authentication authentication) {
        return new ResponseEntity<>(eventService.getTodayEvents(getUserId(authentication)), HttpStatus.OK);
    }

    @GetMapping("/this-week")
    public ResponseEntity<List<EventResponse>> getThisWeekEvents(Authentication authentication) {
        return new ResponseEntity<>(eventService.getThisWeekEvents(getUserId(authentication)), HttpStatus.OK);
    }

    private UUID getUserId(Authentication authentication) {
       if (authentication.getPrincipal() instanceof CustomUserDetails userDetails)
           return userDetails.getUserId();
       return null;
    }
}
