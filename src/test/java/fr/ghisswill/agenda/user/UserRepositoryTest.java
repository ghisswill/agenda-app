package fr.ghisswill.agenda.user;

import fr.ghisswill.agenda.user.domain.model.Role;
import fr.ghisswill.agenda.user.domain.model.User;
import fr.ghisswill.agenda.user.domain.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldSaveAndFindUserByEmail() {
        User user = User.builder()
                .email("test@ghiss.fr")
                .password("hasard_password")
                .role(Role.USER)
                .build();

        userRepository.save(user);

        Optional<User> result = userRepository.findByEmail(user.getEmail());

        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get().getEmail()).isEqualTo(user.getEmail());
        Assertions.assertThat(result.get().getRole()).isEqualTo(user.getRole());

    }
}
