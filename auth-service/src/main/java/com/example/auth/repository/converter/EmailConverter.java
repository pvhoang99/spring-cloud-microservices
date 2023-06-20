package com.example.auth.repository.converter;

import com.example.auth.domain.user.Email;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class EmailConverter implements AttributeConverter<Email, String> {

  @Override
  public String convertToDatabaseColumn(Email email) {
    return email.getValue();
  }

  @Override
  public Email convertToEntityAttribute(String email) {
    return Email.create(email);
  }

}
