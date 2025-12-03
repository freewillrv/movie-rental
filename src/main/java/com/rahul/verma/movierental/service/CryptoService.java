package com.rahul.verma.movierental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.rahul.verma.movierental.util.Constants.ENV_HASHING_ITERATIONS;

/**
 * Manages Crypto activities.
 * <a href="https://security.stackexchange.com/questions/211/how-to-securely-hash-passwords">Good Article on Password Hashes.</a>
 *
 */
@Service
public class CryptoService {

    private final Environment environment;
    private final SecretsManager secretsManager;
    private final int iterations;
    private final Pbkdf2PasswordEncoder encoder;

    @Autowired
    public CryptoService(final SecretsManager secretsManager, final Environment environment) {
        this.secretsManager = secretsManager;
        this.environment = environment;
        this.iterations = environment.getRequiredProperty(ENV_HASHING_ITERATIONS, Integer.class);
        this.encoder = Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

    public String hashPassword(final char[] password) {
        return encoder.encode(new String(password));
    }

    public boolean matchPassword(final char[] password, final String encodedInDB) {
        return encoder.matches(new String(password), encodedInDB);
    }

}
