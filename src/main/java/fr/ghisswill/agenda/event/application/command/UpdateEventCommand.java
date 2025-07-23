package fr.ghisswill.agenda.event.application.command;

import java.time.LocalDateTime;

public record UpdateEventCommand(
        Long eventId,
        String title,
        String description,
        LocalDateTime startTime,
        LocalDateTime endTime
) {
}
