package com.faforever.client.tutorial;

import com.faforever.client.fx.AbstractViewController;
import com.google.common.base.Strings;
import javafx.beans.binding.Bindings;
import javafx.css.PseudoClass;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TutorialListItemController extends AbstractViewController<Node> {

  public static final PseudoClass SELECTED_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");
  public GridPane root;
  public ImageView imageView;
  public Label titleLabel;
  public Label ordinalLabel;
  private Tutorial tutorial;

  @Override
  public Node getRoot() {
    return root;
  }

  public Tutorial getTutorial() {
    return tutorial;
  }

  public void setTutorial(final Tutorial tutorial) {
    this.tutorial = tutorial;
    imageView.imageProperty().bind(Bindings.createObjectBinding(() -> {
      String image = tutorial.getThumbnailUrl();
      if (!Strings.isNullOrEmpty(image)) {
        return new Image(tutorial.getThumbnailUrl());
      }
      return null;
    }, tutorial.thumbnailUrlProperty()));
    titleLabel.textProperty().bind(tutorial.titleProperty());
    ordinalLabel.textProperty().bind(Bindings.createStringBinding(() -> String.valueOf(tutorial.getOrdinal()), tutorial.ordinalProperty()));
  }
}
