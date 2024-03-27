import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdminTest {
  private Admin admin;

@BeforeEach
  void setUp(){
    String username = "adminUser";
    String password = "adminPassword";
    String firstName = "John";
    String lastName = "Doe";
    String email = "admin@example.com";
    admin = new Admin(username, password, firstName, lastName, email);
  }

  @AfterEach
  void tearDown(){
    admin = null;
  }

  @Test
  void testAdminUserName(){
    assertEquals("adminUser", admin.getUsername());
  }

  @Test
  void testAdminPassword(){
    assertEquals("adminPassword", admin.getPassword());
  }

  @Test
  void testAdminFirstName(){
    assertEquals("John", admin.getFirstName());
  }

  @Test
  void testAdminLastName(){
    assertEquals("Doe", admin.getLastName());
  }

  @Test
  void testAdminEmail(){
    assertEquals("admin@example.com", admin.getEmail());
  }

  @Test
  void testAdminUserType(){
    assertEquals(UserType.ADMIN, admin.getUserType());
  }
}
