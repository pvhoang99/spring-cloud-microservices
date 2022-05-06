package com.example.orderservice.dao.entity;

import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("invoice")
@AllArgsConstructor
public class Invoice extends BaseEntity {

  @MongoId
  private ObjectId objectId;
  private int totalMoney;
  private InvoiceStatus invoiceStatus;

  public Invoice(int totalMoney,
      InvoiceStatus invoiceStatus) {
    this.totalMoney = totalMoney;
    this.invoiceStatus = invoiceStatus;
  }

  public enum InvoiceStatus {
    CREATED,
    PAID
  }

}
