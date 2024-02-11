package com.vladsant.robot.simulator.service;

import com.vladsant.robot.simulator.enums.Command;
import com.vladsant.robot.simulator.enums.Direction;
import com.vladsant.robot.simulator.exceptions.InvalidCommandException;
import com.vladsant.robot.simulator.exceptions.PositionOutOfBoundsException;
import com.vladsant.robot.simulator.model.Robot;
import com.vladsant.robot.simulator.model.Tabletop;
import java.io.File;
import java.util.Scanner;
import java.util.logging.Logger;

public class RobotSimulator {

  private static final Logger logger = Logger.getLogger(RobotSimulator.class.getName());

  private static final String PLACE = "PLACE";

  private final Robot robot;
  private final Tabletop tabletop;

  public RobotSimulator() {
    this.robot = new Robot();
    this.tabletop = new Tabletop();
  }

  /**
   * Processes a single command line input to control the robot's actions. The method supports
   * placing the robot, moving it, rotating it, and reporting its status. Commands that would result
   * in invalid states (e.g., moving the robot off the tabletop) are handled by throwing
   * exceptions.
   *
   * @param commandLine The command to execute, formatted as a string.
   */
  public void executeCommand(String commandLine) {
    try {
      if (commandLine.startsWith(PLACE)) {
        String[] parts = commandLine.split(" ")[1].split(",");
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        Direction direction = Direction.valueOf(parts[2]);
        if (!tabletop.isValidPosition(x, y)) {
          throw new PositionOutOfBoundsException(
              "Place command would position robot out of bounds.");
        }
        robot.setPosition(x, y, direction);
      } else {
        if (!robot.isPlaced()) {
          return;
        }

        switch (Command.valueOf(commandLine)) {
          case MOVE:
            int currentX = robot.getX();
            int currentY = robot.getY();
            robot.move();
            if (!tabletop.isValidPosition(robot.getX(), robot.getY())) {
              robot.setPosition(currentX, currentY, robot.getDirection());
              throw new PositionOutOfBoundsException(
                  "Move command would place robot out of bounds.");
            }
            break;
          case LEFT:
            robot.rotateLeft();
            break;
          case RIGHT:
            robot.rotateRight();
            break;
          case REPORT:
            System.out.println(report());
            break;
          default:
            throw new InvalidCommandException("Command not recognized: " + commandLine);
        }
      }
    } catch (InvalidCommandException | PositionOutOfBoundsException e) {
      logger.severe(e.getMessage());
    } catch (IllegalArgumentException e) {
      logger.severe("Invalid command: " + commandLine);
    }
  }

  /**
   * Generates a report of the robot's current position and direction, or null if not placed.
   *
   * @return A string representation of the robot's status or null if the robot hasn't been placed.
   */
  public String report() {
    return robot.isPlaced() ? robot.report() : null;
  }

  /**
   * Loads and executes a sequence of commands from 'command.txt' file.
   *
   * @param filePath The path to the file containing commands to be executed.
   */
  public void loadCommandsFromFile(String filePath) {
    try (Scanner scanner = new Scanner(new File(filePath))) {
      while (scanner.hasNextLine()) {
        executeCommand(scanner.nextLine());
      }
    } catch (Exception e) {
      logger.severe("Failed to load or read command file: " + e.getMessage());
    }
  }

}




