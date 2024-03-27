public enum CreditType {
  DEFAULT("Default", "DC"),
  TRANSFER("Transfer", "TC"),
  WITHDRAWN("Withdrawn", "W");

  String string;
  String abbreviation;

  CreditType(String string, String abbreviation){
    this.string = string;
    this.abbreviation = abbreviation;
  }

  public static CreditType fromString(String string){
    for(CreditType type : CreditType.values())
      if(type.getString().equalsIgnoreCase(string))
        return type;
    return null;
  }

  public String getString(){
    return string;
  }
  public String getAbbreviation() {
      return abbreviation;
  }
}
