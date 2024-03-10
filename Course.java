import java.util.ArrayList;

public class Course {
    private String courseID;
    private String courseName;
    private String courseDescription;
    private int creditHours;
    private ArrayList<Season> semesterAvailability;
    private ArrayList<String> preRequisites;
    private ArrayList<String> coRequisites;
    private boolean isOverlay;
    private CourseType type;

    public Course(String courseID, String courseName, String courseDescription, int creditHours, ArrayList<Season> semesterAvailability, ArrayList<String> preRequisites, ArrayList<String> coRequisites, boolean isOverlay){
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.creditHours = creditHours;
        this.semesterAvailability = semesterAvailability;
        this.preRequisites = preRequisites;
        this.coRequisites = coRequisites;
        this.isOverlay = isOverlay;
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
    public ArrayList<String> getPreRequisites(){
        return preRequisites;
    }
    public boolean addPreRequirisite(String courseID){
        return preRequisites.add(courseID);
    }  
    public boolean removePreRequisite(String courseID){
        return preRequisites.remove(courseID);
    }
    public ArrayList<String> getCoRequisites(){
        return coRequisites;
    }
    public boolean addCoRequirisite(String courseID){
        return coRequisites.add(courseID);
    }
    public boolean removeCoRequisite(String courseID){
        return coRequisites.remove(courseID);
    }

    public boolean isAvailable(Season season){
        return semesterAvailability.contains(season);
    }

    public boolean isOverlay(){
        return isOverlay();
    }

    public boolean isPlacement(){
        return false;
    }

    public String getSubject(){
        return "";
    }

    public int getCourseNumber(){
        return 1;
    }

  }
