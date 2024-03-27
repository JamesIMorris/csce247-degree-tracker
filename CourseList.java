import java.util.ArrayList;

public class CourseList {
  private static CourseList courseList;
  private ArrayList<Course> courses;

  private CourseList() {
    courses = new ArrayList<>();
    //DataLoader.getInstance();
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
    ArrayList<Course> addList = new ArrayList<Course>();
    for (Course course : courses) {
      if (!this.courses.contains(course))
        addList.add(course);
    }
    this.courses.addAll(addList);
    return addList;
  }

  public Course getCourseFromName(String name) {
    for (Course course : courses) {
      if (course.getCourseName().equals(name)) {
        return course;
      }
    }
    return null;
  }

  public Course getCourseFromID(String id) {
    for (Course course : courses) {
      if (course.getCourseID().equals(id)) {
        return course;
      }
    }
    return null;
  }

  public ArrayList<Course> getCourses() {
    return courses;
  }
}
