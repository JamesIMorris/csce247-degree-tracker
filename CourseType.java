public enum CourseType {
  DEFAUILT("Default"),
  OVERLAY("Overlay"),
  PLACEMENT("Placement");

  String string;

  CourseType(String string){
    this.string = string;
  }

  public static CourseType fromString(String string){
    if(string.toLowerCase().equals("default"))
      return DEFAUILT;
    if(string.toLowerCase().equals("overlay"))
      return OVERLAY;
    if(string.toLowerCase().equals("placement"))
      return PLACEMENT;
    return null;
  }
}
