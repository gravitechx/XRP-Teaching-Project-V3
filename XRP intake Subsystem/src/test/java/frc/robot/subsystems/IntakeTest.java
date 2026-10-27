package frc.robot.subsystems;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import frc.robot.Constants.IntakeConstants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.wpilib.command2.Command;
import org.wpilib.command2.CommandScheduler;
import org.wpilib.hardware.hal.HAL;
import org.wpilib.simulation.DriverStationSim;
import org.wpilib.simulation.SimHooks;

class IntakeTest {
  private static final double kDelta = 1e-6;
  private static Intake s_intake;

  // Starts the simulated robot hardware and creates the one intake shared by every test.
  @BeforeAll
  static void setupAll() {
    assertTrue(HAL.initialize());
    s_intake = new Intake();
  }

  // Enables the simulated robot and makes sure the rollers start stopped.
  @BeforeEach
  void setup() {
    DriverStationSim.setEnabled(true);
    DriverStationSim.notifyNewData();
    SimHooks.pauseTiming();
    CommandScheduler.getInstance().cancelAll();
    s_intake.stop();
  }

  // Cancels any running commands and lets the clock run normally again.
  @AfterEach
  void teardown() {
    CommandScheduler.getInstance().cancelAll();
    SimHooks.resumeTiming();
  }

  // Checks that setSpeed sends the given speed to the motor.
  @Test
  void setSpeedSetsMotorOutput() {
    s_intake.setSpeed(0.5);
    assertEquals(0.5, s_intake.getSpeed(), kDelta);
  }

  // Checks that stop sets the motor output to zero.
  @Test
  void stopSetsMotorToZero() {
    s_intake.setSpeed(0.7);
    s_intake.stop();
    assertEquals(0.0, s_intake.getSpeed(), kDelta);
  }

  // Checks that the intake command runs the rollers inward and stops them when canceled.
  @Test
  void intakeCommandRunsThenStops() {
    Command command = s_intake.intakeCommand();
    CommandScheduler.getInstance().schedule(command);
    CommandScheduler.getInstance().run();
    assertEquals(IntakeConstants.kIntakeSpeed, s_intake.getSpeed(), kDelta);

    command.cancel();
    assertEquals(0.0, s_intake.getSpeed(), kDelta);
  }

  // Checks that the outtake command runs the rollers outward and stops them when canceled.
  @Test
  void outtakeCommandRunsThenStops() {
    Command command = s_intake.outtakeCommand();
    CommandScheduler.getInstance().schedule(command);
    CommandScheduler.getInstance().run();
    assertEquals(IntakeConstants.kOuttakeSpeed, s_intake.getSpeed(), kDelta);

    command.cancel();
    assertEquals(0.0, s_intake.getSpeed(), kDelta);
  }

  // Checks that the eject command pushes out and then stops by itself after its timeout.
  @Test
  void ejectCommandStopsAfterTimeout() {
    Command command = s_intake.ejectCommand();
    CommandScheduler.getInstance().schedule(command);
    CommandScheduler.getInstance().run();
    assertEquals(IntakeConstants.kOuttakeSpeed, s_intake.getSpeed(), kDelta);

    SimHooks.stepTiming(IntakeConstants.kEjectTimeSeconds / 2);
    CommandScheduler.getInstance().run();
    assertTrue(command.isScheduled());

    SimHooks.stepTiming(IntakeConstants.kEjectTimeSeconds);
    CommandScheduler.getInstance().run();
    assertFalse(command.isScheduled());
    assertEquals(0.0, s_intake.getSpeed(), kDelta);
  }

  // Checks that the stop command holds the rollers at zero.
  @Test
  void stopCommandKeepsRollersStopped() {
    s_intake.setSpeed(0.9);
    CommandScheduler.getInstance().schedule(s_intake.stopCommand());
    CommandScheduler.getInstance().run();
    assertEquals(0.0, s_intake.getSpeed(), kDelta);
  }
}
