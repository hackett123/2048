package core.model;

import core.controller.IController;
import core.util.BoardDimensions;
import core.util.Direction;

public interface IModel {

  /**
   * To initialize this IModel and to accept the IController with which
   * to communicate.
   * @param controller The IController
   */
  void init(IController controller);

  /**
   * Acts to determine what rank must be earned in order to win the game.
   * Also acts as the method in which the model learns of the board size.
   * @param boardDimensions
   */
  void acceptBoardDimensions(BoardDimensions boardDimensions);


  /**
   * To perform a move and update board accordingly.
   * @param direction
   */
  void performMove(Direction direction);
}
