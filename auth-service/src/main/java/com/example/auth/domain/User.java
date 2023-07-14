package com.example.auth.domain;

import com.example.auth.domain.user.Credentials;
import com.example.auth.domain.user.Email;
import com.example.auth.repository.converter.EmailConverter;
import com.example.common.domain.AggregateRoot;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter(AccessLevel.PRIVATE)
@Getter
@Entity
@Table(name = "user")
@NoArgsConstructor
public class User extends AggregateRoot {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Embedded
  private Credentials credentials;

  @Column(name = "full_name")
  private String fullName;

  @Column(name = "email")
  @Convert(converter = EmailConverter.class)
  private Email email;

  @Column(name = "image")
  private String image;

  @Column(name = "role_id")
  private Long roleId;

  public static User create(String username, String plainTextPassword, String fullName,
      String email, String image) {
    User user = new User();
    user.setCredentials(Credentials.create(username, plainTextPassword));
    user.setFullName(fullName);
    user.setEmail(Email.create(email));
    user.setImage(image);

    return user;
  }

  public void update(String fullName, String email, String image) {
    // do something here
  }

  public String getUsername() {
    return this.credentials.getUsername().getValue();
  }

  public String getPassword() {
    return this.credentials.getPassword().getValue();
  }

  public String getEmail() {
    return this.email.getValue();
  }

  public boolean isActive() {
    return this.credentials.isActive();
  }

  public void inactive() {
    this.credentials.setActive(false);
  }

}
