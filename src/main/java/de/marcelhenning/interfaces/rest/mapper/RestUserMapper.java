package de.marcelhenning.interfaces.rest.mapper;

import de.marcelhenning.app.dto.UserDTO;
import de.marcelhenning.interfaces.rest.dto.UserResponse;

public class RestUserMapper {
    public static UserResponse toResponse(UserDTO userDTO) {
        UserResponse userResponse = new UserResponse();
        userResponse.id = userDTO.id;
        userResponse.firstname = userDTO.firstname;
        userResponse.lastname = userDTO.lastname;
        userResponse.email = userDTO.email;
        userResponse.birthday = userDTO.birthday;
        userResponse.createdAt = userDTO.createdAt;
        userResponse.updatedAt = userDTO.updatedAt;
        return userResponse;
    }
}
