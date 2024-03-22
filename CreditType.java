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

  public String getAbbreviation() {
      return abbreviation;
  }
}
