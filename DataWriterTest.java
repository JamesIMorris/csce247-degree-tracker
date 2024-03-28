import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class DataWriterTest {

    @Before
    public void oneTimeSetup(){
        UserList userList = UserList.getInstance();
        userList.addUser(new Student("SJones", "57rongP@55w0rd!", "Steve", "Jones", "SJones@email.sc.edu", "P30295019"));
        ArrayList<Student> users = new ArrayList<Student>();
        users.add((Student)userList.findUser("SJones"));
        userList.addUser(new Advisor("GMiller", "123abc!", "George", "Miller", "GMiller@email.sc.edu", users));
        userList.addUser(new Admin("Admin", "Password1!", "Admin", "McAdmin", "admin@admin.sc.edu"));
    }

    @After
    public void oneTimeTearDown(){
        UserList userList = UserList.getInstance();
        ArrayList<User> users = userList.getUsers();
        users.remove(userList.findUser("SJones"));
        users.remove(userList.findUser("GMiller"));
        users.remove(userList.findUser("Admin"));
    }

    @Test
    public void getUserJSONTestStudent(){
        Student user = (Student)UserList.getInstance().findUser("SJones");
        String testString = DataWriter.getInstance().getUserJSON(user).toJSONString();
        String exampleString = ""
        + "{\"firstName\":\"Steve\","
        + "\"lastName\":\"Jones\","
        + "\"password\":\"57rongP@55w0rd!\","
        + "\"type\":\"STUDENT\","
        + "\"email\":\"SJones@email.sc.edu\","
        + "\"username\":\"SJones\"}";
        assertEquals(exampleString, testString);
    }

    @Test
    public void getUserJSONTestAdvisor(){
        Advisor user = (Advisor)UserList.getInstance().findUser("GMiller");
        String testString = DataWriter.getInstance().getUserJSON(user).toJSONString();
        String exampleString = ""
        + "{\"firstName\":\"George\","
        + "\"lastName\":\"Miller\","
        + "\"password\":\"123abc!\","
        + "\"type\":\"ADVISOR\","
        + "\"email\":\"GMiller@email.sc.edu\","
        + "\"username\":\"GMiller\"}";
        assertEquals(exampleString, testString);
    }

    @Test
    public void getUserJSONTestAdmin(){
        Admin user = (Admin)UserList.getInstance().findUser("Admin");
        String testString = DataWriter.getInstance().getUserJSON(user).toJSONString();
        String exampleString = ""
        + "{\"firstName\":\"Admin\","
        + "\"lastName\":\"McAdmin\","
        + "\"password\":\"Password1!\","
        + "\"type\":\"ADMINISTRATOR\","
        + "\"email\":\"admin@admin.sc.edu\","
        + "\"username\":\"Admin\"}";
        assertEquals(exampleString, testString);
    }

    @Test 
    public void getAdvisorJSONTest(){
        Advisor user = (Advisor)UserList.getInstance().findUser("GMiller");
        String testString = DataWriter.getInstance().getAdvisorJSON(user).toJSONString();
        String exampleString = ""
        + "{\"username\":\"GMiller\","
        + "\"advisees\":[\"SJones\"]}";
        assertEquals(exampleString, testString);
    }

    @Test
    public void getAdminJSONTest(){
        Admin user = (Admin)UserList.getInstance().findUser("Admin");
        String testString = DataWriter.getInstance().getAdminJSON(user).toJSONString();
        String exampleString = ""
        + "{\"username\":\"Admin\"}";
        assertEquals(exampleString, testString);
    }

    
}
