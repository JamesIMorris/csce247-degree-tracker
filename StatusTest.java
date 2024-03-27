import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StatusTest {
    private ArrayList<Credit> credits;
    private Course course1;
    private Course course2;
    private Course course3;

    @BeforeEach
    public void setUp() {
        credits = new ArrayList<>();
    }

    @AfterEach
    public void tearDown() {
        credits.clear();
    }

    @Test
    public void testGetStatusArrayList() {
      Course course1 = new Course("Course1", "Course 1", "", 3, new ArrayList<>(), CourseType.DEFAUILT);
      Course course2 = new Course("Course2", "Course 2", "", 3, new ArrayList<>(), CourseType.DEFAUILT);
      Course course3 = new Course("Course3", "Course 3", "", 3, new ArrayList<>(), CourseType.DEFAUILT);

      Credit completeCredit = new Credit(course1, new Semester(2024, Season.SPRING));
      completeCredit.setGrade(80);

      Credit inProgressCredit = new Credit(course2, new Semester(2024, Season.SPRING));
      inProgressCredit.setGrade(70);

      Credit notStartedCredit = new Credit(course3, new Semester(2024, Season.SPRING));
      notStartedCredit.setGrade(0);

      credits.add(completeCredit);
      credits.add(inProgressCredit);
      credits.add(notStartedCredit);
      assertEquals(Status.COMPLETE, Status.getStatus(credits));
    }

    @Test
    public void testGetStatusCredit() {
      Credit completeCredit = new Credit(course1, new Semester(2024, Season.SPRING));
      completeCredit.setGrade(80);

      Credit inProgressCredit = new Credit(course2, new Semester(2024, Season.SPRING));
      inProgressCredit.setGrade(70);

      Credit notStartedCredit = new Credit(course3, new Semester(2024, Season.SPRING));
      notStartedCredit.setGrade(0);

      credits.add(completeCredit);
      credits.add(inProgressCredit);
      credits.add(notStartedCredit);
      assertEquals(Status.COMPLETE, Status.getStatus(completeCredit));
      assertEquals(Status.IN_PROGRESS, Status.getStatus(inProgressCredit));
      assertEquals(Status.NOT_STARTED, Status.getStatus(notStartedCredit));
    }
}