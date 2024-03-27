import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.UUID;

public class RequirementTest {

    private Requirement requirement;

    @BeforeEach
    public void setUp() {
        // Initialize a new Requirement object before each test
        UUID id = UUID.randomUUID();
        String name = "Test Requirement";
        Category category = Category.MR; // Assuming MR is a valid category for major requirements
        ArrayList<String> courseIDs = new ArrayList<>();
        courseIDs.add("COURSE1");
        courseIDs.add("COURSE2");
        int creditHoursRequired = 10;
        requirement = new Requirement(id, name, category, courseIDs, creditHoursRequired);
    }

    @Test
    public void testGetID() {
        // Test the getID method
        UUID expectedId = requirement.getID();
        assertNotNull(expectedId);
    }

    @Test
    public void testSetName() {
        // Test the setName method
        String newName = "New Test Requirement";
        requirement.setName(newName);
        assertEquals(newName, requirement.getName());
    }

    @Test
    public void testSetCategory() {
        // Test the setCategory method
        Category newCategory = Category.CC; // Assuming CC is another valid category
        requirement.setCategory(newCategory);
        assertEquals(newCategory, requirement.getCategory());
    }

    // Add more test methods for other getters and setters

    @Test
    public void testAddCourse() {
        // Test the addCourse method
        String newCourseID = "NEWCOURSE";
        boolean success = requirement.addCourse(newCourseID);
        assertTrue(success);
        assertTrue(requirement.getCourseIDs().contains(newCourseID));
    }

    @Test
    public void testAddCourses() {
        // Test the addCourses method
        ArrayList<String> newCourseIDs = new ArrayList<>();
        newCourseIDs.add("COURSE3");
        newCourseIDs.add("COURSE4");
        requirement.setCourseIDs(newCourseIDs);
        assertTrue(requirement.getCourseIDs().contains(newCourseIDs.get(0)));
        assertTrue(requirement.getCourseIDs().contains(newCourseIDs.get(1)));
    }

    @Test
    public void testHasCourse() {
        // Test the hasCourse method
        String courseID = "COURSE1";
        assertTrue(requirement.hasCourse(CourseList.getInstance().getCourseFromID(courseID)));
    }

    // Add more test methods to cover other functionality provided by the Requirement class
}