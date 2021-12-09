package com.example.common.event;

import com.example.common.dto.OrderRequestDto;
import java.util.Date;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class OrderEvent implements Event {

  private UUID eventId = UUID.randomUUID();
  private Date eventDate = new Date();
  private OrderRequestDto orderRequestDto;
  private OrderStatus orderStatus;

  @Override
  public UUID getEventId() {
    return eventId;
  }

  @Override
  public Date getDate() {
    return eventDate;
  }

  public OrderEvent(OrderRequestDto orderRequestDto, OrderStatus orderStatus) {
    this.orderRequestDto = orderRequestDto;
    this.orderStatus = orderStatus;
  }
}
