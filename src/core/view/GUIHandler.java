package core.view;

import core.controller.IController;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class GUIHandler implements IView {

  @Override
  public void startGame(IController controller) {
    throw new NotImplementedException();
  }

  @Override
  public void sendMessage(String message) {
    throw new NotImplementedException();
  }
}
