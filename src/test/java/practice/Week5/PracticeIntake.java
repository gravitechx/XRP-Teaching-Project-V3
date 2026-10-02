package practice.Week5;

import org.wpilib.command3.Mechanism;

/**
 * Practice Problem 4.1: Dummy Subsystem for Integration Challenge
 * DON'T EDIT
 */
public class PracticeIntake implements Mechanism {
    private boolean running = false;

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isRunning() {
        return running;
    }
}