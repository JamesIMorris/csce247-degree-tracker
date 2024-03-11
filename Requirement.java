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

    public Status getstatus() {
        return Status.getstatus(credit);
    }
}
