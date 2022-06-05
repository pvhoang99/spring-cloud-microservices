package com.example.auth.dao.model;

import java.io.Serializable;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "user")
//@JsonView(Views.User.class)
public class UserEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id", unique = true)
  private Long userId;

  @Column(name = "user_name", unique = true, nullable = false)
  private String username;

  @Column(name = "password", nullable = false, length = 1000)
//  @JsonView(Views.Admin.class)
  private String password;

  @Column(name = "full_name")
  private String fullName;

  @Column(name = "is_active")
  private Boolean isActive = true;

  @Column(name = "email")
//  @JsonView(Views.User.class)
  private String email;

  @Column(name = "image")
  private String image;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "role_id", referencedColumnName = "id")
  private RoleEntity roleEntity;
}
