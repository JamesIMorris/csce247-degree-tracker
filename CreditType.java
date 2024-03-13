public enum CreditType {
  DEFAULT("C"),
  TRANSFER("TC"),
  WITHDRAWN("W");

  String abbreviation;

  CreditType(String abbreviation){
    this.abbreviation = abbreviation;
  }

  public String getAbbreviation() {
      return abbreviation;
  }
}
