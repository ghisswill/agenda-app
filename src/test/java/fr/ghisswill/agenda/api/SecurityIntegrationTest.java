package fr.ghisswill.agenda.api;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.class)
public class SecurityIntegrationTest {

    /*@LocalServerPort
    private int port;

    @Autowired
    private TestTemplate rest;

    @Autowired
    private UserRepository userRepository;

    private final String email = "secure@test.com";
    private final String password = "pass";

    @BeforeEach
    void setup() {
        if (!userRepository.findByEmail(email).isPresent()) {
            User user = new User();
            user.setEmail(email);
            user.setPassword("$2a$10$xJz..."); // mot de passe "pass" encodé (BCrypt)
            user.setRole(Role.USER);
            userRepository.save(user);
        }
    }

    @Test
    @Order(1)
    void loginShouldReturnJwtToken() {
        String url = "http://localhost:" + port + "/api/auth/login";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String body = String.format("{\"email\":\"%s\", \"password\":\"%s\"}", email, password);

        ResponseEntity<Map> response = rest.postForEntity(url, new HttpEntity<>(body, headers), Map.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).containsKey("token");
    }

    @Test
    @Order(2)
    void agendaEndpointShouldBeProtectedAndAccessibleWithToken() {
        // Authentifier l'utilisateur
        String loginUrl = "http://localhost:" + port + "/api/auth/login";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String body = String.format("{\"email\":\"%s\", \"password\":\"%s\"}", email, password);

        ResponseEntity<Map> loginResponse = rest.postForEntity(loginUrl, new HttpEntity<>(body, headers), Map.class);
        String token = (String) loginResponse.getBody().get("token");

        // Appeler l'API sécurisée
        HttpHeaders authHeaders = new HttpHeaders();
        authHeaders.setBearerAuth(token);
        HttpEntity<Void> request = new HttpEntity<>(authHeaders);

        String agendaUrl = "http://localhost:" + port + "/api/agenda";
        ResponseEntity<String> agendaResponse = rest.exchange(agendaUrl, HttpMethod.GET, request, String.class);

        assertThat(agendaResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(agendaResponse.getBody()).contains("Bienvenue dans ton agenda");
    }*/
}
