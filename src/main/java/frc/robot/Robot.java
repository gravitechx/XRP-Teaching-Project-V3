// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import org.wpilib.command3.Scheduler;
import org.wpilib.command3.button.CommandXboxController;
import org.wpilib.driverstation.XboxController;
import org.wpilib.framework.TimedRobot;

import frc.robot.mechanisms.Drivetrain;

/**
 * The methods in this class are called automatically corresponding to each
 * mode, as described in
 * the TimedRobot documentation. If you change the name of this class or the
 * package after creating
 * this project, you must also update the Main.java file in the project.
 */
public class Robot extends TimedRobot {
    /**
     * This function is run when the robot is first started up and should be used
     * for any
     * initialization code.
     */

    public CommandXboxController controller;
    public Drivetrain drivetrain;

    public Robot() {
        // Instantiate our RobotContainer. This will perform all our button bindings,
        // and put our
        // autonomous chooser on the dashboard.

        controller = new CommandXboxController(0);

        
        
        drivetrain = new Drivetrain();
        drivetrain.setDefaultCommand(drivetrain.defaultCommand(() -> controller.getLeftY(), () -> controller.getRightX()));
        configureKeybinds();
    }

    public void configureKeybinds() {
        controller.x().whileTrue(drivetrain.spinToSetAngle(90));
    }


    /**
     * This function is called every 20 ms, no matter the mode. Use this for items
     * like diagnostics
     * that you want ran during disabled, autonomous, teleoperated and test.
     *
     * <p>
     * This runs after the mode specific periodic functions, but before LiveWindow
     * and
     * SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {
        //

        Scheduler.getDefault().run();
    }

    /** This function is called once each time the robot enters Disabled mode. */
    @Override
    public void disabledInit() {
    }

    @Override
    public void disabledPeriodic() {
    }

    @Override
    public void autonomousInit() {
    }

    /** This function is called periodically during autonomous. */
    @Override
    public void autonomousPeriodic() {
    }

    @Override
    public void teleopInit() {

    }

    /** This function is called periodically during operator control. */
    @Override
    public void teleopPeriodic() {
    }

    @Override
    public void utilityInit() {
        // Cancels all running commands at the start of test mode.
    }

    /** This function is called periodically during test mode. */
    @Override
    public void utilityPeriodic() {
    }
}
