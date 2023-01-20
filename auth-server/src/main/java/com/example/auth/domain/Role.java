package com.example.auth.domain;

import com.example.common.domain.AggregateRoot;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@Entity
@Table(name = "role")
@NoArgsConstructor
public class Role extends AggregateRoot {

    @Id
    @Column(name = "id")
    private Long id;

    private String code;

    private String name;

    public static Role create(String name, String code) {
        Role role = new Role();
        role.setName(name);
        role.setCode(code);

        return role;
    }

}
