package com.example.common.utils;

import java.security.Principal;
import java.util.Optional;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityContext {

    public static String currentUsername() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
            .map(Principal::getName)
            .orElseThrow();
    }

}
