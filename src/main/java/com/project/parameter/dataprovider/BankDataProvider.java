package com.project.parameter.dataprovider;

import com.project.parameter.domain.dao.BankRepository;
import com.project.parameter.domain.model.Bank;
import com.vaadin.data.provider.AbstractBackEndDataProvider;
import com.vaadin.data.provider.Query;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.UUID;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by p.ly on 3/2/2018.
 */
@Component
public class BankDataProvider extends AbstractBackEndDataProvider<Bank, String>{

  private final BankRepository repository;
  @Inject
  public BankDataProvider(BankRepository repository) {
    this.repository = repository;
  }

  @Override
  protected Stream<Bank> fetchFromBackEnd(Query<Bank, String> query) {
    return StreamSupport.stream(repository.findAll().spliterator(), true);
  }

  @Override
  protected int sizeInBackEnd(Query<Bank, String> query) {
    return Math.toIntExact(repository.count());
  }

  public void save(Bank bean){
    repository.save(bean);
    refreshAll();
  }

  public void delete(UUID Id){
    repository.delete(Id);
    refreshAll();
  }
}
