package core.controller;

import core.model.IModel;
import core.view.IView;

/**
 * :)
 */
public class GameController implements IController {

  /**
   *
   */
  private IModel mModel;


  private IView mView;


  public GameController(IModel model, IView view) {
    // Store the given parameters
    mModel = model;
    mView = view;

    // Tell the model to initialize itself
    mModel.init(this);

    // Tell the view to initialize itself
    mView.startGame(this);

    // All is good- alert the view that the game is to begin and then start
    // the game logic
    mView.sendMessage("Press an arrow key to start.");
  }

}
