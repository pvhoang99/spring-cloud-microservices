package com.example.catalog.dao.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "disease")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Disease extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String code;

  private String imageUrl;

  @Column(name = "catalog_id")
  private Long catalogId;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Catalog.class)
  @JoinColumn(name = "catalog_id", referencedColumnName = "id", insertable = false, updatable = false)
  private Catalog catalog;
}