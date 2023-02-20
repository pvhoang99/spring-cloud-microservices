package com.example.inventory.domain.category;

import com.example.common.domain.AggregateRoot;
import com.example.common.exception.BadRequestException;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Entity(name = "category")
@Table(name = "category")
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
public class Category extends AggregateRoot {

    @Id
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    public static Category of(String name) {
        Category category = new Category();
        category.setId(UUID.randomUUID().toString());
        category.setName(name);

        return category;
    }

    public void update(String name) {
        this.setName(name);
    }

    private void setName(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new BadRequestException("category.name.not.blank");
        }

        this.name = name;
    }

}