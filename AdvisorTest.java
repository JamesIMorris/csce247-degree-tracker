import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
// import java.util.ArrayList;

public class AdvisorTest {

    private Advisor advisor;
    private Student student;

    @BeforeEach
    public void setUp() {
        advisor = new Advisor("advisor1", "password", "John", "Doe", "john.doe@example.com");
        student = new Student("student1", "password", "Alice", "Smith", "alice.smith@example.com", "123456");
        advisor.getAdvisees().clear();
    }

    @Test
    public void testAddAdvisee() {
        assertTrue(advisor.addAdvisee(student));
        assertTrue(advisor.getAdvisees().contains(student));
    }

    @Test
    public void testAddNullAdvisee() {
        assertFalse(advisor.addAdvisee(null));
    }

    @Test
    public void testAddExistingAdvisee() {

        advisor.addAdvisee(student);
        assertFalse(advisor.addAdvisee(student));
    }

    @Test
    public void testAddAdviseeByUsername() {
      UserList.getInstance().getUsers().add(student);
      assertTrue(advisor.addAdvisee("student2"));
    }

    @Test
    public void testAddNonexistentAdviseeByUsername() {
        assertFalse(advisor.addAdvisee("nonexistent"));
    }

    @Test
    public void testRemoveAdvisee() {
        advisor.addAdvisee(student);
        assertTrue(advisor.removeAdvisee(student));
        assertFalse(advisor.getAdvisees().contains(student));
    }

    @Test
    public void testRemoveNullAdvisee() {
        assertFalse(advisor.removeAdvisee(null));
    }

    @Test
    public void testRemoveNonexistentAdvisee() {
        assertTrue(advisor.removeAdvisee(student));
    }
}