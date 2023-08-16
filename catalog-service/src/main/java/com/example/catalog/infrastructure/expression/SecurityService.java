package com.example.catalog.infrastructure.expression;

public interface SecurityService {

    public boolean hasUser(Long id);

    public boolean isOwn(String createdBy);
}
