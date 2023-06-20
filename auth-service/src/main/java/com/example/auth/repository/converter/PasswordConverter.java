package com.example.auth.repository.converter;

import com.example.auth.domain.user.Password;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

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
