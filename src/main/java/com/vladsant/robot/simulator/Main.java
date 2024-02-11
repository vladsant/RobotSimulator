package com.vladsant.robot.simulator;

import com.vladsant.robot.simulator.service.RobotSimulator;

public class Main {

  public static void main(String[] args) {
    RobotSimulator simulator = new RobotSimulator();
    simulator.loadCommandsFromFile("src/main/resources/command1.txt");
  }
}