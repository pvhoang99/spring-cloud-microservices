package com.example.common.utils;

import java.security.Principal;
import java.util.Optional;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityContext {

    public static String currentUsername() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
            .map(Principal::getName)
            .orElseThrow();
    }

}
