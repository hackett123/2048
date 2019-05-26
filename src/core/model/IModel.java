package core.model;

import core.controller.IController;

public interface IModel {

  /**
   * To initialize this IModel and to accept the IController with which
   * to communicate.
   * @param controller The IController
   */
  void init(IController controller);
}
