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
    void relayDirection(Direction direction);

    /**
     * Relays array of ranks from the model to the view.
     * @param ranks
     */
    void relayBoard(Rank[] ranks);

    /**
     * To inform the user that the game has reached its victory
     * requirements.
     */
    void signalGameWon();

    /**
     * To inform the user that there are no remaining possible moves
     * and to end the game.
     */
    void signalGameOver();

    void sendMessage(String message);

}
