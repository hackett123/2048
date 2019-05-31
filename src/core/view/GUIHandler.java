package core.view;

import core.controller.IController;
import core.util.BoardDimensions;
import core.util.Direction;
import core.util.IHighScore;
import core.util.Rank;
import core.view.gui.GuiRenderer;
import core.view.gui.IGuiRenderer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class GUIHandler implements IView {

  private IController mController;
  private IGuiRenderer mGuiComps;

  private void _startGame(IController controller) {
    if (controller == null) {
      throw new IllegalArgumentException("Null controller provided");
    }
    this.mController = controller;
    mGuiComps = new GuiRenderer();
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
  }

  @Override
  public void acceptAndRenderBoardState(Rank[] ranks) {
    mGuiComps.renderBoardState(ranks);
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
