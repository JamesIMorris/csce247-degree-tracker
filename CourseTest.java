import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;

public class CourseTest {

    private Course course;

    @BeforeEach
    public void setUp() {
        ArrayList<Season> semesters = new ArrayList<>(Arrays.asList(Season.FALL, Season.SPRING));
        ArrayList<Course> prerequisites = new ArrayList<>();
        ArrayList<Course> corequisites = new ArrayList<>();

        course = new Course("CSCE101", "Introduction to Computer Science", "Introduction to computer science concepts", 4, semesters, prerequisites, corequisites, CourseType.DEFAUILT);
    }

    @AfterEach
    void tearDown(){
      course = null;
    }

    @Test
    public void testGetCourseID() {
        assertEquals("CSCE101", course.getCourseID());
    }


    @Test
    public void testGetNullCourseID() {
        assertNotNull(null, course.getCourseID());
    }

    @Test
    public void testGetCourseName() {
        assertEquals("Introduction to Computer Science", course.getCourseName());
    }

    @Test
    public void testGetCourseDescription() {
        assertEquals("Introduction to computer science concepts", course.getCourseDescription());
    }

    @Test
    public void testGetCreditHours() {
        assertEquals(4, course.getCreditHours());
    }

    @Test
    public void testGetSemesterAvailability() {
        ArrayList<Season> expectedSemesters = new ArrayList<>(Arrays.asList(Season.FALL, Season.SPRING));
        assertEquals(expectedSemesters, course.getSemesterAvailability());
    }

    @Test
    public void testAddPreRequisite() {
        Course preRequisite = new Course("CSCE102", "Data Structures and Algorithms", "Introduction to data structures and algorithms", 4, new ArrayList<>(Arrays.asList(Season.FALL)), CourseType.DEFAUILT);
        assertTrue(course.addPreRequirisite(preRequisite));
        assertTrue(course.getPreRequisites().contains(preRequisite));
    }

      @Test
      public void testRemovePreRequisite() {
        //multiple asserts so that It can assert that preRequisite is correctly added and then removed.
          Course preRequisite = new Course("CSCE102", "Data Structures and Algorithms", "Introduction to data structures and algorithms", 4, new ArrayList<>(Arrays.asList(Season.FALL)), CourseType.DEFAUILT);
          course.addPreRequirisite(preRequisite);
          assertTrue(course.removePreRequisite(preRequisite));
      }

    @Test
    public void testAddCoRequisite() {
        Course coRequisite = new Course("CSCE103", "Database Management Systems", "Introduction to database management systems", 4, new ArrayList<>(Arrays.asList(Season.SPRING)), CourseType.DEFAUILT);
        assertTrue(course.addCoRequirisite(coRequisite));
    }

    @Test
    public void testRemoveCoRequisite() {
        Course coRequisite = new Course("CSCE103", "Database Management Systems", "Introduction to database management systems", 4, new ArrayList<>(Arrays.asList(Season.SPRING)), CourseType.DEFAUILT);
        course.addCoRequirisite(coRequisite);
        assertTrue(course.removeCoRequisite(coRequisite));
    }

    @Test
    public void testIsAvailableTrue() {
        assertTrue(course.isAvailable(Season.FALL));
    }

    @Test
    public void testIsAvailableFalse() {
        assertFalse(course.isAvailable(Season.SUMMER));
    }

    @Test
    public void testGetType() {
        assertEquals(CourseType.DEFAUILT, course.getType());
    }

    @Test
    public void testIsPlacement() {
        assertFalse(course.isPlacement());
        Course placementCourse = new Course("PLC101", "Placement Exam", "Placement exam for introductory courses", 2, new ArrayList<>(Arrays.asList(Season.SPRING)), CourseType.PLACEMENT);
        assertTrue(placementCourse.isPlacement());
    }

    @Test
    public void testGetSubject() {
        assertEquals("CSCE", course.getSubject());
    }

    @Test
    public void testGetCourseNumber() {
        assertEquals(101, course.getCourseNumber());
    }

    @Test
    public void testIsOverlay() {
        assertFalse(course.isOverlay());
        Course overlayCourse = new Course("CSCE201", "Advanced Programming", "Advanced programming concepts", 4, new ArrayList<>(Arrays.asList(Season.SPRING)), CourseType.OVERLAY);
        assertTrue(overlayCourse.isOverlay());
    }

    @Test
    public void testToString() {
        assertEquals("Introduction to Computer Science", course.toString());
    }
}