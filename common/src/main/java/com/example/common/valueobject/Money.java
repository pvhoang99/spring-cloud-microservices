package com.example.common.valueobject;

import com.example.common.exception.BadRequestException;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter(AccessLevel.PRIVATE)
public class Money {

  private Long money;

  public static Money of(Long money) {
    if (money <= 0) {
      throw new BadRequestException("money.not.less.than.zero");
    }

    return new Money(money);
  }

  public void add(Money money) {
    this.money += money.getMoney();
  }

  public Money mul(Long factor) {
    this.money *= factor;

    return this;
  }

  public Long getValue() {
    return this.money;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Money money1 = (Money) o;
    return Objects.equals(money, money1.money);
  }

  @Override
  public int hashCode() {
    return Objects.hash(money);
  }
}
