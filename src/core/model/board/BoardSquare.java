package core.model.board;

import core.util.BoardDimensions;
import core.util.Coordinate;
import core.util.Direction;
import core.util.Rank;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class BoardSquare implements IBoard {

    private ITile[][] mBoard;

    //constants : we consider a probability distribution for all ranks as a later feature.
    private static final double RANK_I_PROBABILITY = 0.9;
    private static final double RANK_II_PROBABILITY = 0.1;

    private int mBoardWidth;
    private int mBoardHeight;

    @Override
    public int getBoardWidth() {
        if (this.mBoardWidth == 0) {
            throw new IllegalArgumentException("Called before board has been initialized");
        }
        return this.mBoardWidth;
    }

    @Override
    public int getBoardHeight() {
        if (this.mBoardHeight == 0) {
            throw new IllegalArgumentException("Called before board has been initialized");
        }
        return this.mBoardHeight;
    }

    @Override
    public void performMove(Direction direction) {

    }

    @Override
    public void rotate(Direction direction) {
      // Determine how many times to rotate the board CW 90
      int numRotations = direction.ordinal();

      // Get the number of layers for this board size
      // Layers increase OUTWARDS such that the outer perimeter of the square
      // board is the highest layer number.
      int numLayers = (int) Math.ceil(mBoardWidth / 2.0);

      // Perform those rotations
      for (int i = 0; i < numRotations; i++) {
        rotateClockwise90(new Coordinate(0, 0), numLayers, numLayers);
      }
    }

    private void rotateClockwise90(Coordinate topLeft, int layer, int numLayers) {
      // Check if this is a base case
      if (layer == 0) {
        // Base case: handle accordingly
        rotateClockwise90BaseCase(topLeft);
      } else {
        // non-base case
        // Determine how many 4-Coordinate swaps we need for this layer
        int layerOffset = (numLayers - layer);
        int num4CoordinateSwaps = (mBoardWidth - 1) - (2 * layerOffset);

        // For each swap...
        for (int i = 0; i < num4CoordinateSwaps; i++) {
          // Get the starting swap coordinate
          Coordinate startingCoordinate = topLeft.offset(0, i);
          // Perform the swap from that coordinate
          rotateClockwise90Perform4Swaps(startingCoordinate);
        }

        // Then recur on the next layer
        Coordinate newTopLeft = topLeft.offset(1, 1);
        rotateClockwise90(newTopLeft, layer - 1, numLayers);
      }
    }

    private void rotateClockwise90BaseCase(Coordinate topLeft) {
      // If the dimensions are odd, do nothing; the center square remains the same
      if (mBoardWidth % 2 == 1) {
        return;
      }

      // Otherwise, just perform the swaps
      rotateClockwise90Perform4Swaps(topLeft);
    }


    private void rotateClockwise90Perform4Swaps(Coordinate toSwap) {
      // Get the four Coordinates to swap
      Coordinate[] allSwapCoordinates
          = rotateClockwise90GetSwapCoordinates(toSwap);

      // Get all four current states
      ITile[] currentState = new Tile[4];
      for (int i = 0; i < 4; i++) {
        Coordinate coordinate = allSwapCoordinates[i];
        currentState[i] = mBoard[coordinate.getX()][coordinate.getY()];
      }

      // Swap all four at once
      for (int i = 0; i < 4; i++) {
        int destIndex = (i + 1) % 4;
        Coordinate destCoord = allSwapCoordinates[destIndex];
        mBoard[destCoord.getX()][destCoord.getY()] = currentState[i];
      }
    }

  /**
   * Return all 4 coordinates to swap, in order, with the given starting coordinate
   * at position 0.
   * @param startingPoint
   * @return
   */
    private Coordinate[] rotateClockwise90GetSwapCoordinates(Coordinate startingPoint) {
      Coordinate[] coords = new Coordinate[4];

      coords[0] = startingPoint;
      //...
      coords[1] = rotateClockwise90Transform(startingPoint);
      coords[2] = rotateClockwise90Transform(coords[1]);
      coords[3] = rotateClockwise90Transform(coords[2]);

      return coords;
    }


    private void swapOnBoard(Coordinate src, Coordinate dest) {
      // Swap on the board
      ITile tmp = mBoard[dest.getX()][dest.getY()];
      mBoard[dest.getX()][dest.getY()] = mBoard[src.getX()][src.getY()];
      mBoard[src.getX()][src.getY()] = tmp;
    }


    private Coordinate rotateClockwise90Transform(Coordinate src) {
      int newX = mBoardWidth - src.getY();
      int newY = src.getX();
      return new Coordinate(newX, newY);
    }


    @Override
    public Rank[] getBoard() {
        throw new NotImplementedException();
    }

    @Override
    public void acceptAndUpdateBoardState(Rank[] ranks) {
        throw new NotImplementedException();
    }

    private void setInitialBoardState() {
        //de-nullifys all tile objects in board.
        for (int i = 0; i < mBoard.length; i++) {
            for (int j = 0; j < mBoard[i].length; j++) {
                mBoard[i][j] = new Tile(Rank.EMPTY);
            }
        }

        /**
         * we choose two starting tiles arbitrarily and at random with a weighted
         * probability towards rank I and rank II only
         */
        Rank firstTileRank = Math.random() < RANK_I_PROBABILITY ? Rank.I : Rank.II;
        Rank secondTileRank = Math.random() < RANK_I_PROBABILITY ? Rank.I : Rank.II;
        int xOne = (int) (Math.random() * mBoardWidth);
        int yOne = (int) (Math.random() * mBoardHeight);
        int xTwo = 0;
        int yTwo = 0;
        boolean differentTile = false;
        while (!differentTile) {
            xTwo = (int) (Math.random() * mBoardWidth);
            yTwo = (int) (Math.random() * mBoardHeight);
            differentTile = (xOne != xTwo || yOne != yTwo);
        }
        mBoard[xOne][yOne].setRank(firstTileRank);
        mBoard[xTwo][yTwo].setRank(secondTileRank);

    }

    @Override
    public void init(BoardDimensions boardDimensions) {
        switch (boardDimensions) {
            case SQUARE4:
                mBoard = new Tile[4][4];
                mBoardWidth = 4;
                mBoardHeight = 4;
                break;
            case SQUARE5:
                mBoard = new Tile[5][5];
                mBoardWidth = 5;
                mBoardHeight = 5;
                break;
            case SQUARE6:
                mBoard = new Tile[6][6];
                mBoardWidth = 6;
                mBoardHeight = 6;
                break;
            default:
                throw new IllegalArgumentException("Forgot to implement new case for new board dimension, perish.");
        }

        setInitialBoardState();
    }

    @Override
    public Rank[] relayGameState() {
        Rank[] ranks = new Rank[mBoardWidth * mBoardHeight];
        for (int i = 0; i < mBoard.length; i++) {
            for (int j = 0; j < mBoard[i].length; j++) {
                ranks[i * mBoardWidth + j] = mBoard[i][j].getRank();
            }
        }

        return ranks;
    }
}
