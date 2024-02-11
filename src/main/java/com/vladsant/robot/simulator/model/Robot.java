package com.vladsant.robot.simulator.model;

import com.vladsant.robot.simulator.enums.Direction;

public class Robot {

  private Integer x = null;
  private Integer y = null;
  private Direction direction = null;

  public void setPosition(int x, int y, Direction direction) {
    this.x = x;
    this.y = y;
    this.direction = direction;
  }

  // Moves the robot one unit forward in the direction it is currently facing. Movement is restricted
  // to the robot's current direction and is contingent on the robot being placed on the tabletop.
  public void move() {
    if (x == null || y == null || direction == null) {
      return;
    }
    switch (direction) {
      case NORTH:
        y++;
        break;
      case SOUTH:
        y--;
        break;
      case EAST:
        x++;
        break;
      case WEST:
        x--;
        break;
    }
  }

  public void rotateLeft() {
    if (direction == null) {
      return;
    }
    direction = Direction.values()[(direction.ordinal() + 3) % Direction.values().length];
  }

  public void rotateRight() {
    if (direction == null) {
      return;
    }
    direction = Direction.values()[(direction.ordinal() + 1) % Direction.values().length];
  }

  // Reports the current position (x, y) and direction of the robot as a String.
  // Returns null if the robot has not been placed.
  public String report() {
    if (x == null || y == null || direction == null) {
      return null;
    }
    return x + "," + y + "," + direction.name();
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public Direction getDirection() {
    return direction;
  }
  
  public boolean isPlaced() {
    return x != null && y != null && direction != null;
  }
}
