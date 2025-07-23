package fr.ghisswill.agenda.auth.service;

import fr.ghisswill.agenda.auth.dto.AuthRequest;
import fr.ghisswill.agenda.auth.dto.AuthResponse;
import fr.ghisswill.agenda.auth.dto.RegisterRequest;

public interface AuthService {

    AuthResponse login(AuthRequest request);
    AuthResponse register(RegisterRequest request);
}
