package fr.ghisswill.agenda.event.domain.repository;

import fr.ghisswill.agenda.event.domain.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByUserId(UUID userId);

    @Query("SELECT e FROM Event e WHERE e.user.id = :userId AND e.startTime >= : start AND e.endTime <= :end")
    List<Event> findByUserIdAndDateRange(
            @Param("userId") UUID userId,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );
}
