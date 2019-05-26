package core.view;

import com.sun.tools.corba.se.idl.constExpr.Not;
import core.controller.IController;
import core.util.BoardDimensions;
import core.util.Direction;
import core.util.IHighScore;
import core.util.Rank;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class GUIHandler implements IView {

  private IController mController;

  private void _startGame(IController controller) {
    if (controller == null) {
      throw new IllegalArgumentException("Null controller provided");
    }
    this.mController = controller;

    throw new NotImplementedException();
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
    throw new NotImplementedException();
  }

  @Override
  public void acceptAndRenderBoardState(Rank[] ranks) {
    throw new NotImplementedException();
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
    throw new NotImplementedException();
  }

  @Override
  public Direction promptTurn() {
    throw new NotImplementedException();
  }

  @Override
  public void acceptBoardDimensions(int width, int height) {
    throw new NotImplementedException();
  }
}
