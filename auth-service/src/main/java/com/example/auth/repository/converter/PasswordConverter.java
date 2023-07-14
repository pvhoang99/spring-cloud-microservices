package com.example.auth.repository.converter;

import com.example.auth.domain.user.Password;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PasswordConverter implements AttributeConverter<Password, String> {

  @Override
  public String convertToDatabaseColumn(Password password) {
    return password.getValue();
  }

  @Override
  public Password convertToEntityAttribute(String password) {
    return Password.create(password);
  }
}
