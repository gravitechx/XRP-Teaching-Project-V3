package practice.Week5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.wpilib.command3.Command;
import org.wpilib.command3.Scheduler;
import org.wpilib.telemetry.MockTelemetryBackend;
import org.wpilib.telemetry.TelemetryRegistry;


/* 
public class Week5PracticeTest {
    // TODO Work on problems in PracticeIntake.java, RobotMathUtils.java, and Shooter.java
    private MockTelemetryBackend backend;

    @Test
    void testVoltageClamping() {
        Shooter shooter = new Shooter();

        // Over-voltage test
        shooter.setVoltage(15.0);
        assertEquals(12.0, shooter.getVoltage(), 0.001);

        // Under-voltage test
        shooter.setVoltage(-18.0);
        assertEquals(-12.0, shooter.getVoltage(), 0.001);

        // Normal voltage test
        shooter.setVoltage(8.5);
        assertEquals(8.5, shooter.getVoltage(), 0.001);

    }

    @Test
    void testRobotMathUtils() {
        // Deadband tests
        assertEquals(0.0, RobotMathUtils.applyDeadband(0.03, 0.05), 0.001);
        assertEquals(0.0, RobotMathUtils.applyDeadband(-0.02, 0.05), 0.001);
        assertEquals(0.8, RobotMathUtils.applyDeadband(0.8, 0.05), 0.001);
        assertEquals(-0.8, RobotMathUtils.applyDeadband(-0.8, 0.05), 0.001);

        // RPM to Rad/s test (6000 RPM ≈ 628.318 rad/s)
        assertEquals(628.318, RobotMathUtils.rpmToRadsPerSec(6000.0), 0.01);
    }

    

    @Test
    void testCommandLifecycle() {
        Shooter shooter = new Shooter();
        Scheduler.getDefault().run();
        Scheduler.getDefault().schedule(shooter.getShootCommand());
        Scheduler.getDefault().run();
        assertTrue(backend.getLastAction("Shooter command").value().toString().contains("running"));
    }

    @Test
    void testRunIntakeCommand() {
        PracticeIntake intake = new PracticeIntake();
        Command command = intake.runIntakeCommand();

        // Verify subsystem requirement declaration
        assertTrue(command.requirements().contains(intake));

        // Test execution cycle
        assertFalse(intake.isRunning());
        Scheduler.getDefault().schedule(command);
        Scheduler.getDefault().run();
        assertTrue(intake.isRunning());
    }

    @Test 
    void testShooterDefaultCommand(){
        Shooter shooter = new Shooter();
        Scheduler.getDefault().run();
        assertTrue(Scheduler.getDefault().isScheduledOrRunning(shooter.getDefaultCommand()));
    }

    @BeforeEach
    public void setupTelemetry(){
        TelemetryRegistry.reset();
        backend = new MockTelemetryBackend();
        TelemetryRegistry.registerBackend("/", backend);
    }
}
*/