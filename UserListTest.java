import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserListTest {

  private UserList userList = UserList.getInstance();
  private ArrayList<User> users = userList.getUsers();

  @BeforeEach
  public void setUp() {
    //userList = UserList.getInstance(); 
    userList.getUsers().clear();
		userList.studentSignup("asmith", "Amy", "Smith", "hello", "803-454-3344","uscid");
		userList.advisorSignup("bwhite", "Bob", "White", "testing", "803-333-3544");
    //DataWriter.saveUsers();
  }

  @AfterEach
  public void tearDown(){
    UserList.getInstance().getUsers().clear();
    //DataWriter.saveUsers();
  }

  @Test
  public void testCreateNewStudent() {
      
      Student student = userList.createNewStudent("testUser", "password", "John", "Doe", "john@example.com", "123456");
      
      assertEquals("testUser", student.getUsername());
      
  }

  @Test
	void testemailAvailable(){
    boolean hasemail = userList.emailAvailable("emailTesting");
    assertFalse(hasemail);
  }

  @Test
  void usernameAvailableFalse(){
    boolean hasusername = userList.usernameAvailable("asmith");
    assertFalse(hasusername);
  }

  @Test
  void usernameAvailableTrue(){
    boolean nousername = userList.usernameAvailable("bsmith");
    assertFalse(nousername);
  }

  @Test
  void tocreateNewAdvisor(){
    Advisor advisor = userList.createNewAdvisor("advisorname", "advisorpassword", "advisorfirstName", "advisorLastName", "advisorEmail");
  }

  @Test
  public void testFindUser(){
    Student student1 = new Student("user1", "password1", "John", "Doe", "john@example.com", "123456");
    Advisor advisor = new Advisor("advisorUser", "password", "Jane", "Smith", "jane@example.com");
    userList.getUsers().add(student1);
    userList.getUsers().add(advisor);

    assertEquals(student1, userList.findUser("user1"));
    assertEquals(advisor, userList.findUser("advisorUser"));
  }

  @Test
  public void testFindUserNotFound(){
    Student student = new Student("user1", "password1", "John", "Doe", "john@example.com", "123456");
    userList.getUsers().add(student);

    assertNull(userList.findUser("nonExistentUser"));
  }

  @Test
  public void testSearchUser() {
     
      userList.createNewStudent("user1", "password", "John", "Doe", "john@example.com", "123456");
      userList.createNewStudent("user2", "password", "Jane", "Doe", "jane@example.com", "654321");

      assertEquals("Should find user with username 'user1'", "user1", userList.searchUser("user1").get(0).getUsername());
      
  }

  @Test
  public void testLogin() {
    userList.studentSignup("testUser", "password", "John", "Doe", "john@example.com", "123456");
    assertTrue(userList.login("testUser", "password"));
  }

  @Test
  void testSearchUserByType() {
    userList.studentSignup("testUser", "password", "John", "Doe", "john@example.com", "123456");
    userList.advisorSignup("advisorUser", "password", "Jane", "Smith", "jane@example.com");
    
    ArrayList<User> studentList = userList.searchUserByType(UserType.STUDENT);
    assertEquals(1, studentList.size());
    assertEquals("testUser", studentList.get(0).getUsername());

    ArrayList<User> advisorList = userList.searchUserByType(UserType.ADVISOR);
    assertEquals(1, advisorList.size());
    assertEquals("advisorUser", advisorList.get(0).getUsername());
}

@Test
void testLogout() {
  userList.setCurrentUser(new Student("testUser", "password", "John", "Doe", "john@example.com", "123456"));
  assertTrue(userList.logout());
  assertNull(userList.getCurrentUser());
}

@Test
void testCreateNewStudentWithValidPassword() {
  Student student = userList.createNewStudent("testUser", "password", "John", "Doe", "john@example.com", "123456");
  assertNotNull(student);
}

@Test
void testCreateNewStudentWithInvalidPassword() {
  Student student = userList.createNewStudent("testUser", "pa", "John", "Doe", "john@example.com", "123456");
  assertNull(student);
  }

  @Test
  public void testStudentSignup() {
    assertTrue(userList.studentSignup("asmith", "hello", "Amy", "Smith", "asmith@example.com", "123456"));
    assertEquals(1, userList.getUsers().size());
    assertEquals("asmith", userList.getUsers().get(0).getUsername());
    assertEquals(UserType.STUDENT, userList.getUsers().get(0).getUserType());
}

@Test
public void testAdvisorSignup() {
  assertTrue(userList.advisorSignup("bwhite", "testing", "Bob", "White", "bwhite@example.com"));
  assertEquals(1, userList.getUsers().size());
  assertEquals("bwhite", userList.getUsers().get(0).getUsername());
  assertEquals(UserType.ADVISOR, userList.getUsers().get(0).getUserType());
}

@Test
public void testFindStudentFromID() {

  Student student1 = new Student("user1", "password1", "John", "Doe", "john@example.com", "123456");
  Student student2 = new Student("user2", "password2", "Jane", "Smith", "jane@example.com", "654321");
  

  userList.getUsers().add(student1);
  userList.getUsers().add(student2);


  assertEquals("user1", userList.findStudentFromID("123456"));
  assertEquals("user2", userList.findStudentFromID("654321"));
}

@Test
public void testFindStudentFromIDNotFound() {
  assertNull(userList.findStudentFromID("nonExistentID"));
}


}