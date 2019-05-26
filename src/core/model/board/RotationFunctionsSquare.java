package core.model.board;

import core.util.Coordinate;
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


  static void rotate(Direction direction, ITile[][] board, int size) {
    // Determine how many times to rotate the board CW 90
    int numRotations = direction.ordinal();

    // Get the number of layers for this board size
    // Layers increase OUTWARDS such that the outer perimeter of the square
    // board is the highest layer number.
    int numLayers = (int) Math.ceil(size / 2.0);

    // Perform those rotations
    for (int i = 0; i < numRotations; i++) {
      rotateClockwise90(new Coordinate(0, 0), numLayers - 1, numLayers, size, board);
    }
  }

  private static void rotateClockwise90(Coordinate topLeft, int layer, int numLayers, int size, ITile[][] board) {
    // Check if this is a base case
    if (layer == 0) {
      // Base case: handle accordingly
      rotateClockwise90BaseCase(topLeft, board, size);
    } else {
      // non-base case
      // Determine how many 4-Coordinate swaps we need for this layer
      int layerOffset = (numLayers - layer) - 1;
      int num4CoordinateSwaps = (size - 1) - (2 * layerOffset);

      // For each swap...
      for (int i = 0; i < num4CoordinateSwaps; i++) {
        // Get the starting swap coordinate
        Coordinate startingCoordinate = topLeft.offset(0, i);
        // Perform the swap from that coordinate
        rotateClockwise90Perform4Swaps(startingCoordinate, board, size);
      }

      // Then recur on the next layer
      Coordinate newTopLeft = topLeft.offset(1, 1);
      rotateClockwise90(newTopLeft, layer - 1, numLayers, size, board);
    }
  }

  private static void rotateClockwise90BaseCase(Coordinate topLeft, ITile[][] board, int size) {
    // If the dimensions are odd, do nothing; the center square remains the same
    if (size % 2 == 1) {
      return;
    }

    // Otherwise, just perform the swaps
    rotateClockwise90Perform4Swaps(topLeft, board, size);
  }


  private static void rotateClockwise90Perform4Swaps(Coordinate toSwap, ITile[][] board, int size) {
    // Get the four Coordinates to swap
    Coordinate[] allSwapCoordinates
            = rotateClockwise90GetSwapCoordinates(toSwap, size);

    // Get all four current states
    ITile[] currentState = new Tile[4];
    for (int i = 0; i < 4; i++) {
      Coordinate coordinate = allSwapCoordinates[i];
      currentState[i] = board[coordinate.getX()][coordinate.getY()];
    }

    // Swap all four at once
    for (int i = 0; i < 4; i++) {
      int destIndex = i - 1;
      if (destIndex == -1) {
        destIndex = 3;
      }
      Coordinate destCoord = allSwapCoordinates[destIndex];
      board[destCoord.getX()][destCoord.getY()] = currentState[i];
    }
  }

  /**
   * Return all 4 coordinates to swap, in order, with the given starting coordinate
   * at position 0.
   * @param startingPoint
   * @return
   */
  private static Coordinate[] rotateClockwise90GetSwapCoordinates(Coordinate startingPoint, int size) {
    Coordinate[] coords = new Coordinate[4];

    coords[0] = startingPoint;
    //...
    coords[1] = rotateClockwise90Transform(startingPoint, size);
    coords[2] = rotateClockwise90Transform(coords[1], size);
    coords[3] = rotateClockwise90Transform(coords[2], size);

    return coords;
  }


  private static void swapOnBoard(Coordinate src, Coordinate dest, ITile[][] board) {
    // Swap on the board
    ITile tmp = board[dest.getX()][dest.getY()];
    board[dest.getX()][dest.getY()] = board[src.getX()][src.getY()];
    board[src.getX()][src.getY()] = tmp;
  }


  private static Coordinate rotateClockwise90Transform(Coordinate src, int size) {
    int newX = (size - 1) - src.getY();
    int newY = src.getX();
    return new Coordinate(newX, newY);
  }



}
