package de.marcelhenning.infra.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(name = "uk_users_email", columnNames = "email"))
public class UserEntity {
    @Id
    @Column(length = 36)
    public String id;
    @Column(nullable = false)
    public String firstname;
    @Column(nullable = false)
    public String lastname;
    @Column(nullable = false, unique = true)
    public String email;
    @Column(nullable = false)
    public LocalDate birthday;
    @Column(nullable = false)
    public String passwordHash;
    @Column(nullable = false)
    public String passwordSalt;
    @Column(nullable = false)
    public OffsetDateTime createdAt;
    @Column(nullable = false)
    public OffsetDateTime updatedAt;
}
