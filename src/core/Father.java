package core;

import core.controller.GameController;
import core.model.IModel;
import core.model.ModelManager;
import core.view.GUIHandler;
import core.view.IView;

/**
 * Starting point
 */
public class Father {


  private Father() {
    // Instantiate the Model and View, then pass to the Controller
    IModel model = new ModelManager();
    IView view = new GUIHandler();

    new GameController(model, view);
  }

  public static void main(String[] args) {
    // Get outta static
    new Father();
  }

}
