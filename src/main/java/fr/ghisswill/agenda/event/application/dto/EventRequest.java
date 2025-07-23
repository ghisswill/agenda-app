package fr.ghisswill.agenda.event.application.dto;

import fr.ghisswill.agenda.event.application.command.CreateEventCommand;
import fr.ghisswill.agenda.event.application.command.UpdateEventCommand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EventRequest(
        @NotBlank String title,
        String description,
        @NotNull LocalDateTime startTime,
        @NotNull LocalDateTime endTime
) {
    public CreateEventCommand toCreateCommand() {
        return new CreateEventCommand(title, description, startTime, endTime, null);
    }

    public UpdateEventCommand toUpdateCommand() {
        return new UpdateEventCommand(null, title, description, startTime, endTime);
    }
}
