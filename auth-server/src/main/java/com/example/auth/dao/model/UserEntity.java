package com.example.auth.dao.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "user")
public class UserEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id", unique = true)
  private Long userId;

  @Column(name = "user_name", unique = true, nullable = false)
  private String username;

  @Column(name = "password" , nullable = false, length = 1000)
  private String password;

  @Column(name = "full_name")
  private String fullName;

  @Column(name = "is_active")
  private Boolean isActive = true;

  @Column(name = "email")
  @Email
  private String email;

  @Column(name = "role_id")
  private Long roleId;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "role_id",insertable = false,updatable = false)
  private RoleEntity roleEntity;
}
