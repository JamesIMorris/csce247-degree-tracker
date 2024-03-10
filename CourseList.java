import java.util.ArrayList;

public class CourseList {
  private CourseList courseList;
  private ArrayList<Course> courses;

  private CourseList() {
    loadCourses();
  }

  public CourseList getInstance() {
    if(courseList == null)
    courseList = new CourseList();
    return courseList;
  }

  public Course addCourse(String courseName, String courseID, String courseDescription, Int creditHours,
      ArrayList<Season> semesterAvailability, ArrayList<String> preRequisites, ArrayList<String> coRequisites,
      CourseType type) {
        boolean success = true;
        if (courseName == null
        || courseID == null
        || courseDescription == null
        || creditHours == null
        || semesterAvailability == null
        || preRequisites == null
        || coRequisites == null
        || type == null) {
          return null;
  }
        else return new Course(courseName, courseID, courseDescription, creditHours, semesterAvailability, preRequisites, coRequisites);
}

  public boolean addCourse(Course course){

  }

  public ArrayList<Course> addCourses(ArrayList<Course> courses){

  }

  public Course getCourse(String name) {
    return courseName;
  }

  public Course getCourse(String id) {
    return courseID;
  }
}
