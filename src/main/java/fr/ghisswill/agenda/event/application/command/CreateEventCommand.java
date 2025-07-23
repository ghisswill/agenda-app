package fr.ghisswill.agenda.event.application.command;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateEventCommand(
        String title,
        String description,
        LocalDateTime startDate,
        LocalDateTime endDate,
        UUID userId
) {
}
