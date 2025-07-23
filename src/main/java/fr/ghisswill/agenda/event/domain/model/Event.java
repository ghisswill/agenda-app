package fr.ghisswill.agenda.event.domain.model;

import fr.ghisswill.agenda.user.domain.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    public void update(String title, String description, LocalDateTime startTime, LocalDateTime endTime) {
        this.title = title;
        this.description = description;
        this.startDate = startTime;
        this.endDate = endTime;
    }
}
