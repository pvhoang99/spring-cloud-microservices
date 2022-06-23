package com.example.patient.dao.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
  @JsonSerialize(using = ToStringSerializer.class)
  private ObjectId objectId;

  private String fullName;

  private String imageId;

  private String icNumber;

  private String phoneNumber;

  private String fullAddress;

  private boolean isNFT;

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
