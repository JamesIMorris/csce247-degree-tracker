import java.util.ArrayList;
import java.util.Locale.Category;

public class Requirement {
    private String name;
    private Category category;
    private ArrayList<Course> courses;
    private int creditHoursRequired;

    public Requirement(String name, Category category, ArrayList<String> courseIDs, int creditHoursRequired) {
        this.name = name;
        this.category = category;
        loadCourses(courseIDs);
        this.creditHoursRequired = creditHoursRequired;
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

    public void setCreditHoursRequired(int creditHoursRequired) {
        this.creditHoursRequired = creditHoursRequired;
    }

    public boolean addCourse(String courseID) {
        Course course = CourseList.getInstance().getCourseID(courseID);
        if (!hasCourse(course)) {
            courses.add(course);
            return true;
        }
        return false;
    }

    public ArrayList<String> addCourses(ArrayList<String> courseIDs) {
        courseIDs.addAll(courseIDs);
        return courseIDs;
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
            Course course = CourseList.getInstance().getCourseID(courseID);
            courses.add(course);
        }
    }
}
