import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTypeTest {
  private UserType advisor;
  private UserType student;
  private UserType admin;

  @BeforeEach
  public void setUp(){
    student = UserType.STUDENT;
    advisor = UserType.ADVISOR;
    admin = UserType.ADMIN;
  }

    @Test
    void testStudentToString() {
        assertEquals("Student", student.toString());
    }

    @Test
    void testStudentToStringCaseError() {
        assertEquals("StUdEnt", student.toString());
    }

    @Test
    void testStudentStudentIsStaff() {
        assertFalse(student.isStaff());
    }

    @Test
    void testAdvisorToString() {
        assertEquals("Advisor", advisor.toString());
    }

    @Test
    void testAdvisorToStringCaseError() {
      assertEquals("AdvIsor", advisor.toString());
    }

    @Test
    void testAdvisorIsStaff() {
        assertFalse(advisor.isStaff());
    }


    @Test
    void testAdminToString() {
        assertEquals("Administrator", admin.toString());
    }

    @Test
    void testAdminToStringCaseError() {
        assertEquals("AdmInIstRatOr", admin.toString());
    }

    @Test
    void testAdminIsStaff() {
        assertFalse(admin.isStaff());
    }
}