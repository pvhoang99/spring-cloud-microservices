package com.example.auth.domain.user;

import com.example.auth.service.UserService;
import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

@Getter
@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Aggregate(repository = "userAggregateRepository")
public class User implements Serializable {

    @Id
    @AggregateIdentifier
    @Column(name = "id", unique = true, nullable = false)
    private String id;

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

    @Column(name = "role_id")
    private String roleId;

    public User(String username, String password, String fullName, String email, String image,
        UserService userService) {
        userService.checkUserExisted(username);

        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.image = image;
    }

    public void update(String password, String fullName, String email, String image) {
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.image = image;
    }
}
