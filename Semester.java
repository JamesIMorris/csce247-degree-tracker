
public class Semester{
  private int year;
  private Season season;

  public Semester(int year, Season season){
    this.year = year;
    this.season = season;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public Season getSeason() {
    return season;
  }

  public void setSeason(Season season) {
    this.season = season;
  }

  public int compare(Semester compareSemester){
    if(this.year < compareSemester.getYear())
      return -1;
    if(this.year > compareSemester.getYear())
      return 1;
    if(this.season.quarter() < compareSemester.getSeason().quarter())
      return -1;
    if(this.season.quarter() > compareSemester.getSeason().quarter())
      return 1;
    return 0;
  }
}