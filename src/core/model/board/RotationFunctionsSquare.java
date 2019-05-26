package core.model.board;

import core.util.Direction;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class RotationFunctionsSquare {

  static Map<Direction, Consumer<ITile[][]>> getDirectionMap() {
    Map<Direction, Consumer<ITile[][]>> map =
        new HashMap<>();
    map.put(Direction.RIGHT, (t) -> {});
    map.put(Direction.LEFT, RotationFunctionsSquare::rotate180);
    map.put(Direction.UP, RotationFunctionsSquare::rotateClockwise90);
    map.put(Direction.DOWN, RotationFunctionsSquare::rotateClockwise270);

    return map;
  }

  static void rotateClockwise90(ITile[][] tiles) {

  }

  static void rotate180(ITile[][] tiles) {

  }

  static void rotateClockwise270(ITile[][] tiles) {

  }



}
