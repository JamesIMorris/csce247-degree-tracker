public enum Season {
  SPRING("Spring", "SP", 1),
  WINTER("Winter", "WI", 2),
  FALL("Fall", "FA", 3),
  SUMMER("Summer", "SU", 4);

  String string;
  String abbreviation;
  int quarter;

  Season(String string, String abbreviation, int quarter){
    this.string = string;
    this.abbreviation = abbreviation;
    this.quarter = quarter;
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
}
