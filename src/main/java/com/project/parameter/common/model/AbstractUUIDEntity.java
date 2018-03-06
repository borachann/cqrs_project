package com.project.parameter.common.model;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by p.ly on 3/2/2018.
 */
@MappedSuperclass
@Data
public abstract class AbstractUUIDEntity extends AbstractAuditedEntity {

  @Id
  @GeneratedValue
  private UUID id;

  @NotNull(message = "{fieldValidation.notNull}")
  @Column(name = "record_status", length = 10)
  @Enumerated(EnumType.STRING)
  private ERecordStatus recordStatus;

  @Column(name = "inactivated_date")
  private LocalDateTime inactivatedDate;

  @Column(name = "inactivated_by", length = 30)
  private String inactivatedBy;

  @Version
  @Column
  private Integer version;

  @PrePersist
  void prePersist() {
    if (recordStatus == null)
      recordStatus = ERecordStatus.ACTIVE;
  }
}