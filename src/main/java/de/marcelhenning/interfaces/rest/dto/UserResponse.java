package de.marcelhenning.interfaces.rest.dto;

import java.time.LocalDate;
import java.time.OffsetDateTime;

public class UserResponse {
    public String id;
    public String firstname;
    public String lastname;
    public String email;
    public LocalDate birthday;
    public OffsetDateTime createdAt;
    public OffsetDateTime updatedAt;
}
