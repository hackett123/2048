package core.model;

import core.controller.IController;
import core.model.board.BoardSquare;
import core.util.BoardDimensions;
import core.util.Direction;
import core.util.Rank;

public class ModelManager implements IModel {


  private IController mController;

  /**
   * Based on board dimension.
   * 4x4 -> XI
   * 5x5 -> XII
   * 6x6 -> XV
   */
  private Rank mWinningRank;

  //halting configurations.
  private boolean mHasWon;
  private boolean mHasLost;

  BoardSquare mBoard;

  private void configureWinningRank(BoardDimensions boardDimensions) {
    switch (boardDimensions) {
      case SQUARE4:
        mWinningRank = Rank.XI;
        break;
      case SQUARE5:
        mWinningRank = Rank.XIII;
        break;
      case SQUARE6:
        mWinningRank = Rank.XV;
        break;
      default:
        throw new IllegalArgumentException("Forgot to implement new case for new board dimension, perish.");
    }
  }

  @Override
  public void acceptBoardDimensions(BoardDimensions boardDimensions) {
    configureWinningRank(boardDimensions);
    mBoard.init(boardDimensions);

  }

  @Override
  public void init(IController controller) {
    this.mController = controller;
    mBoard = new BoardSquare();
  }

  @Override
  public void performMove(Direction direction) {
      // Defer to the board
      if (!mBoard.performMove(direction)) {
        mController.sendMessage("Invalid move, nothing will happen.");
      }
  }


  @Override
  public Rank[] relayGameState() {
    return mBoard.relayGameState();
  }

  @Override
  public int getBoardWidth() {
    return this.mBoard.getBoardWidth();
  }

  @Override
  public int getBoardHeight() {
    return this.mBoard.getBoardHeight();
  }
}
