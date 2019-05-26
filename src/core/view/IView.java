package core.view;

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
   * To deliver text to the user through this view's standard output mechanism.
   * @param message The text to display.
   */
  void sendMessage(String message);

  /**
   * To accept the current state of the board and to render each tile on the screen accordingly.
   */
  void acceptAndRenderBoardState(Rank[] ranks);

  /**
   * To inform the user that the game has ended.
   */
  void signalEndOfGame();


  void acceptHighScores(IHighScore[] highScores);

}
