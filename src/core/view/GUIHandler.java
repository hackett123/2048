package core.view;

import core.controller.IController;
import core.util.BoardDimensions;
import core.util.Direction;
import core.util.IHighScore;
import core.util.Rank;
import core.view.gui.GUIComponents;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class GUIHandler implements IView {

  private IController mController;
  private GUIComponents mGuiComps;

  private void _startGame(IController controller) {
    if (controller == null) {
      throw new IllegalArgumentException("Null controller provided");
    }
    this.mController = controller;
    mGuiComps = new GUIComponents(this);
  }

  @Override
  public void startGame(IController controller) {
    _startGame(controller);
  }

  @Override
  public void resetGame() {
    _startGame(mController);
  }

  @Override
  public void sendMessage(String message) {
    //TODO : Implement Real User Experience rather than doing nothing
    return;
  }

  @Override
  public void acceptAndRenderBoardState(Rank[] ranks) {
    mGuiComps.render(ranks);
  }

  @Override
  public void signalEndOfGame() {
    throw new NotImplementedException();
  }

  @Override
  public void acceptAndRenderHighScores(IHighScore[] highScores) {
    throw new NotImplementedException();
  }

  @Override
  public BoardDimensions promptBoardDimensions() {
    //TODO : Implement Real User Experience rather than defaulting to 4x4
    return BoardDimensions.SQUARE4;
  }

  @Override
  public Direction promptTurn() {
    String move = mGuiComps.getMove();
    move = move.toLowerCase();

    Direction direction;
    switch (move) {
      case "w":
        direction = Direction.UP;
        break;
      case "a":
        direction = Direction.LEFT;
        break;
      case "s":
        direction = Direction.DOWN;
        break;
      case "d":
        direction = Direction.RIGHT;
        break;
      default:
        throw new IllegalArgumentException("Impossible input.");
    }
    return direction;
  }

  @Override
  public void acceptBoardDimensions(int width, int height) {
    //TODO : Implement Real User Experience rather than defaulting to 4x4

  }
}
