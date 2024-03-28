import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CreditTypeTest {

  @Test
  public void testGetAbbreviationDC() {
      assertEquals("DC", CreditType.DEFAULT.getAbbreviation());
      
  }

  @Test
  public void testGetInvalidAbbreviationDC() {
      assertNotNull("IVALID", CreditType.DEFAULT.getAbbreviation());
  }

  @Test
  public void testGetEmptyAbbreviationDC() {
      assertNotNull("", CreditType.DEFAULT.getAbbreviation());
  }


  @Test
  void testGetAbbreviationTC(){
    assertEquals("TC", CreditType.TRANSFER.getAbbreviation());
  }

  @Test
  void testGetAbbreviationW(){
    assertEquals("W", CreditType.WITHDRAWN.getAbbreviation());
  }
}