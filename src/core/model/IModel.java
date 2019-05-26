package core.model;

import core.controller.IController;
import core.util.BoardDimensions;
import core.util.Direction;
import core.util.Rank;

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

  /**
   * take 2D board and convert to a single-dimensional array of the ranks themselves in row-major order.
   * @return rank array representing state of board
   */
  Rank[] relayGameState();

  int getBoardWidth();

  int getBoardHeight();


}
