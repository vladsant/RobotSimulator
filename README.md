# Robot Simulator

## Introduction

The Robot Simulator is a Java application designed to simulate a toy robot moving on a square
tabletop of dimensions 5 units x 5 units. It accepts commands to place the robot on the table, move
it, rotate it, and report its position. The main goal is to prevent the robot from falling off the
table while allowing it to navigate freely within the table's bounds.

## Features

- **Place**: Put the robot on the table in a specific position and facing direction.
- **Move**: Move the robot one unit forward in the direction it is currently facing.
- **Rotate**: Rotate the robot 90 degrees to the left or right without changing its position.
- **Report**: Output the current position and direction of the robot.

## Installation

1. **Clone the repository**:

   ```bash
   git clone https://github.com/vladsant/robot-simulator.git

2. **Navigate to the project directory**:

   ```bash
   cd robot-simulator

3. **Build the project**:

   ```bash
   mvn clean install

## Usage

1. **Run**

   ```bash
   java -jar target/robot-simulator-1.0-SNAPSHOT.jar command1.txt

## Running Tests

1. **Run**

   ```bash
   mvn test

## Example of 'commands.text'

#### 'commands.txt' should be a text file containing a list of commands, one per line, that you want the robot to execute.

   ```bash
    PLACE 0,0,NORTH
    MOVE
    REPORT
    LEFT
    MOVE
    REPORT
