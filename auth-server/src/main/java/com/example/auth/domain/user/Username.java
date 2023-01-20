package com.example.auth.domain.user;

import com.example.common.domain.ValueObject;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Username implements ValueObject {

    private static final int MIN_LENGTH = 6;

    private static final int MAX_LENGTH = 12;

    private final String username;

    public static Username create(String username) {
        return new Username(username);
    }

    private Username(String username) {
        this.username = normalize(username);
        validate(this.username);
    }

    private static void validate(String username) {
        if (username.length() < MIN_LENGTH) {
            throw new RuntimeException();
        }
        if (username.length() > MAX_LENGTH) {
            throw new RuntimeException();
        }
    }

    public static String normalize(String username) {
        return username.trim().toLowerCase();
    }

    public String getValue() {
        return this.username;
    }

}
