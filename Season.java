public enum Season {
  SPRING("Spring", "SP", 1, 2),
  SUMMER("Summer", "SU", 2, 2),
  FALL("Fall", "FA", 3, 1),
  WINTER("Winter", "WI", 4, 1);
  

  String string;
  String abbreviation;
  int quarter;
  int half;

  Season(String string, String abbreviation, int quarter, int half){
    this.string = string;
    this.abbreviation = abbreviation;
    this.quarter = quarter;
    this.half = half;
  }

  public String toString(){
    return string;
  }
  public String abbrevation(){
    return abbreviation;
  }
  public int quarter(){
    return quarter;
  }
  public int half(){
    return half;
  }

  static Season fromString(String string) {
    String abbreviation = string.substring(0, 2);
    if(abbreviation.equalsIgnoreCase("sp"))
      return SPRING;
    if(abbreviation.equalsIgnoreCase("su"))
     return SUMMER;
    if(abbreviation.equalsIgnoreCase("fa"))
      return FALL;
    if(abbreviation.equalsIgnoreCase("wi"))
      return WINTER;
    return null;
  }
}