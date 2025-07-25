package fr.ghisswill.agenda.user.service.impl;

import fr.ghisswill.agenda.user.domain.model.CustomUserDetails;
import fr.ghisswill.agenda.user.domain.repository.UserRepository;
import fr.ghisswill.agenda.user.service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class MyUserDetailsServiceImpl implements MyUserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .map(user -> CustomUserDetails.builder()
                        .userId(user.getId())
                        .username(user.getEmail())
                        .password(user.getPassword())
                        .authorities(Collections.singleton(new SimpleGrantedAuthority(String.valueOf(user.getRole()))))
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));
    }
}
