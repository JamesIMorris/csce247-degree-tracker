import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public class StudentTest {
    private Student student;
    private Requirement requirement;
    private Credit credit;
    private Course course;

    @BeforeEach
    public void setUp() {
       ArrayList<Season> semesters = new ArrayList<>(Arrays.asList(Season.FALL, Season.SPRING));

      ArrayList<Course> prerequisites = new ArrayList<>();

      ArrayList<Course> corequisites = new ArrayList<>();

      student = new Student("john_doe", "password", "John", "Doe", "john.doe@example.com", "123456789");

      course = new Course("CSCI101", "Introduction to Computer Science", "Introduction to computer science concepts", 4, semesters, prerequisites, corequisites, CourseType.DEFAUILT);

      requirement = new Requirement(UUID.randomUUID(), "Test Requirement", Category.CC, new ArrayList<>(), 120);

      credit = new Credit(course);
      //setUpRequirements();
      //setUpCredits();
    }

    @AfterEach
    public void tearDown() {
        student = null;
    }

    @Test
    public void testConstructor() {
        assertNotNull(student);
    }

    @Test
    public void testUsername() {
        assertEquals("john_doe", student.getUsername());
    }

    @Test
    public void testPassword() {
        assertEquals("password", student.getPassword());
    }

    @Test
    public void testFirstName() {
        assertEquals("John", student.getFirstName());
    }

    @Test
    public void testLastName() {
        assertEquals("Doe", student.getLastName());
    }

    @Test
    void testLastNameNotEquals() {
      assertNotEquals("notReal", student.getLastName());
    }

    @Test
    public void testEmail() {
        assertEquals("john.doe@example.com", student.getEmail());
    }

    @Test
    public void testUscID() {
        assertEquals("123456789", student.getUscID());
    }

    @Test
    public void testApplicationArea() {
        assertEquals("Undecided", student.getApplicationArea());
    }

    @Test
    public void testCreditsEmpty() {
        assertTrue(student.getCredits().isEmpty());
    }

    @Test
    public void testRequirementsEmpty() {
        assertTrue(student.getRequirements().isEmpty());
    }

    @Test
    public void testNotesEmpty() {
        assertTrue(student.getNotes().isEmpty());
    }

    @Test
    public void testChangeInProgress() {
        assertTrue(student.changeIsInProgress());
    }

    @Test
    public void testSetUscID() {
        student.setUscID("987654321");
        assertEquals("987654321", student.getUscID());
    }

    @Test
    public void testSetApplicationArea() {
        student.setApplicationArea("Computer Science");
        assertEquals("Computer Science", student.getApplicationArea());
    }

    @Test
    public void testChangeIsInProgress() {
        assertTrue(student.changeIsInProgress());
    }

    @Test
    public void testAddCredit() {
        assertTrue(student.addCredit(credit));
    }

    @Test
    public void testRemoveCredit() {
        student.addCredit(credit);
        assertTrue(student.removeCredit(credit));
    }

    @Test
    public void testAddCreditByCourseAndSemester() {
        assertTrue(student.addCredit(credit));
    }

    @Test
    public void testGetCreditByCourseAndSemester() {
      student.addCredit(credit);
      assertFalse(student.getCredits().isEmpty());

    }

    @Test
    public void testGetCreditFromID() {
        UUID id = UUID.randomUUID();
        credit.setID(id);
        student.addCredit(credit);
        assertEquals(credit, student.getCreditFromID(id));
    }

    @Test
    public void testSetRequirements() {
        HashMap<Requirement, ArrayList<Credit>> requirements = new HashMap<>();
        student.setRequirements(requirements);
        assertEquals(requirements, student.getRequirements());
    }

    @Test
    public void testTotalCreditHoursCompleteEmpty() {
        assertEquals(0, student.totalCreditHoursComplete());
    }

    @Test
    public void testAssignCreditValid() {
        assertTrue(student.assignCredit(credit, requirement));
    }

    @Test
    public void testCreditAddedToRequirements() {
        student.assignCredit(credit, requirement);
        assertTrue(student.getRequirements().get(requirement).contains(credit));
    }

    @Test
    public void testAssignCreditNullCredit() {
        assertFalse(student.assignCredit(null, requirement));
    }

    @Test
    public void testAssignCreditNullRequirement() {
        assertNull(student.getRequirements().get(null));
    }


  }