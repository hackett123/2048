package core.view;

import core.controller.IController;
import core.util.BoardDimensions;
import core.util.Direction;
import core.util.IHighScore;
import core.util.Rank;
import java.util.Scanner;
import java.util.regex.Pattern;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class CLIHandler implements IView {

  private IController mController;
  //Board


  private Scanner mScanner;

  private static String sInputRegex = "[456]";

  public CLIHandler() {
    // Init scanner
    mScanner = new Scanner(System.in);
  }


  private void _startGame(IController controller) {
    if (controller == null) {
      throw new IllegalArgumentException("Null controller provided");
    }
    this.mController = controller;
    // Nothing much to initialize - just print a greeting
    System.out.println("Welcome to 2048 in 20:48");
  }

  @Override
  public void startGame(IController controller) {
    _startGame(controller);
  }

  @Override
  public void resetGame() {
    _startGame(mController);
  }


  private void _sendMessage(String message) {
    // Just print it to the screen with a newline
    System.out.println(message);
  }

  @Override
  public void sendMessage(String message) {
    _sendMessage(message);
  }

  @Override
  public void acceptAndRenderBoardState(Rank[] ranks) {

    //check for nulls
    for (Rank rank : ranks) {
      if (rank == null) {
        throw new IllegalArgumentException("Provided null rank in array");
      }
    }

    // TODO parse to 2D array for printing
    throw new NotImplementedException();
  }


  @Override
  public void signalEndOfGame() {
    throw new NotImplementedException();
  }

  @Override
  public void acceptAndRenderHighScores(IHighScore[] highScores) {
    throw new NotImplementedException();
  }

  @Override
  public BoardDimensions promptBoardDimensions() {
    // First, prompt the user to input the dimensions.
    _sendMessage("Select board type: \n"
        + "'4' for a 4x4 square, \n"
        + "'5' for a 5x5 square, \n"
        + "'6' for a 6x6 square");

    boolean validInput = false;
    String input = "";

    while (!validInput) {
      // Poll the scanner for input
      input = mScanner.next();
      // See if the input is valid
      validInput = Pattern.matches(sInputRegex, input);
      // Ask for further input if invalid
      if (!validInput) {
        _sendMessage("The given input was not valid. Try again.");
      }
    }

    // Switch on the input
    int inputAsInt = Integer.parseInt(input);
    int dimensionsOrdinal = inputAsInt - 4;
    BoardDimensions dims = BoardDimensions.values()[dimensionsOrdinal];

    // Tell the user their input
    String msg = "You have selected: " + dims.toString();
    _sendMessage(msg);

    return dims;
  }

  @Override
  public Direction promptTurn() {
    // Ask the user for input
    _sendMessage("");
    return null;
  }


  /**
   * Formats the board for printing.
   */
  private static class BoardFormatter {


    private static String formatBoardForPrinting(Rank[][] ranks) {
      throw new NotImplementedException();
    }

  }
}
