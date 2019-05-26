package core.model.board;

import core.util.BoardDimensions;
import core.util.Direction;
import core.util.Rank;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class BoardRectangular implements IBoard {

    ITile[][] mBoard;

    static final Map<Direction, Consumer<ITile[][]>> mRotateFunctionMap =
      RotationFunctionsRectangular.getDirectionMap();

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
