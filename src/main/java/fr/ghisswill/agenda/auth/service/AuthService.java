package fr.ghisswill.agenda.auth.service;

import fr.ghisswill.agenda.auth.dto.AuthRequest;
import fr.ghisswill.agenda.auth.dto.AuthResponse;
import fr.ghisswill.agenda.auth.dto.RegisterRequest;

import java.util.UUID;

public interface AuthService {

    AuthResponse login(AuthRequest request);
    UUID register(RegisterRequest request);
}
