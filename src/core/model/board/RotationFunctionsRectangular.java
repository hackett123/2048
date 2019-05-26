package core.model.board;

import core.util.Direction;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class RotationFunctionsRectangular {

  static Map<Direction, Consumer<ITile[][]>> getDirectionMap() {
    Map<Direction, Consumer<ITile[][]>> map =
        new HashMap<Direction, Consumer<ITile[][]>>();
    map.put(Direction.RIGHT, (t) -> {});
    map.put(Direction.LEFT, RotationFunctionsRectangular::rotate180);
    map.put(Direction.UP, RotationFunctionsRectangular::rotateClockwise90);
    map.put(Direction.DOWN, RotationFunctionsRectangular::rotateClockwise270);

    return map;
  }

  static void rotateClockwise90(ITile[][] tiles) {

  }

  static void rotate180(ITile[][] tiles) {

  }

  static void rotateClockwise270(ITile[][] tiles) {

  }

}
