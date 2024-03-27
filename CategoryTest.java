import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CategoryTest {

    @Test
    public void testFromAbbreviation_NullAbbreviation() {
        assertEquals(Category.REQUIREMENT, Category.fromAbbreviation(null));
    }

    @Test
    public void testFromAbbreviation_EmptyAbbreviation() {
        assertEquals(Category.REQUIREMENT, Category.fromAbbreviation(""));
    }

    @Test
    public void testFromAbbreviation_CC() {
        assertEquals(Category.CC, Category.fromAbbreviation("CC"));
    }

    @Test
    public void testFromAbbreviation_CR() {
        assertEquals(Category.CR, Category.fromAbbreviation("CR"));
    }

    @Test
    public void testFromAbbreviation_PR() {
        assertEquals(Category.PR, Category.fromAbbreviation("PR"));
    }

    @Test
    public void testFromAbbreviation_MR() {
        assertEquals(Category.MR, Category.fromAbbreviation("MR"));
    }

    @Test
    public void testFromAbbreviation_FND() {
        assertEquals(Category.FND, Category.fromAbbreviation("FND"));
    }

    @Test
    public void testFromAbbreviation_CC_AIU() {
        assertEquals(Category.CC_AIU, Category.fromAbbreviation("CC-AIU"));
    }

    @Test
    public void testFromAbbreviation_CC_ARP() {
        assertEquals(Category.CC_ARP, Category.fromAbbreviation("CC-ARP"));
    }

    @Test
    public void testFromAbbreviation_CC_CMS() {
        assertEquals(Category.CC_CMS, Category.fromAbbreviation("CC-CMS"));
    }

    @Test
    public void testFromAbbreviation_CC_CMW() {
        assertEquals(Category.CC_CMW, Category.fromAbbreviation("CC-CMW"));
    }

    @Test
    public void testFromAbbreviation_CC_GFL() {
        assertEquals(Category.CC_GFL, Category.fromAbbreviation("CC-GFL"));
    }

    @Test
    public void testFromAbbreviation_CC_GHS() {
        assertEquals(Category.CC_GHS, Category.fromAbbreviation("CC-GHS"));
    }

    @Test
    public void testFromAbbreviation_CC_GSS() {
        assertEquals(Category.CC_GSS, Category.fromAbbreviation("CC-GSS"));
    }

    @Test
    public void testFromAbbreviation_CC_INF() {
        assertEquals(Category.CC_INF, Category.fromAbbreviation("CC-INF"));
    }

    @Test
    public void testFromAbbreviation_CC_INT() {
        assertEquals(Category.CC_INT, Category.fromAbbreviation("CC-INT"));
    }

    @Test
    public void testFromAbbreviation_CC_SCI() {
        assertEquals(Category.CC_SCI, Category.fromAbbreviation("CC-SCI"));
    }

    @Test
    public void testFromAbbreviation_CC_VSR() {
        assertEquals(Category.CC_VSR, Category.fromAbbreviation("CC-VSR"));
    }

    @Test
    public void testFromAbbreviation_CC_IntegrativeCourse() {
        assertEquals(Category.CC_IntegrativeCourse, Category.fromAbbreviation("CC-IC"));
    }

    @Test
    public void testFromAbbreviation_PR_FoundationalCourse() {
        assertEquals(Category.PR_FoundationalCourse, Category.fromAbbreviation("PR-FC"));
    }

    @Test
    public void testFromAbbreviation_PR_LabSci() {
        assertEquals(Category.PR_LabSci, Category.fromAbbreviation("PR-LS"));
    }

    @Test
    public void testFromAbbreviation_PR_LibArts() {
        assertEquals(Category.PR_LibArts, Category.fromAbbreviation("PR-LA"));
    }

    @Test
    public void testFromAbbreviation_PR_LowerDiv() {
        assertEquals(Category.PR_LowerDiv, Category.fromAbbreviation("PR-LD"));
    }

    @Test
    public void testFromAbbreviation_PR_AppArea() {
        assertEquals(Category.PR_AppArea, Category.fromAbbreviation("PR-AA"));
    }

    @Test
    public void testFromAbbreviation_MR_MajorCourse() {
        assertEquals(Category.MR_MajorCourse, Category.fromAbbreviation("MR-MC"));
    }

    @Test
    public void testFromAbbreviation_MR_Elective() {
        assertEquals(Category.MR_Elective, Category.fromAbbreviation("MR-E"));
    }

    @Test
    public void testFromAbbreviation_InvalidAbbreviation() {
        assertNull(Category.fromAbbreviation("InvalidAbbreviation"));
    }

    @Test
    public void testToString_REQUIREMENT() {
        assertEquals("Requirement", Category.REQUIREMENT.toString());
    }

    @Test
    public void testToString_CC() {
        assertEquals("Carolina Core", Category.CC.toString());
    }

    @Test
    public void testToString_CR() {
        assertEquals("College Requirement", Category.CR.toString());
    }

    @Test
    public void testToString_PR() {
        assertEquals("Program Requirement", Category.PR.toString());
    }

    @Test
    public void testToString_MR() {
        assertEquals("Major Requirement", Category.MR.toString());
    }

    @Test
    public void testToString_FND() {
        assertEquals("Founding Documents", Category.FND.toString());
    }

    @Test
    public void testToString_CC_AIU() {
        assertEquals("Carolina Core - Asthetic and Interpretive Understanding", Category.CC_AIU.toString());
    }

    @Test
    public void testToString_CC_ARP() {
        assertEquals("Carolina Core - Analytical Reasoning and Problem Solving", Category.CC_ARP.toString());
    }

    @Test
    public void testToString_CC_CMS() {
        assertEquals("Carolina Core - Effective, Engaged, and Persuasive Communication: Spoken Component", Category.CC_CMS.toString());
    }

    @Test
    public void testToString_CC_CMW() {
        assertEquals("Carolina Core - Effective, Engaged, and Persuasive Communication: Written", Category.CC_CMW.toString());
    }

    @Test
    public void testToString_CC_GFL() {
        assertEquals("Carolina Core - Global Citizenship and Multicultural Understanding: Foreign Language", Category.CC_GFL.toString());
    }

    @Test
    public void testToString_CC_GHS() {
        assertEquals("Carolina Core - Global Citizenship and Multicultural Understanding: Historival Thinking", Category.CC_GHS.toString());
    }

    @Test
    public void testToString_CC_GSS() {
        assertEquals("Carolina Core - Global Citizenship and Multicultural Understanding: Social Sciences", Category.CC_GSS.toString());
    }

    @Test
    public void testToString_CC_INF() {
        assertEquals("Carolina Core - Informative Literacy", Category.CC_INF.toString());
    }

    @Test
    public void testToString_CC_INT() {
        assertEquals("Carolina Core - Integrative Course", Category.CC_INT.toString());
    }

    @Test
    public void testToString_CC_SCI() {
        assertEquals("Carolina Core - Science Literacy", Category.CC_SCI.toString());
    }

    @Test
    public void testToString_CC_VSR() {
        assertEquals("Carolina Core - Values, Ethics, and Social Responsibility", Category.CC_VSR.toString());
    }

    @Test
    public void testToString_CC_IntegrativeCourse() {
        assertEquals("Carolina Core - Integrative Course", Category.CC_IntegrativeCourse.toString());
    }

    @Test
    public void testToString_PR_FoundationalCourse() {
        assertEquals("Program Requirement - Foundational Course", Category.PR_FoundationalCourse.toString());
    }

    @Test
    public void testToString_PR_LabSci() {
        assertEquals("Program Requirement - Lab Science", Category.PR_LabSci.toString());
    }

    @Test
    public void testToString_PR_LibArts() {
        assertEquals("Program Requirement - Liberal Arts", Category.PR_LibArts.toString());
    }

    @Test
    public void testToString_PR_LowerDiv() {
        assertEquals("Program Requirement - Lower Division", Category.PR_LowerDiv.toString());
    }

    @Test
    public void testToString_PR_AppArea() {
        assertEquals("Program Requirement - Application Area", Category.PR_AppArea.toString());
    }

    @Test
    public void testToString_MR_MajorCourse() {
        assertEquals("Major Requirement - Major Course", Category.MR_MajorCourse.toString());
    }

    @Test
    public void testToString_MR_Elective() {
        assertEquals("Major Requirement - College Elective", Category.MR_Elective.toString());
    }
}