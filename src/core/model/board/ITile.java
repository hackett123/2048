package core.model.board;

import core.util.Rank;

public interface ITile {

    /**
     *
     * @return the rank of the tile. Invariant that this cannot be null.
     */
    public Rank getRank();

    /**
     * Sets the rank of the tile to avoid object generation overhead in later game.
     */
    public void setRank(Rank rank);
}
