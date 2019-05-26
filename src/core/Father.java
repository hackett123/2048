package core;

import core.controller.GameController;
import core.controller.IController;
import core.model.IModel;
import core.model.ModelManager;
import core.view.CLIHandler;
import core.view.IView;

/**
 * Starting point
 */
public class Father {


  private Father() {
    // Instantiate the Model and View, then pass to the Controller
    IModel model = new ModelManager();
    IView view = new CLIHandler();

    new GameController(model, view);
  }













  public static void main(String[] args) {
    // Get outta static
    new Father();
  }

}
