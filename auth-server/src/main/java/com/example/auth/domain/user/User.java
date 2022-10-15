package com.example.auth.domain.user;

import com.example.auth.domain.role.Role;
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
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id", unique = true)
  private Long userId;

  @Column(name = "user_name", unique = true, nullable = false)
  private String username;

  @Column(name = "password", nullable = false, length = 1000)
  private String password;

  @Column(name = "full_name")
  private String fullName;

  @Column(name = "is_active")
  private Boolean isActive = true;

  @Column(name = "email")
  private String email;

  @Column(name = "image")
  private String image;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "role_id", referencedColumnName = "id")
  private Role role;

  public static User create(String username, String password, String fullName, String email, String image, Role role) {
    final User user = new User();
    user.setUsername(username);
    user.setPassword(password);
    user.setFullName(fullName);
    user.setEmail(email);
    user.setImage(image);
    user.setRole(role);
    return user;
  }

}
