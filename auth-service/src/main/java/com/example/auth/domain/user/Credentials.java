package com.example.auth.domain.user;

import com.example.auth.repository.converter.PasswordConverter;
import com.example.auth.repository.converter.UsernameConverter;
import com.example.common.domain.ValueObject;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter(AccessLevel.PRIVATE)
@Getter
@Embeddable
@NoArgsConstructor
public class Credentials implements ValueObject {

    @Column(name = "username", nullable = false)
    @Convert(converter = UsernameConverter.class)
    private Username username;

    @Column(name = "password", nullable = false)
    @Convert(converter = PasswordConverter.class)
    private Password password;

    @Column(name = "active")
    private boolean active;

    public static Credentials create(String username, String password) {
        Credentials credentials = new Credentials();
        credentials.setUsername(Username.create(username));
        credentials.setPassword(Password.create(password));
        credentials.setActive(true);

        return credentials;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
