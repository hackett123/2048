package core.util;

/**
 * Contains information for a high score, each of which has the
 * name of the player who scored the score and the score value itself.
 */
public interface IHighScore {

  /**
   * To get the name associated with this high score.
   * @return The name.
   */
  String getName();

  /**
   * To get the score associated with this high score.
   * @return The score.
   */
  long getScore();

}
