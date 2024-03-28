import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class PossibleRequirementTest {

    private Requirement requirement;
    private PossibleRequirement possibleRequirement;

    @BeforeEach
    public void setUp() {
        requirement = new Requirement("Test Requirement", Category.MR, new ArrayList<>(), 10);
        possibleRequirement = new PossibleRequirement(requirement);
    }

    @AfterEach
    public void tearDown() {
        requirement = null;
        possibleRequirement = null;
    }

    @Test
    public void testConstructorWithRequirement() {
        assertEquals(requirement, possibleRequirement.getRequirement());
    }

    @Test
    public void testConstructorWithRequirementAndPossible() {
        possibleRequirement = new PossibleRequirement(requirement, true);
        assertTrue(possibleRequirement.getPossible());
    }

    @Test
    public void testGetRequirement() {
        assertEquals(requirement, possibleRequirement.getRequirement());
    }

    @Test
    public void testSetRequirement() {
        Requirement newRequirement = new Requirement("New Requirement", Category.CC, new ArrayList<>(), 8);
        possibleRequirement.setRequirement(newRequirement);
        assertEquals(newRequirement, possibleRequirement.getRequirement());
    }

    @Test
    public void testGetPossible() {
        assertFalse(possibleRequirement.getPossible());
    }

    @Test
    public void testSetPossible() {
        possibleRequirement.setPossible(true);
        assertTrue(possibleRequirement.getPossible());
    }

}