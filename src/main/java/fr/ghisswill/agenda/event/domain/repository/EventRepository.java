package fr.ghisswill.agenda.event.domain.repository;

import fr.ghisswill.agenda.event.domain.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByUserId(UUID userId);
}
