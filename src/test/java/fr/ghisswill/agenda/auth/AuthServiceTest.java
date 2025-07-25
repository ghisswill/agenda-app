package fr.ghisswill.agenda.auth;

import fr.ghisswill.agenda.auth.dto.AuthResponse;
import fr.ghisswill.agenda.auth.dto.RegisterRequest;
import fr.ghisswill.agenda.auth.service.impl.AuthServiceImpl;
import fr.ghisswill.agenda.config.JwtService;
import fr.ghisswill.agenda.user.domain.model.Role;
import fr.ghisswill.agenda.user.domain.model.User;
import fr.ghisswill.agenda.user.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AuthServiceTest {
    private AuthServiceImpl authService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        jwtService = mock(JwtService.class);
        authenticationManager = mock(AuthenticationManager.class);

        authService = new AuthServiceImpl(userRepository, passwordEncoder, jwtService, authenticationManager);
    }

    @Test
    void ShouldRegisterUserAndReturnUserId() {
        RegisterRequest request = new RegisterRequest("test@ghss.fr", "password123");
        UUID userId = UUID.randomUUID(); // sans ID
        User savedUser = new User(userId, request.email(), "encoded_pw", Role.USER);       // avec ID (mock retour)

        when(passwordEncoder.encode("password123")).thenReturn("encoded_pw");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        UUID response = authService.register(request);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());

        User capturedUser = userCaptor.getValue();
        assertThat(capturedUser.getEmail()).isEqualTo("test@ghss.fr");
        assertThat(capturedUser.getRole()).isEqualTo(Role.USER);
        assertThat(capturedUser.getPassword()).isEqualTo("encoded_pw");

        assertThat(response).isEqualTo(userId);
    }

}
