package de.marcelhenning.app.ports;

public interface PasswordHasher {
    String generateSalt();
    String hash(String password, String saltB64);
}
