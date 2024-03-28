import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.ArrayList;

public class DataLoaderTest {

    private DataLoader dataLoader;

    @BeforeEach
    public void setUp() {
        dataLoader = DataLoader.getInstance();
    }

    @AfterEach
    public void tearDown() {
        // Reset any changes made during the tests
        
        File coursesFile = new File(DataConstants.COURSES_FILE_NAME);
        if (coursesFile.exists()) coursesFile.delete();

        File majorsFile = new File(DataConstants.MAJORS_FILE_NAME);
        if (majorsFile.exists()) majorsFile.delete();

        File usersFile = new File(DataConstants.USERS_FILE_NAME);
        if (usersFile.exists()) usersFile.delete();

        File adminsFile = new File(DataConstants.ADMIN_FILE_NAME);
        if (adminsFile.exists()) adminsFile.delete();

        File advisorsFile = new File(DataConstants.ADVISORS_FILE_NAME);
        if (advisorsFile.exists()) advisorsFile.delete();

        File studentsFile = new File(DataConstants.STUDENTS_FILE_NAME);
        if (studentsFile.exists()) studentsFile.delete();
    }

    @Test
    public void testLoadData() {
        assertTrue(dataLoader.loadData());
    }

    @Test
    public void testLoadCourses() {
        assertTrue(dataLoader.loadCourses());
    }

    @Test
    public void testLoadMajors() {
        assertTrue(dataLoader.loadMajors());
    }

    @Test
    public void testLoadRequirements() {
        assertTrue(dataLoader.loadRequirements());
    }

    @Test
    public void testLoadUsers() {
        assertTrue(dataLoader.loadUsers());
    }

    @Test
    public void testLoadUserData() {
        assertTrue(dataLoader.loadUserData());
    }

    @Test
    public void testLoadStudents() {
        assertTrue(dataLoader.loadStudents());
    }

    @Test
    public void testLoadAdvisors() {
        assertTrue(dataLoader.loadAdvisors());
    }

    @Test
    public void testLoadAdmin() {
        assertTrue(dataLoader.loadAdmin());
    }
}