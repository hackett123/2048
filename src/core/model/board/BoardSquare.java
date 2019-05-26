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
    public boolean performMove(Direction direction) {
        //flip to the right
        int numRotations = RotationFunctionsSquare.rotate(direction, mBoard, mBoardSize);
        int numRotationsToRealign = 4 - numRotations;

        boolean moveOccurred = shoveRight();
        if (!moveOccurred) {
            System.out.println("Invalid move");
        }

        //realign board
        RotationFunctionsSquare.rotate(numRotationsToRealign, mBoard, mBoardSize);

        if (moveOccurred) {
            addTileToBoard();
        }
        return moveOccurred;
    }

    private boolean shoveRight() {
        //row from top
        boolean moveOccurred = false;
        for (int i = 0; i < mBoardSize; i++) {
            moveOccurred = shoveRowRight(i) || moveOccurred;
        }
        return moveOccurred;
    }

    private boolean shoveRowRight(int row) {
        boolean moveOccurred = false;

        for (int col = mBoardSize - 2; col >= 0; col--) {
            //get tiles
            ITile toShove = mBoard[row][col];
            ITile toTheRight = mBoard[row][col + 1];
            if (toShove.getRank() != Rank.EMPTY) {
                //move until adjacent to wall or another block
                int count = 0;
                while (toTheRight.getRank() == Rank.EMPTY) {
                    moveOccurred = true;
                    toTheRight.setRank(toShove.getRank());
                    toShove.setRank(Rank.EMPTY);
                    toShove = toTheRight;
                    count++;
                    if (col + 1 + count < mBoardSize) {
                        toTheRight = mBoard[row][col + 1 + count];
                    } else {
                        break;
                    }
                }

                //consider combining
                if (col + 1 + count < mBoardSize && toShove.getRank() == toTheRight.getRank()) {
                    moveOccurred = true;
                    toShove.setRank(Rank.EMPTY);
                    toTheRight.setRank(Rank.values()[toTheRight.getRank().ordinal() + 1]);
                }
            }
        }

        return moveOccurred;
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

        addTileToBoard();
        addTileToBoard();
    }

    private Rank generateRandomRank() {
        return Math.random() < RANK_I_PROBABILITY ? Rank.I : Rank.II;
    }

    private void addTileToBoard() {
        boolean noVacancy = true;
        int x = 0;
        int y = 0;
        while (noVacancy) {
            x = genPos();
            y = genPos();
            noVacancy = mBoard[x][y].getRank() != Rank.EMPTY;
        }
        mBoard[x][y].setRank(generateRandomRank());
    }

    private int genPos() {
        return (int) (Math.random() * mBoardSize);
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
