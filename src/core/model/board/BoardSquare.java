package core.model.board;

import core.util.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class BoardSquare implements IBoard {

    @VisibleForTesting
    ITile[][] mBoard;

    //constants : we consider a probability distribution for all ranks as a later feature.
    private static final double RANK_I_PROBABILITY = 0.9;
    private static final double RANK_II_PROBABILITY = 0.1;

    private int mBoardSize;

    @Override
    public int getBoardWidth() {
        if (this.mBoardSize == 0) {
            throw new IllegalArgumentException("Called before board has been initialized");
        }
        return this.mBoardSize;
    }

    @Override
    public int getBoardHeight() {
        if (this.mBoardSize == 0) {
            throw new IllegalArgumentException("Called before board has been initialized");
        }
        return this.mBoardSize;
    }

    @Override
    public void performMove(Direction direction) {
        int numRotations = RotationFunctionsSquare.rotate(direction, mBoard, mBoardSize);
        int numRotationsToRealign = 4 - numRotations;
        RotationFunctionsSquare.rotate(numRotationsToRealign, mBoard, mBoardSize);
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
        int xOne = (int) (Math.random() * mBoardSize);
        int yOne = (int) (Math.random() * mBoardSize);
        int xTwo = 0;
        int yTwo = 0;
        boolean differentTile = false;
        while (!differentTile) {
            xTwo = (int) (Math.random() * mBoardSize);
            yTwo = (int) (Math.random() * mBoardSize);
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
                mBoardSize = 4;
                mBoardSize = 4;
                break;
            case SQUARE5:
                mBoard = new Tile[5][5];
                mBoardSize = 5;
                mBoardSize = 5;
                break;
            case SQUARE6:
                mBoard = new Tile[6][6];
                mBoardSize = 6;
                mBoardSize = 6;
                break;
            default:
                throw new IllegalArgumentException("Forgot to implement new case for new board dimension, perish.");
        }

        setInitialBoardState();
    }

    @Override
    public Rank[] relayGameState() {
        Rank[] ranks = new Rank[mBoardSize * mBoardSize];
        for (int i = 0; i < mBoard.length; i++) {
            for (int j = 0; j < mBoard[i].length; j++) {
                ranks[i * mBoardSize + j] = mBoard[i][j].getRank();
            }
        }

        return ranks;
    }
}
