package fr.ghisswill.agenda.auth.service.impl;

import fr.ghisswill.agenda.auth.dto.AuthRequest;
import fr.ghisswill.agenda.auth.dto.AuthResponse;
import fr.ghisswill.agenda.auth.dto.RegisterRequest;
import fr.ghisswill.agenda.auth.service.AuthService;
import fr.ghisswill.agenda.config.JwtService;
import fr.ghisswill.agenda.user.domain.model.Role;
import fr.ghisswill.agenda.user.domain.model.User;
import fr.ghisswill.agenda.user.domain.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        authenticationManager.authenticate(authToken);

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new AuthResponse(jwtService.generateToken(user));
    }

    @Override
    public UUID register(RegisterRequest request) {
        String encodedPw = passwordEncoder.encode(request.password());
        User user = User.builder()
                .email(request.email())
                .password(encodedPw)
                .role(Role.USER)
                .build();
        return userRepository.save(user).getId();
    }
}
