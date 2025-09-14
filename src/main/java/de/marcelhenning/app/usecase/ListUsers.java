package de.marcelhenning.app.usecase;

import de.marcelhenning.app.dto.UserDTO;
import de.marcelhenning.app.mapper.UserMapper;
import de.marcelhenning.app.ports.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ListUsers {
    @Inject UserRepository repo;
    public List<UserDTO> execute(int page, int size) {
        return repo.findAll(page, size).stream().map(UserMapper::toDTO).collect(Collectors.toList());
    }
}
