package com.example.auth.dao.model;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Data
public class BaseEntity implements Serializable {

  @Column(name = "created_at",nullable = false,updatable = false)
  @CreatedDate
  private ZonedDateTime createdAt;

  @Column(name = "updated_at",nullable = false)
  @LastModifiedDate
  private ZonedDateTime updatedAt;

  @Column(name = "created_by", nullable = false)
  @CreatedBy
  private Long createdBy;

  @Column(name = "updated_by",nullable = false)
  @LastModifiedBy
  private Long updatedBy;

  @Column(name = "deleted", nullable = false)
  private Short deleted = 0;

  @PrePersist
  protected void prePersist() {
    this.setCreatedAt(ZonedDateTime.now());
    this.setUpdatedAt(ZonedDateTime.now());
  }

  @PreUpdate
  protected void preUpdate() {
    this.setUpdatedAt(ZonedDateTime.now());
  }
}
