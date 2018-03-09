package com.project.parameter.ui.component.bank;

import com.project.parameter.common.I18nMessage;
import com.project.parameter.common.model.ERecordStatus;
import com.project.parameter.dataprovider.BankDataProvider;
import com.project.parameter.domain.model.Bank;
import com.project.parameter.ui.component.confirmation.DeleteConfirmationComponent;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.Binder;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by p.ly on 3/3/2018.
 */
@UIScope
@SpringComponent
public class BankComponent extends BankComponentDesign {
  private final Logger logger = LoggerFactory.getLogger(BankComponent.class);

  private final Binder<Bank> bankBinder;
  private final static String FIELD_NOT_BLANK = "field not blank";
  private final static String FIELD_NAME_ID = "name";
  private final static String FIELD_STATUS_ID = "recordStatus";
  private final static String PROPERTY_NAME = "name";
  private final static String DELETE_CAPTION = "Delete";

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
    customBankGrid(bankDataProvider, localizer);
    setComponentCaption(localizer);
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

  private void customBankGrid(BankDataProvider dataProvider, I18nMessage localize) {
    //Deleted bank item
    bankGrid.addComponentColumn(event -> {
      Button deleteButton = createDeleteButton();
      deleteButton.addClickListener(e -> onDeleteItem(dataProvider, localize, event.getId()));
      return deleteButton;
    }).setCaption(DELETE_CAPTION);
  }

  private Button createDeleteButton() {
    Button deleteButton = new Button();
    deleteButton.setStyleName("bank-close-button borderless");
    deleteButton.setIcon(VaadinIcons.TRASH);
    return deleteButton;
  }

  private Binder<Bank> initializeBankBinder() {
    Binder<Bank> result = new BeanValidationBinder<>(Bank.class);
    result.forField(bankTextField).asRequired(FIELD_NOT_BLANK).bind(PROPERTY_NAME);
    result.setBean(new Bank());
    return result;
  }

  private void setComponentCaption(I18nMessage localizer) {
    bankTextField.setCaption(localizer.getMessage("caption.bank"));
    addButton.setCaption(localizer.getMessage("button.add"));
  }

  private void onDeleteItem(BankDataProvider dataProvider, I18nMessage localize, UUID Id){
    DeleteConfirmationComponent deleteConfirmationComponent = new DeleteConfirmationComponent(localize);
    Window popUp = deleteConfirmationComponent.displayConfiguration();
    deleteConfirmationComponent.setListener(new DeleteConfirmationComponent.DeleteConfirmationEventListener() {
      @Override
      public void onClose() {
        popUp.close();
      }

      @Override
      public void onYes() {
        try {
          dataProvider.delete(Id);
        } catch (Exception ex) {
          logger.error("Error while deleting loan purpose", ex);
        }
        popUp.close();
      }

      @Override
      public void onNo() {
        popUp.close();
      }
    });
    popUp.setContent(deleteConfirmationComponent);
    UI.getCurrent().addWindow(popUp);
  }
}
