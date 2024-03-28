import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.UUID;

public class MajorTest {
    private Major major;
    private UUID id;
    private String name;
    private String school;
    private String department;
    private ArrayList<Requirement> requirements;
    private Requirement requirement;

    @BeforeEach
    public void setUp() {
        id = UUID.randomUUID();
        name = "Computer Science";
        school = "School of Engineering";
        department = "Computer Science Department";
        requirements = new ArrayList<>();
        requirement = new Requirement("Test Requirement", Category.CC, new ArrayList<>(), 120);
        major = new Major(id, name, school, department, requirements);
        major.addRequirement(requirement);
    }

    @AfterEach
    public void tearDown() {
      major = null;
      requirement = null;
    }

    @Test
    public void testGetRequirement() {
      assertEquals(requirement, major.getRequirement("Test Requirement"));
    }

    @Test
    public void testGetRequirementNotFound() {
      assertNull(major.getRequirement("Nonexistent Requirement"));
    }

    @Test
    public void testAddRequirement() {
    boolean added = major.addRequirement(requirement);
    assertTrue(added);
}

@Test
public void testGetRequirementsSizeAfterAddingRequirement() {
    major.addRequirement(requirement);
    assertEquals(2, major.getRequirements().size());
}


}