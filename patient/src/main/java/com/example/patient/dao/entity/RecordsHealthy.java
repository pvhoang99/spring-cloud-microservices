package com.example.patient.dao.entity;

import java.util.LinkedList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("patient")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RecordsHealthy extends BaseEntity {

  @MongoId
  private ObjectId objectId;

  private String fullName;

  private String imageUrl;

  private String icNumber;

  private String phoneNumber;

  private String fullAddress;

  @Transient
  private String diseaseIds;

  private List<Disease> diseases = new LinkedList<>();

  @Getter
  @Setter
  @NoArgsConstructor
  @Builder
  @AllArgsConstructor
  public static class Disease {

    private String name;

    private String code;

    private String imageUrl;
  }

}
