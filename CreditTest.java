import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class CreditTest {
  private Credit credit;

  @BeforeEach
  public void setUp() {
    ArrayList<Season> semesterAvailability = new ArrayList<>();
    semesterAvailability.add(Season.FALL);
    semesterAvailability.add(Season.SPRING);
    Course course = new Course("CSE101", "Introduction to Computer Science", "Description", 3, semesterAvailability, CourseType.DEFAUILT);
    Semester semester = new Semester(2024, Season.FALL);
    credit = new Credit(course, semester);
    credit.populatePossibleRequirements();
    }

    @AfterEach
    public void tearDown(){
      credit = null;
    }
    @Test
    public void testUpdate() {
        // Test update method
      Requirement requirement = new Requirement("RequirementName", Category.MR, new ArrayList<>(Arrays.asList("CSCE101", "CSCE102")), 6);
      boolean open = true; 
      boolean result = credit.update(requirement, open);
      assertTrue(result);
    }

    @Test
    public void testUpdateReturnsTrueForExistingRequirement() {
        // Test update method with an existing requirement
        Requirement requirement = new Requirement("RequirementName", Category.MR, new ArrayList<>(Arrays.asList("CSCE101", "CSCE102")), 6);
        boolean open = true; 
        boolean result = credit.update(requirement, open);
        assertTrue(result, "update should return true for an existing requirement.");
    }

    @Test
    public void testAddPossibleRequirementAddsToPossibleRequirementsList() {
      Requirement requirement = new Requirement("RequirementName", Category.MR, new ArrayList<>(Arrays.asList("CSCE101", "CSCE102")), 6);
      
      credit.addPossibleRequirement(requirement);
      ArrayList<PossibleRequirement> possibleRequirements = credit.getPossibleRequirements();
      boolean found = false;
      for (PossibleRequirement possibleRequirement : possibleRequirements) {
      if (possibleRequirement.getRequirement().equals(requirement)) {
        found = true;
        break;
      }
    }
    assertTrue(found, "PossibleRequirement should be added to the possibleRequirements list.");
  }
}