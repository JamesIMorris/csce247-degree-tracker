import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


public class CourseTypeTest {

  @Test
  public void testGetAbbreviationDefault() {
    //Tests that the enum correctly goes to lowercase
      assertEquals(CourseType.DEFAUILT, CourseType.fromString("DefaUlt"));
      
  }

  @Test
  void testGetAbbreviationOverlay(){
    //Tests that the enum correctly goes to lowercase
    assertEquals(CourseType.OVERLAY, CourseType.fromString("OverlAy"));
  }

  @Test
  void testGetAbbreviationPlacement(){
    //Tests that the enum correctly goes to lowercase
    assertEquals(CourseType.PLACEMENT, CourseType.fromString("PlaceMent"));
  }

  @Test
  void testWrongValue(){
    //Tests if it correctly returns null
    assertNull(CourseType.fromString("notCorrecT"));
  }
  
}
