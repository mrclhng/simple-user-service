package de.marcelhenning.app.mapper;

import de.marcelhenning.app.dto.UserDTO;
import de.marcelhenning.domain.model.User;


//Mapstruct
public class UserMapper {
    public static UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.id = user.getId();
        userDTO.firstname = user.getFirstname();
        userDTO.lastname = user.getLastname();
        userDTO.email = user.getEmail();
        userDTO.birthday = user.getBirthday();
        userDTO.createdAt = user.getCreatedAt();
        userDTO.updatedAt = user.getUpdatedAt();
        return userDTO;
    }
}
