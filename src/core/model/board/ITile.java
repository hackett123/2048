package core.model.board;

import core.util.Rank;

public interface ITile {

    /**
     *
     * @return the rank of the tile. Invariant that this cannot be null.
     */
    public Rank getRank();
}
