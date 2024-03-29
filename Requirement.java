import java.util.ArrayList;
import java.util.UUID;

public class Requirement {
    private UUID id;
    private String name;
    private Category category;
    private ArrayList<Course> courses;
    private int creditHoursRequired;

    public Requirement(String name, Category category, ArrayList<String> courseIDs, int creditHoursRequired) {
        id = UUID.randomUUID();
        this.name = name;
        this.category = category;
        loadCourses(courseIDs);
        this.creditHoursRequired = creditHoursRequired;
    }

    public Requirement(UUID id, String name, Category category, ArrayList<String> courseIDs, int creditHoursRequired) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.courses = new ArrayList<>();
        loadCourses(courseIDs);
        this.creditHoursRequired = creditHoursRequired;
    }

    public UUID getID(){
        return id;
    }
    public void setID(UUID id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public ArrayList<String> getCourseIDs() {
        ArrayList<String> courseIDs = new ArrayList<String>();
        for(Course course : courses){
            courseIDs.add(course.getCourseID());
        }
        return courseIDs;
    }
    public ArrayList<Course> getCourses(){
        return courses;
    }

    public int getCreditHoursRequired() {
        return creditHoursRequired;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setCourseIDs(ArrayList<String> courseIDs) {
        loadCourses(courseIDs);
    }
    public void setCourses(ArrayList<Course> courses){
        this.courses = courses;
    }

    public void setCreditHoursRequired(int creditHoursRequired) {
        this.creditHoursRequired = creditHoursRequired;
    }

    public boolean addCourse(String courseID) {
        Course course = CourseList.getInstance().getCourseFromID(courseID);
        return addCourse(course);
    }
    public boolean addCourse(Course course){
        if (!hasCourse(course)) {
            courses.add(course);
            return true;
        }
        return false;
    }

    public void addCourses(ArrayList<Course> courses) {
        this.courses.addAll(courses);
    }

    public boolean hasCourse(Course course) {
        return courses.contains(course);
    }

    public Status getstatus(Credit credit) {
        return Status.getStatus(credit);
    }

    private void loadCourses(ArrayList<String> courseIDs){
        ArrayList<Course> courses = new ArrayList<Course>();
        for(String courseID : courseIDs){
            Course course = CourseList.getInstance().getCourseFromID(courseID);
            courses.add(course);
        }
    }

    public static ArrayList<Requirement> allRequirements(){
        ArrayList<Requirement> allRequirements = new ArrayList<Requirement>();
        for(Major major : MajorList.getInstance().getMajors())
            for(Requirement requirement : major.getRequirements())
                if(!allRequirements.contains(requirement))
                    allRequirements.add(requirement);
        return allRequirements;
    }
    public static Requirement fromID(UUID id){
        for(Requirement requirement : allRequirements())
            if(requirement.getID().equals(id))
                return requirement;
        return null;
    }
}
