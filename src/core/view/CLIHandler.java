package core.view;

import core.controller.IController;

public class CLIHandler implements IView {

  @Override
  public void init(IController controller) {
    // Nothing much to initialize - just print a greeting
    System.out.println("Welcome to 2048 in 20:48");
  }

  @Override
  public void sendMessage(String message) {
    // Just print it to the screen with a newline
    System.out.println(message);
  }
}
