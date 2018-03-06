package com.project.parameter.domain.dao;

import com.project.parameter.common.model.ERecordStatus;
import com.project.parameter.domain.model.Bank;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.UUID;

/**
 * Created by p.ly on 3/2/2018.
 */
public interface BankRepository extends CrudRepository<Bank, UUID>{
  List<Bank>findAllByRecordStatus(ERecordStatus recordStatus);
}
