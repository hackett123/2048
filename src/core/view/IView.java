package core.view;

import core.util.BoardDimensions;
import core.util.IHighScore;
import core.util.Rank;
import core.controller.IController;

public interface IView {

  /**
   * To initialize this IView and to accept the IController with which
   * to communicate.
   * @param controller The IController
   */
  void startGame(IController controller);

  /**
   * Resets the game. Needs to be called after {@link #startGame(IController)} to execute properly.
   */
  void resetGame();


  /**
   * To inform the user that the game has ended.
   */
  void signalEndOfGame();

  /**
   * To deliver text to the user through this view's standard output mechanism.
   * @param message The text to display.
   */
  void sendMessage(String message);

  /**
   * To accept the current state of the board and to render each tile on the screen accordingly.
   */
  void acceptAndRenderBoardState(Rank[] ranks);



  void acceptAndRenderHighScores(IHighScore[] highScores);

  /**
   * To prompt the user for a board shape and size and to
   * return the {@link core.util.BoardDimensions} associated
   * with that shape/size.
   * @return The dimensions.
   */
  BoardDimensions promptBoardDimensions();

}
