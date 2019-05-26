package core.controller;

import core.util.Direction;
import core.util.Rank;

/**
 * Represents a controller .
 */
public interface IController {

    /**
     * Sends user's move choice to the model for board state changes.
     * @param direction
     */
    public void relayDirection(Direction direction);

    /**
     * Relays array of ranks from the model to the view.
     * @param ranks
     */
    public void relayBoard(Rank[] ranks);

}
