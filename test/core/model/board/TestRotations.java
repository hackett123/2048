package core.model.board;

import core.util.BoardDimensions;
import core.util.Direction;
import core.util.Rank;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestRotations {

  BoardSquare board;

  @Before
  public void setup() {
    board = new BoardSquare();
    board.init(BoardDimensions.SQUARE4);
    for (int i = 0; i < board.getBoardWidth(); i++) {
      for (int j = 0; j < board.getBoardHeight(); j++) {
        board.mBoard[i][j].setRank(Rank.EMPTY);
      }
    }
    board.mBoard[0][0].setRank(Rank.I);
    board.mBoard[3][0].setRank(Rank.II);
    board.mBoard[3][3].setRank(Rank.III);
    board.mBoard[2][1].setRank(Rank.IV);
    board.mBoard[1][3].setRank(Rank.V);
    board.mBoard[0][3].setRank(Rank.VI);
  }

  @Test
  public void testRotation90CW() {

    board.rotate(Direction.UP);

    ITile[][] rotated = new ITile[4][4];
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        rotated[i][j] = new Tile(Rank.EMPTY);
      }
    }
    rotated[0][0].setRank(Rank.II);
    rotated[3][0].setRank(Rank.III);
    rotated[3][3].setRank(Rank.VI);
    rotated[0][3].setRank(Rank.I);
    rotated[1][1].setRank(Rank.IV);
    rotated[3][2].setRank(Rank.V);

    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        assertEquals(rotated[i][j].getRank().ordinal(), board.mBoard[i][j].getRank().ordinal());
      }
    }
  }
}
