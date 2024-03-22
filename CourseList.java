import java.util.ArrayList;

public class CourseList {
  private static CourseList courseList;
  private ArrayList<Course> courses;

  private CourseList() {
    DataLoader.getInstance();
  }

  public static CourseList getInstance() {
    if (courseList == null)
      courseList = new CourseList();
    return courseList;
  }

  public void setCourses(ArrayList<Course> courses) {
    this.courses = courses;
  }

  public Course addCourse(String courseName, String courseID, String courseDescription, int creditHours,
      ArrayList<Season> semesterAvailability, ArrayList<Course> preRequisites, ArrayList<Course> coRequisites,
      CourseType type) {
    if (courseName == null
        || courseID == null
        || courseDescription == null
        || semesterAvailability == null
        || preRequisites == null
        || coRequisites == null
        || type == null)
      return null;
    Course newCourse = new Course(courseID, courseName, courseDescription, creditHours, semesterAvailability,
        preRequisites, coRequisites, type);
    courses.add(newCourse);
    return newCourse;
  }

  public boolean addCourse(Course course) {
    if (course != null) {
      return courses.add(course);
    }
    return false;
  }

  public ArrayList<Course> addCourses(ArrayList<Course> courses) {
    boolean added = true;
    for (Course course : courses) {
      if (!courses.add(course)) {
        added = false;
      }
    }
    if (added) {
      return courses;
    }
    return null;
  }

  public Course getCourse(String name) {
    for (Course course : courses) {
      if (course.getCourseName().equals(name)) {
        return course;
      }
    }
    return null;
  }

  public Course getCourseID(String id) {
    for (Course course : courses) {
      if (course.getCourseID().equals(id)) {
        return course;
      }
    }
    return null;
  }
}
