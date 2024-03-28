import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class DataLoaderTest {

    private DataLoader dataLoader;
    private UserList userList;
    private ArrayList<User> users;

    @BeforeEach
    public void setup(){
        dataLoader = DataLoader.getInstance();
        userList = UserList.getInstance();
        users = userList.getUsers();
        userList.addUser(new Advisor("GMiller", "123abc!", "George", "Miller", "GMiller@email.sc.edu"));
        userList.addUser(new Admin("Admin", "Password1!", "Admin", "McAdmin", "admin@admin.sc.edu"));
        DataWriter.getInstance().saveData();
    }

    @AfterEach
    public void tearDown() {
        users.remove(userList.findUser("GMiller"));
        users.remove(userList.findUser("Admin"));
        DataWriter.getInstance().saveData();
    }

    @Test
    public void testLoadData() {
        dataLoader.loadData();
        assertTrue(users.contains(userList.findUser("GMiller")));
        assertTrue(users.contains(userList.findUser("Admin")));
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
}