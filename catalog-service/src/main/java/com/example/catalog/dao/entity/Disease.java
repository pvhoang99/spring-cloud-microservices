package com.example.catalog.dao.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "disease")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Disease extends BaseEntity {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  private String code;

  @Transient
  private Long catalogId;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "catalog_id", referencedColumnName = "id")
  private Catalog catalog;
}