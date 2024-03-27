import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SeasonTest{
@Test
  public void testToStringSpring() {
    assertEquals("Spring", Season.SPRING.toString());
    }

  @Test
  public void testToStringSpringError() {
      assertEquals("SpRing", Season.SPRING.toString());
    }

    @Test
    public void testToStringSummer() {
        assertEquals("Summer", Season.SUMMER.toString());
    }

    @Test
  public void testToStringSummerError() {
      assertEquals("SummEr", Season.SUMMER.toString());
    }

    @Test
  public void testToStringFallError() {
      assertEquals("fAlL", Season.FALL.toString());
    }

    @Test
    public void testToStringFall() {
        assertEquals("Fall", Season.FALL.toString());
    }

    @Test
    public void testToStringWinter() {
        assertEquals("Winter", Season.WINTER.toString());
    }

    @Test
  public void testToStringWinterError() {
      assertEquals("wiNTer", Season.WINTER.toString());
    }

    @Test
    public void testAbbreviationSP() {
        assertEquals("SP", Season.SPRING.abbrevation());
    }

    @Test
    public void testAbbreviationSU() {
        assertEquals("SU", Season.SUMMER.abbrevation());
    }

    @Test
    public void testAbbreviationFA() {
        assertEquals("FA", Season.FALL.abbrevation());
    }

    @Test
    public void testAbbreviationWI() {
        assertEquals("WI", Season.WINTER.abbrevation());
    }

    @Test
    public void testQuarterSpring() {
        assertEquals(1, Season.SPRING.quarter());
    }

    @Test
    public void testQuarterSummer() {
        assertEquals(2, Season.SUMMER.quarter());
    }

    @Test
    public void testQuarterFall() {
        assertEquals(3, Season.FALL.quarter());
    }

    @Test
    public void testQuarterWinter() {
        assertEquals(4, Season.WINTER.quarter());
    }

    @Test
    public void testHalfSpring() {
        assertEquals(2, Season.SPRING.half());
    }

    @Test
    public void testHalfSummer() {
        assertEquals(2, Season.SUMMER.half());
    }

    @Test
    public void testHalfFall() {
        assertEquals(1, Season.FALL.half());
    }

    @Test
    public void testHalfWinter() {
        assertEquals(1, Season.WINTER.half());
    }

    @Test
    public void testFromStringSpring() {
        assertEquals(Season.SPRING, Season.fromString("SprIng"));
    }

    @Test
    public void testFromStringSummer() {
        assertEquals(Season.SUMMER, Season.fromString("SumMer"));
    }

    @Test
    public void testFromStringFall() {
        assertEquals(Season.FALL, Season.fromString("FaLl"));
    }
    @Test
    public void testFromStringWinter() {
        assertEquals(Season.WINTER, Season.fromString("winter"));
    }
    @Test
    public void testFromStringWinterCaseError() {
        assertEquals(Season.WINTER, Season.fromString("WintEr"));
    }

    public void testFromStringInvalidInput() {
      assertNull(Season.fromString("Invalid"));
  }
}