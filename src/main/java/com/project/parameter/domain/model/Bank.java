package com.project.parameter.domain.model;

import com.project.parameter.common.model.AbstractUUIDEntity;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by p.ly on 3/2/2018.
 */
@Entity
@Table(name = "bank")
public class Bank extends AbstractUUIDEntity {

  @NotEmpty
  @Column(name = "name")
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString(){
    return name;
  }
}
