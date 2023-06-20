package com.example.auth.repository.converter;

import com.example.auth.domain.user.Username;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

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
