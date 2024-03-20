import java.util.ArrayList;

public class Course {
    private String courseID;
    private String courseName;
    private String courseDescription;
    private int creditHours;
    private ArrayList<Season> semesterAvailability;
    private ArrayList<Course> preRequisites;
    private ArrayList<Course> coRequisites;
    private CourseType type;

    public Course(String courseID, String courseName, String courseDescription, int creditHours, ArrayList<Season> semesterAvailability, ArrayList<Course> preRequisites, ArrayList<Course> coRequisites, CourseType type){
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.creditHours = creditHours;
        this.semesterAvailability = semesterAvailability;
        this.preRequisites = preRequisites;
        this.coRequisites = coRequisites;
        this.type = type;
    }
    public Course(String courseID, String courseName, String courseDescription, int creditHours, ArrayList<Season> semesterAvailability, CourseType type){
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.creditHours = creditHours;
        this.semesterAvailability = semesterAvailability;
        this.preRequisites = new ArrayList<Course>();
        this.coRequisites = new ArrayList<Course>();
        this.type = type;
    }

    public String getCourseID(){
        return courseID;
    }
    public String getCourseName(){
        return courseName;
    }
    public void setCourseName(String name){
        this.courseName = name;
    }
    public String getCourseDescprition(){
        return courseDescription;
    }
    public void setCourseDescription(String description){
        this.courseDescription = description;
    }
    public int getCreditHours(){
        return creditHours;
    }
    public void setCreditHours(int creditHours){
        this.creditHours = creditHours;
    }
    public ArrayList<Season> getSemeseterAvailabilty(){
        return semesterAvailability;
    }
    public void addSeason(Season season){
        semesterAvailability.add(season);
    }
    public void removeSeason(Season season){
        semesterAvailability.remove(season);
    }
    public ArrayList<Course> getPreRequisites(){
        return preRequisites;
    }
    public boolean addPreRequirisite(Course course){
        return preRequisites.add(course);
    }  
    public boolean removePreRequisite(Course course){
        return preRequisites.remove(course);
    }
    public void setPreRequisites(ArrayList<Course> courses){
        this.preRequisites = courses;
    }
    public ArrayList<Course> getCoRequisites(){
        return coRequisites;
    }
    public boolean addCoRequirisite(Course course){
        return coRequisites.add(course);
    }
    public boolean removeCoRequisite(Course course){
        return coRequisites.remove(course);
    }
    public void setCoRequisites(ArrayList<Course> courses){
        this.coRequisites = courses;
    }

    public boolean isAvailable(Season season){
        return semesterAvailability.contains(season);
    }

    public CourseType getType() {
        return type;
    }

    public void setType(CourseType type) {
        this.type = type;
    }

    public boolean isPlacement(){
        return false;
    }

    public String getSubject(){
        return courseID.substring(0, Math.min(courseID.length(), 4));
    }

    public int getCourseNumber(){
        String courseNumberStr = courseID.substring(4);
        return Integer.parseInt(courseNumberStr);
    }

    public String toString(){
        return this.courseName;
    }
  }
