package com.example.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditing implements Serializable {

  private static final long serialVersionUID = 1L;

  @CreatedDate
  @Column(name = "created_at", updatable = false, columnDefinition = "DATETIME")
  @JsonIgnore
  private LocalDateTime createdAt = LocalDateTime.now();

  @LastModifiedDate
  @Column(name = "updated_at", columnDefinition = "DATETIME")
  @JsonIgnore
  private LocalDateTime updatedAt = LocalDateTime.now();

  @CreatedBy
  @Column(name = "created_by", length = 50, updatable = false)
  @JsonIgnore
  private String createdBy;

  @LastModifiedBy
  @Column(name = "updated_by", length = 50)
  @JsonIgnore
  private String updatedBy;

}
