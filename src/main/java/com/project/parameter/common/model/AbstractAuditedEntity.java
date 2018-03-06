package com.project.parameter.common.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by p.ly on 3/2/2018.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class AbstractAuditedEntity implements Serializable {

  @CreatedBy
  @Column(name = "created_by", length = 30, updatable = false)
  private String createdBy;

  @CreatedDate
  @Column(name = "created_date", updatable = false)
  private LocalDateTime createdDate;

  @LastModifiedBy
  @Column(name = "modified_by", length = 30)
  private String modifiedBy;

  @LastModifiedDate
  @Column(name = "modified_date")
  private LocalDateTime modifiedDate;
}