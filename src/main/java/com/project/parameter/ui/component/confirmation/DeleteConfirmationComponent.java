package com.project.parameter.ui.component.confirmation;

import com.project.parameter.common.I18nMessage;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Window;
import lombok.Data;
import javax.inject.Inject;

/**
 * Created by p.ly on 3/8/2018.
 */
@UIScope
@SpringComponent
@Data
public class DeleteConfirmationComponent extends DeleteConfirmationComponentDesign {

  private DeleteConfirmationEventListener listener;

  @Inject
  public DeleteConfirmationComponent(I18nMessage localize) {
    super(localize);
    addListener();
  }

  private void addListener() {
    closeButton.addClickListener(event -> {
      if (listener != null)
        listener.onClose();
    });

    yesButton.addClickListener(event -> {
      if (listener != null)
        listener.onYes();
    });

    noButton.addClickListener(event -> {
      if (listener != null)
        listener.onNo();
    });
  }

  public interface DeleteConfirmationEventListener {
    void onClose();

    void onYes();

    void onNo();
  }

  public Window displayConfiguration() {
    Window window = new Window();
    window.center();
    window.removeAllCloseShortcuts();
    window.setResizable(false);
    window.setClosable(false);
    window.setModal(true);
    window.setWidth(580, Unit.PIXELS);
    return window;
  }
}
