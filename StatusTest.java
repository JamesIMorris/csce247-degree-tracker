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
      Course course1 = new Course("Course1", "Course 1", "", 3, new ArrayList<>(), CourseType.DEFAUILT);
      Course course2 = new Course("Course2", "Course 2", "", 3, new ArrayList<>(), CourseType.DEFAUILT);
      Course course3 = new Course("Course3", "Course 3", "", 3, new ArrayList<>(), CourseType.DEFAUILT);
    }

    @AfterEach
    public void tearDown() {
        credits.clear();
    }

    @Test
    public void testGetStatusArrayListComplete() {
      Credit completeCredit = new Credit(course1, new Semester(2024, Season.SPRING));
      completeCredit.setGrade(80);
      credits.add(completeCredit);
      assertEquals(Status.COMPLETE, Status.getStatus(credits));
    }

    @Test
    public void testGetStatusArrayListInProgress() {
      Credit inProgressCredit = new Credit(course2, new Semester(2024, Season.SPRING));
      inProgressCredit.setGrade(70);
      credits.add(inProgressCredit);
      assertEquals(Status.IN_PROGRESS, Status.getStatus(credits));
    }


    @Test
    public void testGetStatusArrayListNotStarted() {
      Credit notStartedCredit = new Credit(course3, new Semester(2024, Season.SPRING));
      notStartedCredit.setGrade(0);
      credits.add(notStartedCredit);
      assertEquals(Status.NOT_STARTED, Status.getStatus(credits));
      
    }

    @Test
    public void testGetStatusCreditComplete() {
      Credit completeCredit = new Credit(course1, new Semester(2024, Season.SPRING));

      completeCredit.setGrade(80);

      credits.add(completeCredit);

      assertEquals(Status.COMPLETE, Status.getStatus(completeCredit));
    }

    @Test
    public void testGetStatusCreditInProgress() {

      Credit inProgressCredit = new Credit(course2, new Semester(2024, Season.SPRING));

      inProgressCredit.setGrade(70);

      credits.add(inProgressCredit);

      assertEquals(Status.IN_PROGRESS, Status.getStatus(inProgressCredit));
    }

    @Test
    public void testGetStatusCreditNotStarted() {
      Credit notStartedCredit = new Credit(course3, new Semester(2024, Season.SPRING));

      notStartedCredit.setGrade(0);

      credits.add(notStartedCredit);
      
      assertEquals(Status.NOT_STARTED, Status.getStatus(notStartedCredit));
    }
}