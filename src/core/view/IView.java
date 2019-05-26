package core.view;

import core.controller.IController;

public interface IView {

  /**
   * To initialize this IView and to accept the IController with which
   * to communicate.
   * @param controller The IController
   */
  void init(IController controller);

  /**
   * To deliver text to the user through this view's standard output mechanism.
   * @param message The text to display.
   */
  void sendMessage(String message);
}
