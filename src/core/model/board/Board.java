package core.model.board;

import core.util.Rank;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Board implements IBoard {

    Rank[] board;

    @Override
    public Rank[] getBoard() {
        throw new NotImplementedException();
    }

    @Override
    public void acceptAndUpdateBoardState(Rank[] ranks) {
        throw new NotImplementedException();
    }
}
