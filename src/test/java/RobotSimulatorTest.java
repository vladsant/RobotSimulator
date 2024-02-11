import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.vladsant.robot.simulator.service.RobotSimulator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RobotSimulatorTest {

  private RobotSimulator simulator;

  @BeforeEach
  void setUp() {
    simulator = new RobotSimulator();
  }

  @Test
  void testPlaceAndReport() {
    simulator.executeCommand("PLACE 0,0,NORTH");
    assertEquals("0,0,NORTH", simulator.report());
  }

  @Test
  void testMove() {
    simulator.executeCommand("PLACE 0,0,NORTH");
    simulator.executeCommand("MOVE");
    assertEquals("0,1,NORTH", simulator.report());
  }

  @Test
  void testRotation() {
    simulator.executeCommand("PLACE 0,0,NORTH");
    simulator.executeCommand("LEFT");
    assertEquals("0,0,WEST", simulator.report());
    simulator.executeCommand("RIGHT");
    simulator.executeCommand("RIGHT");
    assertEquals("0,0,EAST", simulator.report());
  }

  @Test
  void testInvalidMove() {
    simulator.executeCommand("PLACE 0,0,SOUTH");
    simulator.executeCommand("MOVE");
    assertEquals("0,0,SOUTH", simulator.report());
  }

  @Test
  void testIgnoringCommandsBeforeValidPlace() {
    simulator.executeCommand("MOVE");
    simulator.executeCommand("LEFT");
    assertNull(simulator.report(), "Robot should not report until it's placed");
    simulator.executeCommand("PLACE 0,0,NORTH");
    assertEquals("0,0,NORTH", simulator.report());
  }

  @Test
  void testComplexCommandSequence() {
    simulator.executeCommand("PLACE 1,2,EAST");
    simulator.executeCommand("MOVE");
    simulator.executeCommand("MOVE");
    simulator.executeCommand("LEFT");
    simulator.executeCommand("MOVE");
    assertEquals("3,3,NORTH", simulator.report());
  }

  @ParameterizedTest
  @CsvSource({
      "0,4,NORTH,0,4,NORTH",
      "0,0,SOUTH,0,0,SOUTH",
      "4,0,EAST,4,0,EAST",
      "0,0,WEST,0,0,WEST"
  })
  void robotDoesNotFallOffTableEdge(int startX, int startY, String startDirection, int expectedX,
      int expectedY, String expectedDirection) {
    String placeCommand = String.format("PLACE %d,%d,%s", startX, startY, startDirection);
    simulator.executeCommand(placeCommand);
    simulator.executeCommand("MOVE");
    String expectedReport = String.format("%d,%d,%s", expectedX, expectedY, expectedDirection);
    assertEquals(expectedReport, simulator.report());
  }
}
