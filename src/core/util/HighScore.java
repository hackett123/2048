package core.util;

public class HighScore implements IHighScore {

  /**
   * The score.
   */
  private long mScore;

  /**
   * The name.
   */
  private String mName;

  /**
   * Accept a name and a score.
   * @param name The name of the user.
   * @param score The score that the user earned. 
   */
  public HighScore(String name, long score) {
    // Store both
    mName = name;
    mScore = score;
  }

  @Override
  public String getName() {
    return mName;
  }

  @Override
  public long getScore() {
    return mScore;
  }
}
