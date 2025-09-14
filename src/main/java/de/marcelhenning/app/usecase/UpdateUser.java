package de.marcelhenning.app.usecase;

import de.marcelhenning.domain.model.User;
import de.marcelhenning.app.ports.PasswordHasher;
import de.marcelhenning.app.ports.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.NoSuchElementException;

@ApplicationScoped
public class UpdateUser {
    @Inject UserRepository repo;
    @Inject PasswordHasher hasher;

    @Transactional
    public void execute(String id, String firstname, String lastname, String email, LocalDate birthday, String newPassword) {
        User user = repo.findById(id).orElseThrow(() -> new NoSuchElementException("not_found"));
        if (!user.getEmail().equals(email)) repo.findByEmail(email).ifPresent(x -> { throw new IllegalStateException("email_exists"); });
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        user.setBirthday(birthday);
        if (newPassword != null && !newPassword.isBlank()) {
            String salt = hasher.generateSalt();
            user.setPasswordSalt(salt);
            user.setPasswordHash(hasher.hash(newPassword, salt));
        }
        user.touchUpdated();
        repo.update(user);
    }
}
