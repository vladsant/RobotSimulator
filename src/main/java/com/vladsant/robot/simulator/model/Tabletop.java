package com.vladsant.robot.simulator.model;

public class Tabletop {

  private static final int WIDTH = 5;
  private static final int HEIGHT = 5;


  /**
   * Checks if a given position (x, y) is within the bounds of the tabletop.
   *
   * @param x The x-coordinate of the position to validate.
   * @param y The y-coordinate of the position to validate.
   * @return true if the position is within the tabletop's bounds; false otherwise.
   */
  public boolean isValidPosition(int x, int y) {
    return x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT;
  }
}

