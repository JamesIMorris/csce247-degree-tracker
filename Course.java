import java.util.ArrayList;

public class Course {
    private String courseID;
    private String courseName;
    private String courseDescription;
    private int creditHours;
    private ArrayList<Season> semesterAvailability;
    private ArrayList<String> preRequisites;
    private ArrayList<String> coRequisites;
    private CourseType type;

    public Course(String courseID, String courseName, String courseDescription, int creditHours, ArrayList<Season> semester Availability, ArrayList<String> preRequisites, ArrayList<String> coRequisites, boolean isOverlay){

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
        this.description = description;
    }
    public int getCreditHours(){
        return creditHours;
    }
    public void getCreditHours(int creditHours){
        this.creditHours = creditHours;
    }
    public ArrayList<Season> getSemeseterAvailabilty(){
        retrun semesterAvailability;
    }
    public void addSeason(Season season){
        
    }
    public void removeSeason(Season season){

    }
    public ArrayList<String> getPreRequisites(){
        return preRequisites;
    }
    public boolean addPreRequirisite(String courseID){
        return false;
    }  
    public boolean removePreRequisite(String courseID){
        return false;
    }
    public ArrayList<String> getCoRequisites(){
        return preRequisites;
    }
    public boolean addCoRequirisite(String courseID){
        return false;
    }
    public boolean removeCoRequisite(String courseID){
        return false;
    }

    public boolean isAvailable(Season season){
        return false;
    }

    public boolean isOverlay(){
        return false;
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
