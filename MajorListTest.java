import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.UUID;

public class MajorListTest {

  private MajorList majorList;
  private UUID id;
  private String name;
  private String school;
  private String department;

    @BeforeEach
    public void setUp() {
        majorList = MajorList.getInstance();
        id = UUID.randomUUID();
        name = "Computer Science";
        school = "School of Engineering";
        department = "Computer Science Department";
        majorList.getMajors().clear();
    }

    @Test
    public void testAddMajorReturnsTrueWhenAddingNewMajor() {
        ArrayList<Requirement> requirements = new ArrayList<>();

        boolean added = majorList.addMajor(id, name, school, department, requirements);

        assertTrue(added);
    }


    @Test
    public void testAddedMajorIsInMajorList() {
        ArrayList<Requirement> requirements = new ArrayList<>();
        majorList.addMajor(id, name, school, department, requirements);

        Major addedMajor = majorList.getMajors().get(0);

        assertEquals(name, addedMajor.getName());
    }

    @Test
    public void testAddMajorDoesNotAddDuplicateMajor() {
        ArrayList<Requirement> requirements = new ArrayList<>();
        majorList.addMajor(id, name, school, department, requirements);

        boolean addedDuplicate = majorList.addMajor(id, name, school, department, requirements);

        assertFalse(addedDuplicate);
    }

    @Test
    public void testGetMajorFromNameCorrectName() {
        majorList.addMajor(id, name, school, department, new ArrayList<>());
        Major retrievedMajor = majorList.getMajorFromName(name);
        assertEquals(name, retrievedMajor.getName());
    }

    @Test
    public void testGetMajorFromName() {
        ArrayList<Requirement> requirements = new ArrayList<>();
        majorList.addMajor(id, name, school, department, requirements);

        Major retrievedMajor = majorList.getMajorFromName(name);
        assertNotNull(retrievedMajor);
        assertEquals(name, retrievedMajor.getName());
    }

    @Test
    public void testGetMajorFromID() {
        ArrayList<Requirement> requirements = new ArrayList<>();
        majorList.addMajor(id, name, school, department, requirements);

        Major retrievedMajor = majorList.getMajorFromID(id);
        assertNotNull(retrievedMajor);
        assertEquals(id, retrievedMajor.getId());
    }

    @Test
    public void testSingletonBehavior() {
        MajorList majorList1 = MajorList.getInstance();
        MajorList majorList2 = MajorList.getInstance();
        assertSame(majorList1, majorList2);
    }
}