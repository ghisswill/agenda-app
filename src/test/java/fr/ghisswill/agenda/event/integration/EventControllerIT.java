package fr.ghisswill.agenda.event.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ghisswill.agenda.auth.dto.AuthRequest;
import fr.ghisswill.agenda.auth.dto.RegisterRequest;
import fr.ghisswill.agenda.event.application.command.CreateEventCommand;
import fr.ghisswill.agenda.event.application.command.UpdateEventCommand;
import fr.ghisswill.agenda.event.domain.model.Event;
import fr.ghisswill.agenda.user.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String jwt;
    private UUID userId;

    @BeforeEach
    void setup() throws Exception {
        // Enregistrement
        /*var register = new RegisterRequest("john@example.com", "password");
        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(register)))
                .andExpect(status().isOk());*/

        // Connexion
       /* var login = new AuthRequest("john@example.com", "password");
        var response = mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(login)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        jwt = objectMapper.readTree(response).get("token").asText();
        userId = UUID.fromString(objectMapper.readTree(response).get("id").asText());*/
    }

    @Test
    void shouldCreateEventWithJwt() throws Exception {
       /* CreateEventCommand command = new CreateEventCommand(
                "Conférence",
                "Présentation technique",
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(1).plusHours(2),
                userId
        );

        mockMvc.perform(post("/api/events")
                        .header("Authorization", "Bearer " + jwt)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isCreated());*/
    }

    @Test
    void shouldUpdateEvent() {
        // given
       /* UUID userId = UUID.randomUUID();
        User user = new User();
        user.setId(userId);

        Event event = new Event();
        event.setId(10L);
        event.setUser(user);

        UpdateEventCommand command = new UpdateEventCommand(
                10L,
                "Titre modifié",
                "Description modifiée",
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(1)
        );

        when(eventRepository.findById(10L)).thenReturn(Optional.of(event));
        when(eventRepository.save(any(Event.class))).thenAnswer(i -> i.getArgument(0));
*/
        // when
       // Event updated = eventService.updateEvent(command, userId);

        // then
       /* assertThat(updated.getTitle()).isEqualTo("Titre modifié");
        verify(eventRepository).save(event);*/
    }

}
