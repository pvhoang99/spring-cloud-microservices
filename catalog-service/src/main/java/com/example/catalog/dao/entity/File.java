package com.example.catalog.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "FILE")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class File extends BaseEntity {

  @Id
  private String id;

  private String url;
}
