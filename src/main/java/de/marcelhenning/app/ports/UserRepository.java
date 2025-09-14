package de.marcelhenning.app.ports;

import de.marcelhenning.domain.model.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void save(User user);
    User update(User user);
    Optional<User> findById(String id);
    Optional<User> findByEmail(String email);
    List<User> findAll(int page, int size);
}
