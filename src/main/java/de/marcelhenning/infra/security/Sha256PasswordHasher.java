package de.marcelhenning.infra.security;

import de.marcelhenning.app.ports.PasswordHasher;
import jakarta.enterprise.context.ApplicationScoped;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

@ApplicationScoped
public class Sha256PasswordHasher implements PasswordHasher {
    public String generateSalt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
    public String hash(String password, String saltB64) {
        try {
            byte[] salt = Base64.getDecoder().decode(saltB64);
            byte[] pw = password.getBytes(StandardCharsets.UTF_8);
            byte[] input = new byte[salt.length + pw.length];
            System.arraycopy(salt, 0, input, 0, salt.length);
            System.arraycopy(pw, 0, input, salt.length, pw.length);
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return Base64.getEncoder().encodeToString(md.digest(input));
        } catch (Exception e) {
            throw new IllegalStateException("hash_error", e);
        }
    }
}
