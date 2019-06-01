package core.view.gui;

import core.util.Rank;

public interface IGuiRenderer {

    /**
     * Called by GUIHandler when it is asked to prompt user move.
     *
     * @return String representation of the move chosen by the user which
     * has been vetted to be valid.
     */
    String getMove();

    /**
     * Visually renders the GUI representation of the board state.
     * @param ranks 1D array is parsed into the 2D representation
     * @param score int of player's score
     */
    void renderBoardState(Rank[] ranks, int score);



}
