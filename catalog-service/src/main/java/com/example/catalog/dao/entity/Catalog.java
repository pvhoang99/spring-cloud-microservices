package com.example.catalog.dao.entity;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "catalog")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Catalog extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "code")
  private String code;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "catalog", cascade = CascadeType.ALL, targetEntity = Disease.class)
  private List<Disease> disease = new LinkedList<>();

  public Catalog(String name, String code) {
    this.name = name;
    this.code = code;
  }
}
