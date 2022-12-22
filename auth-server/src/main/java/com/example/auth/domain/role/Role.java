package com.example.auth.domain.role;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

@Getter
@Setter
@Entity
@Aggregate(repository = "roleAggregateRepository")
@Table(name = "role")
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {

    @Id
    @Column(name = "id")
    @AggregateIdentifier
    private String id;

    private String value;

    private String name;

    public Role(String value, String name) {
        this.value = value;
        this.name = name;
    }
}
