package com.example.auth.dao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "role")
//@JsonView(Views.Admin.class)
public class RoleEntity implements Serializable {

  public RoleEntity(String value, String name) {
    this.value = value;
    this.name = name;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String value;

  private String name;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "roleEntity")
  @JsonIgnore
  private Set<UserEntity> userEntities;

}
