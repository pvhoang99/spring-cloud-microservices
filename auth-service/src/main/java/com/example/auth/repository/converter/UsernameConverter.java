package com.example.auth.repository.converter;

import com.example.auth.domain.user.Username;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class UsernameConverter implements AttributeConverter<Username, String> {

  @Override
  public String convertToDatabaseColumn(Username username) {
    return username.getValue();
  }

  @Override
  public Username convertToEntityAttribute(String username) {
    return Username.create(username);
  }
}
