package com.example.auth.domain.user;

import com.example.common.domain.ValueObject;
import java.io.Serializable;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Password implements ValueObject {

    private static final int MIN_LENGTH = 6;

    private static final int MAX_LENGTH = 12;

    private final String password;

    public static Password create(String password) {
        return new Password(password);
    }

    private Password(String password) {
        this.password = normalize(password);
        validate(this.password);
    }

    private static void validate(String username) {
        if (username.length() < MIN_LENGTH) {
            throw new RuntimeException();
        }
        if (username.length() > MAX_LENGTH) {
            throw new RuntimeException();
        }
    }

    public static String normalize(String password) {
        return password.trim();
    }

    public String getValue() {
        return this.password;
    }

}
