package de.marcelhenning.domain.model;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

public class User {
    private final String id;
    private String firstname;
    private String lastname;
    private String email;
    private LocalDate birthday;
    private String passwordHash;
    private String passwordSalt;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public User(String id) { this.id = id == null ? UUID.randomUUID().toString() : id; }

    public String getId() { return id; }
    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }
    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public LocalDate getBirthday() { return birthday; }
    public void setBirthday(LocalDate birthday) { this.birthday = birthday; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public String getPasswordSalt() { return passwordSalt; }
    public void setPasswordSalt(String passwordSalt) { this.passwordSalt = passwordSalt; }
    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
    public OffsetDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(OffsetDateTime updatedAt) { this.updatedAt = updatedAt; }

    public void touchCreated() { this.createdAt = OffsetDateTime.now(); this.updatedAt = this.createdAt; }
    public void touchUpdated() { this.updatedAt = OffsetDateTime.now(); }

    @Override public boolean equals(Object o) { if (this == o) return true; if (!(o instanceof User user)) return false;
        return Objects.equals(id, user.id); }
    @Override public int hashCode() { return Objects.hash(id); }
}
