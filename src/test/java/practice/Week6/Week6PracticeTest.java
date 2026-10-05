package practice.Week6;

import org.junit.jupiter.api.BeforeEach;
import org.wpilib.telemetry.MockTelemetryBackend;
import org.wpilib.telemetry.TelemetryRegistry;

public class Week6PracticeTest {
    private MockTelemetryBackend backend;


    @BeforeEach
    public void setupTelemetry(){
        TelemetryRegistry.reset();
        backend = new MockTelemetryBackend();
        TelemetryRegistry.registerBackend("/", backend);
    }
}
