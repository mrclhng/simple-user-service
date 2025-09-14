package de.marcelhenning.app.usecase;

import de.marcelhenning.app.dto.UserDTO;
import de.marcelhenning.app.mapper.UserMapper;
import de.marcelhenning.domain.model.User;
import de.marcelhenning.app.ports.PasswordHasher;
import de.marcelhenning.app.ports.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.time.LocalDate;

@ApplicationScoped
public class CreateUser {
    @Inject UserRepository repo;
    @Inject PasswordHasher hasher;

    @Transactional
    public UserDTO execute(String firstname, String lastname, String email, LocalDate birthday, String password) {
        repo.findByEmail(email).ifPresent(u -> { throw new IllegalStateException("email_exists"); });
        User user = new User(null);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        user.setBirthday(birthday);
        String salt = hasher.generateSalt();
        user.setPasswordSalt(salt);
        user.setPasswordHash(hasher.hash(password, salt));
        user.touchCreated();
        repo.save(user);
        return UserMapper.toDTO(user);
    }
}
