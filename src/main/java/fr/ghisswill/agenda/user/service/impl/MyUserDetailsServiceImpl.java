package fr.ghisswill.agenda.user.service.impl;

import fr.ghisswill.agenda.user.domain.repository.UserRepository;
import fr.ghisswill.agenda.user.service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsServiceImpl implements MyUserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .map(user -> User.builder()
                        .username(user.getEmail())
                        .password(user.getPassword())
                        .roles(String.valueOf(user.getRole()))
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));
    }
}
