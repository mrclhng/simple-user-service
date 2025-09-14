package de.marcelhenning.interfaces.rest.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class UserUpdateRequest {
    @NotBlank public String firstname;
    @NotBlank public String lastname;
    @NotBlank @Email public String email;
    @NotNull @Past public LocalDate birthday;
    @Size(min = 8) public String password;
}
