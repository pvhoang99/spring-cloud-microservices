package com.example.catalog.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "FILE")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FileEntity extends BaseEntity {

  @Id
  @GeneratedValue
  private Long id;

  private String fileId;

  private String url;

  public FileEntity(String fileId, String url) {
    this.fileId = fileId;
    this.url = url;
  }
}
