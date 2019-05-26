package core.model.board;

import core.util.Rank;

/**
 * Representation of a board.
 */
public interface IBoard {

    /**
     * @return array of Rank enums which represent board state.
     */
    public Rank[] getBoard();


    public void acceptAndUpdateBoardState(Rank[] ranks);



}
