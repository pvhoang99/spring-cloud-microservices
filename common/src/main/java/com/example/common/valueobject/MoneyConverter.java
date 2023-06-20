package com.example.common.valueobject;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class MoneyConverter implements AttributeConverter<Money, Long> {

  @Override
  public Long convertToDatabaseColumn(Money money) {
    return money.getValue();
  }

  @Override
  public Money convertToEntityAttribute(Long dbData) {
    return Money.of(dbData);
  }
}
