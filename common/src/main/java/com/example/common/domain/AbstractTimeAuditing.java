package com.example.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractTimeAuditing implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  @CreatedDate
  @Column(name = "created_at", updatable = false, columnDefinition = "DATETIME")
  @JsonIgnore
  private LocalDateTime createdAt = LocalDateTime.now();

  @LastModifiedDate
  @Column(name = "updated_at", columnDefinition = "DATETIME")
  @JsonIgnore
  private LocalDateTime updatedAt = LocalDateTime.now();

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public AbstractTimeAuditing setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public AbstractTimeAuditing setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
    return this;
  }

}
