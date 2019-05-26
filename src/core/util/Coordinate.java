package core.util;

public class Coordinate {

  private int mX;

  private int mY;

  public Coordinate(int x, int y) {
    mX = x;
    mY = y;
  }

  public int getX() { return mX; }

  public int getY() { return mY; }


  public Coordinate offset(int offX, int offY) {
    return new Coordinate(offX + mX, offY + mY);
  }

}
