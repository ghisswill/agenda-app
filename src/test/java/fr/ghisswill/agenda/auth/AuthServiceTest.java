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
    void ShouldRegisterUserAndReturnToken() {
        RegisterRequest request = new RegisterRequest("test@ghss.fr","password123");

        when(passwordEncoder.encode("password1223")).thenReturn("encoded_pw");
        when(jwtService.generateToken(any())).thenReturn("jwt_token");

        AuthResponse response = authService.register(request);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());

        User savedUser = userCaptor.getValue();
        assertThat(savedUser.getEmail()).isEqualTo("test@ghss.fr");
        assertThat(savedUser.getRole()).isEqualTo(Role.USER);

        assertThat(response.token()).isEqualTo("jwt_token");
    }
}
