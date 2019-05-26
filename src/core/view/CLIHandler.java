package core.view;

import core.controller.IController;
import core.util.IHighScore;
import core.util.Rank;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class CLIHandler implements IView {

  private IController mController;
  //Board


  private void _startGame(IController controller) {
    if (controller == null) {
      throw new IllegalArgumentException("Null controller provided");
    }
    this.mController = controller;
    // Nothing much to initialize - just print a greeting
    System.out.println("Welcome to 2048 in 20:48");
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
    // Just print it to the screen with a newline
    System.out.println(message);
  }

  @Override
  public void acceptAndRenderBoardState(Rank[] ranks) {

    //check for nulls
    for (Rank rank : ranks) {
      if (rank == null) {
        throw new IllegalArgumentException("Provided null rank in array");
      }
    }


  }

  @Override
  public void signalEndOfGame() {
    throw new NotImplementedException();
  }

  @Override
  public void acceptAndRenderHighScores(IHighScore[] highScores) {
    throw new NotImplementedException();
  }
}
