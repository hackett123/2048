package core.model.board;

import core.util.Rank;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Tile implements ITile {

    private Rank mRank;

    public Tile(Rank rank) {
        this.mRank = rank;
    }

    @Override
    public Rank getRank() {
        return this.mRank;
    }

    @Override
    public void setRank(Rank rank) {
        this.mRank = rank;
    }
}
