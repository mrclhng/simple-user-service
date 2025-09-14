package de.marcelhenning.app.usecase;

import de.marcelhenning.app.dto.UserDTO;
import de.marcelhenning.app.mapper.UserMapper;
import de.marcelhenning.domain.model.User;
import de.marcelhenning.app.ports.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.NoSuchElementException;

@ApplicationScoped
public class GetUser {
    @Inject UserRepository repo;
    public UserDTO execute(String id) {
        User user = repo.findById(id).orElseThrow(() -> new NoSuchElementException("not_found"));
        return UserMapper.toDTO(user);
    }
}
