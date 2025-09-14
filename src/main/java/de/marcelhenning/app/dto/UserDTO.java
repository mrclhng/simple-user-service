package de.marcelhenning.app.dto;

import java.time.LocalDate;
import java.time.OffsetDateTime;

public class UserDTO {
    public String id;
    public String firstname;
    public String lastname;
    public String email;
    public LocalDate birthday;
    public OffsetDateTime createdAt;
    public OffsetDateTime updatedAt;
}
