import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class SemesterTest {

    private Semester semester;

    @BeforeEach
    public void setUp() {
        semester = new Semester(2024, Season.FALL);
    }

    @AfterEach
    public void tearDown() {
        semester = null;
    }

    @Test
    public void testGetAbbreviation() {
        assertEquals("FA24", semester.getAbbreviation());
    }

    @Test
    public void testCompare() {
        Semester otherSemester = new Semester(2023, Season.SPRING);
        assertEquals(1, semester.compare(otherSemester));
    }

    @Test
    public void testCurrent() {
        Semester currentSemester = Semester.current();
        assertEquals(2024, currentSemester.getYear());
    }

    @Test
    public void testFromString() {
        Semester parsedSemester = Semester.fromString("FA24");
        assertEquals(2024, parsedSemester.getYear());
        assertEquals(Season.FALL, parsedSemester.getSeason());
    }

    @Test
    public void testTrueSemester() {
        Semester winterSemester = new Semester(2024, Season.WINTER);
        Semester trueSemester = winterSemester.trueSemester();
        assertEquals(2023, trueSemester.getYear());
        assertEquals(Season.FALL, trueSemester.getSeason());
    }

    @Test
    public void testIncrementSemester() {
        semester.incrementSemester();
        assertEquals(2025, semester.getYear());
        assertEquals(Season.SPRING, semester.getSeason());
    }
}
