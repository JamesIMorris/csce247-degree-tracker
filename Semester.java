import java.time.LocalDate;

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

  public static Semester current(){
    int currentYear = LocalDate.now().getYear();
    Season currentSeason;
    int currentDay = LocalDate.now().getDayOfYear();
    if(currentDay > 350 || currentDay < 8)
      currentSeason = Season.WINTER;
    else if(currentDay < 95)
      currentSeason = Season.SPRING;
    else if(currentDay > 200)
      currentSeason = Season.FALL;
    else
      currentSeason = Season.SUMMER;
    return new Semester(currentYear, currentSeason);
  }

  public static Semester fromString(String abbreviation){
      int year = Integer.parseInt((abbreviation.substring(2, 4))) + 2000;
      Season season = Season.fromString(abbreviation.substring(0, 2));
      return new Semester(year, season);
  }
}