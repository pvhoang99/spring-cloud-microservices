package com.example.catalog.dao.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "catalog")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Catalog extends BaseEntity {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "code")
  private String code;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "catalog", cascade = CascadeType.ALL)
  private List<Disease> disease;

  public Catalog(String name, String code) {
    this.name = name;
    this.code = code;
  }
}
