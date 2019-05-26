package core.model;

import core.controller.IController;
import core.util.Direction;

public interface IModel {

  /**
   * To initialize this IModel and to accept the IController with which
   * to communicate.
   * @param controller The IController
   */
  void init(IController controller);

  /**
   * To perform a move and update board accordingly.
   * @param direction
   */
  void performMove(Direction direction);
}
