package core.controller;

import core.model.IModel;
import core.util.BoardDimensions;
import core.util.Direction;
import core.util.Rank;
import core.view.IView;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * :)
 */
public class GameController implements IController {

  /**
   *
   */
  private IModel mModel;


  private IView mView;

  private boolean mHasLost;
  private boolean mHasWon;


  public GameController(IModel model, IView view) {
    // Store the given parameters
    mModel = model;
    mView = view;
    mHasLost = false;
    mHasWon = false;

    // Tell the model to initialize itself
    mModel.init(this);

    // Tell the view to initialize itself
    mView.startGame(this);

    // Ask the view for board dimensions
    BoardDimensions boardDimensions = mView.promptBoardDimensions();

    // All is good- alert the view that the game is to begin and then start
    // the game logic
    mView.sendMessage("Press an arrow key to start.");

    //initialize the model's board and set starting configuration
    mModel.acceptBoardDimensions(boardDimensions);

    //give w and h to view for rendering details
    mView.acceptBoardDimensions(mModel.getBoardWidth(), mModel.getBoardHeight());


    enterGameLoop();
  }

  private void volleyGameState() {
    //receive and print starting game configuration
    Rank[] gameState = mModel.relayGameState();
    mView.acceptAndRenderBoardState(gameState);
  }

  private void enterGameLoop() {

    volleyGameState();

    while (!mHasLost) {
      Direction turn = mView.promptTurn();
      mModel.performMove(turn);
      volleyGameState();
    }

    //receive from model if in halting configuration
    //TODO : this ^
  }

  @Override
  public void relayDirection(Direction direction) {
    throw new NotImplementedException();
  }

  @Override
  public void relayBoard(Rank[] ranks) {
    throw new NotImplementedException();
  }

  @Override
  public void signalGameWon() {
    throw new NotImplementedException();
  }

  @Override
  public void signalGameOver() {
    throw new NotImplementedException();
  }
}
