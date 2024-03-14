import java.util.ArrayList;
import java.util.Locale.Category;

public class Requirement {
    private String name;
    private Category category;
    private ArrayList<String> courseIDs;
    private int creditHoursRequired;

    public Requirement(String name, Category category, ArrayList<String> courseIDs, int creditHoursRequired) {
        this.name = name;
        this.category = category;
        this.courseIDs = courseIDs;
        this.creditHoursRequired = creditHoursRequired;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public ArrayList<String> getCourseIDs() {
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
        this.courseIDs = courseIDs;
    }

    public void setCreditHoursRequired(int creditHoursRequired) {
        this.creditHoursRequired = creditHoursRequired;
    }

    public boolean addCourse(String courseID) {
        if (!hasCourse(courseID)) {
            courseIDs.add(courseID);
            return true;
        }
        return false;
    }

    public ArrayList<String> addCourses(ArrayList<String> courseIDs) {
        courseIDs.addAll(courseIDs);
        return courseIDs;
    }

    public boolean hasCourse(String courseID) {
        return courseIDs.contains(courseID);
    }

    public Status getstatus(Credit credit) {
        return Status.getStatus(credit);
    }

}
