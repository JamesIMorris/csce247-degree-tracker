import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UITest {

    private UI ui;
    private DegreeTracker degreeTracker;
    private Scanner scanner;

    @BeforeEach
    public void setUp() {
        degreeTracker = mock(DegreeTracker.class);
        scanner = mock(Scanner.class);
        ui = new UI(degreeTracker, scanner);
    }

    @Test
    public void testScenario1() {
        // Mock user input
        when(scanner.nextLine()).thenReturn("testUser", "password", "2", "CS101", "Fall", "yes", "5");

        // Mock DegreeTracker behavior
        when(degreeTracker.login("testUser", "password")).thenReturn(true);
        when(degreeTracker.studentHomePage("testUser")).thenReturn("Home page content");
        when(degreeTracker.studentAssignCourse("testUser", "CS101", "Fall", "GFL")).thenReturn(true);

        // Call the method to be tested
        ui.scenario1();

        // Verify interactions
        verify(degreeTracker).login("testUser", "password");
        verify(degreeTracker).studentHomePage("testUser");
        verify(degreeTracker).studentAssignCourse("testUser", "CS101", "Fall", "GFL");
    }

    // Add more test cases for other scenarios and methods as needed
}