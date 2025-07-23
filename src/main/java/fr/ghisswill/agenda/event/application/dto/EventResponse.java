package fr.ghisswill.agenda.event.application.dto;

import java.time.LocalDateTime;

public record EventResponse(
        Long id,
        String title,
        String description,
        LocalDateTime startTime,
        LocalDateTime endTime
) {
    public static EventResponse fromEntity(fr.ghisswill.agenda.event.domain.model.Event event) {
        return new EventResponse(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getStartDate(),
                event.getEndDate()
        );
    }

}
