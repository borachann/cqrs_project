package com.project.parameter.ui.component;

import com.project.parameter.common.I18nMessage;
import com.project.parameter.common.model.ERecordStatus;
import com.project.parameter.dataprovider.BankDataProvider;
import com.project.parameter.domain.model.Bank;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.Binder;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.event.ShortcutAction;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import java.util.Arrays;

/**
 * Created by p.ly on 3/3/2018.
 */
@UIScope
@SpringComponent
public class BankComponent extends BankComponentDesign{
  private final Binder<Bank> bankBinder;
  private final static String FIELD_NOT_BLANK = "field not blank";
  private final static String FIELD_NAME_ID = "name";
  private final static String FIELD_STATUS_ID = "recordStatus";
  private final static String PROPERTY_NAME = "name";

  public BankComponent(BankDataProvider bankDataProvider, I18nMessage localizer) {
    bankBinder = initializeBankBinder();
    bankBinder.addStatusChangeListener(event -> addButton.setEnabled(this.bankBinder.isValid()));

    addButton.addClickListener(event -> {
      bankDataProvider.save(bankBinder.getBean());
      bankBinder.setBean(new Bank());
    });
    // set Enter key as short cut key
    addButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
    initGrid(bankDataProvider);
    setCompoentCaption(localizer);
  }

  private void initGrid(BankDataProvider dataProvider) {
    bankGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
    bankGrid.setDataProvider(dataProvider);
    bankGrid.getEditor().setEnabled(true);

    BeanValidationBinder<Bank> validationBinder = new BeanValidationBinder<>(Bank.class);
    bankGrid.getEditor().setBinder(validationBinder);

    // Set editor of name field
    TextField nameEditor = new TextField();
    Binder.Binding<Bank, String> nameBinding = validationBinder.bind(nameEditor, FIELD_NAME_ID);
    bankGrid.getColumn(FIELD_NAME_ID).setEditorBinding(nameBinding);

    // Set editor of record status field
    ComboBox<ERecordStatus> recordStatusEditor = new ComboBox<>();
    recordStatusEditor.setDataProvider(new ListDataProvider<ERecordStatus>(Arrays.asList(ERecordStatus.values())));
    Binder.Binding<Bank, ERecordStatus> recordStatusBinding = validationBinder.bind(recordStatusEditor, FIELD_STATUS_ID);
    bankGrid.getColumn(FIELD_STATUS_ID).setEditorBinding(recordStatusBinding);
    bankGrid.getEditor().addSaveListener(e -> dataProvider.save(e.getBean()));
  }

  private Binder<Bank> initializeBankBinder() {
    Binder<Bank> result = new BeanValidationBinder<>(Bank.class);
    result.forField(bankTextField).asRequired(FIELD_NOT_BLANK).bind(PROPERTY_NAME);
    result.setBean(new Bank());
    return result;
  }

  private void setCompoentCaption(I18nMessage localizer){
    bankTextField.setCaption(localizer.getMessage("caption.bank"));
    addButton.setCaption(localizer.getMessage("button.add"));
  }
}
