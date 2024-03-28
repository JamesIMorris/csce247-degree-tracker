import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class DataLoaderTest {

    private DataLoader dataLoader;
    private UserList userList;
    private ArrayList<User> users;

    @Before
    public void oneTimeSetup(){
        dataLoader = DataLoader.getInstance();
        dataLoader.loadData();
        userList = UserList.getInstance();
        users = userList.getUsers();
        ArrayList<Student> advisorUsers = new ArrayList<Student>();
        userList.addUser(new Advisor("GMiller", "123abc!", "George", "Miller", "GMiller@email.sc.edu", advisorUsers));
        userList.addUser(new Admin("Admin", "Password1!", "Admin", "McAdmin", "admin@admin.sc.edu"));
        DataWriter.getInstance().saveData();
    }

    @BeforeEach
    public void setup(){
        dataLoader = DataLoader.getInstance();
        userList = UserList.getInstance();
    }

    @After
    public void oneTimeTearDown() {
        users = userList.getUsers();
        users.remove(userList.findUser("GMiller"));
        users.remove(userList.findUser("Admin"));
        DataWriter.getInstance().saveData();
    }

    @Test
    public void testLoadCourses() {
        assertTrue(dataLoader.loadCourses(""));
    }

    @Test
    public void testLoadMajors() {
        assertTrue(dataLoader.loadMajors(""));
    }

    @Test
    public void testLoadRequirements() {
        assertTrue(dataLoader.loadRequirements(""));
    }

    @Test
    public void testLoadUsers() {
        assertTrue(dataLoader.loadUsers(""));
    }

    @Test
    public void testLoadUserData() {
        assertTrue(dataLoader.loadUserData(""));
    }

    @Test
    public void testLoadStudents() {
        assertTrue(dataLoader.loadStudents(""));
    }

    @Test
    public void testLoadAdvisors() {
        assertTrue(dataLoader.loadAdvisors(""));
    }

    @Test
    public void testLoadAdmin() {
        assertTrue(dataLoader.loadAdmin(""));
    }

    @Test
    public void testLoadData() {
        dataLoader.loadData();
        users = userList.getUsers();

        assertTrue(users.contains(userList.findUser("GMiller")));
        assertTrue(users.contains(userList.findUser("Admin")));
    }
}