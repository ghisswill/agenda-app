package fr.ghisswill.agenda.event.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record EventResponse(
        Long id,
        String title,
        String description,
        LocalDateTime startTime,
        LocalDateTime endTime,
        UUID userId) { }
