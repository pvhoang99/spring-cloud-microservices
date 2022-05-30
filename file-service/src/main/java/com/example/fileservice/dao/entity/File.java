package com.example.fileservice.dao.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "file")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class File extends BaseEntity {

  @Id
  private String id;

  private String url;
}
