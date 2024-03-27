import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.UUID;

public class RequirementTest {

    private Requirement requirement;

    @BeforeEach
    public void setUp() {
        UUID id = UUID.randomUUID();
        String name = "Test Requirement";
        Category category = Category.MR;
        ArrayList<String> courseIDs = new ArrayList<>();
        courseIDs.add("COURSE1");
        courseIDs.add("COURSE2");
        int creditHoursRequired = 10;
        requirement = new Requirement(id, name, category, courseIDs, creditHoursRequired);
    }

    @Test
    public void testGetID() {
        UUID expectedId = requirement.getID();
        assertNotNull(expectedId);
    }

    @Test
    public void testSetName() {
        String newName = "New Test Requirement";
        requirement.setName(newName);
        assertEquals(newName, requirement.getName());
    }

    @Test
    public void testSetCategory() {
        Category newCategory = Category.CC;
        requirement.setCategory(newCategory);
        assertEquals(newCategory, requirement.getCategory());
    }


    @Test
    public void testAddCourse() {
        String newCourseID = "NEWCOURSE";
        boolean success = requirement.addCourse(newCourseID);
        assertTrue(success);
        assertTrue(requirement.getCourseIDs().contains(newCourseID));
    }

    @Test
    public void testAddCourses() {
        ArrayList<String> newCourseIDs = new ArrayList<>();
        newCourseIDs.add("COURSE3");
        newCourseIDs.add("COURSE4");
        requirement.setCourseIDs(newCourseIDs);
        assertTrue(requirement.getCourseIDs().contains(newCourseIDs.get(0)));
        assertTrue(requirement.getCourseIDs().contains(newCourseIDs.get(1)));
    }

    @Test
    public void testHasCourse() {
        String courseID = "COURSE1";
        assertTrue(requirement.hasCourse(CourseList.getInstance().getCourseFromID(courseID)));
    }

}