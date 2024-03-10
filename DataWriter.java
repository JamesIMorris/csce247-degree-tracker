import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter extends DataConstants {
    public boolean setCourse;
    public boolean setUsers;
    public boolean setMajors;

    // Courses
    public boolean saveCourses() {
        CourseList course = CourseList.getInstance();
        ArrayList<Course> courseList = CourseList.getCourse();
        JSONArray jsonCourses = new JSONArray();

        for (int i = 0; i < courseList.size(); i++) {
            jsonCourses.add(getCourseJSON(courseList.get(i)));
        }

        try (FileWriter file = new FileWriter(COURSE_FILE_NAME)) {

            file.write(jsonCourses.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject getCourseJSON(Course course) {
        JSONObject courseDetails = new JSONObject();
        courseDetails.put(COURSE_ID, course.getCourseID().toString());
        courseDetails.put(COURSE_NAME, course.getCourseName());
        courseDetails.put(COURSE_DESCRIPTION, course.getCourseDescription());
        courseDetails.put(CREDIT_HOURS, course.getCreditHours());
        courseDetails.put(SEMESTER_AVAILABILITY, course.getSemesterAvailability());
        courseDetails.put(COURSE_PRE_REQUISISTES, course.getPreRequisites());
        courseDetails.put(COURSE_CO_REQUISITES, course.getCoRequisites());
        courseDetails.put(COURSE_TYPE, course.getCourseType());

        return courseDetails;
    }

    // Users
    public boolean saveUsers() {
        UserList user = UserList.getInstance();
        ArrayList<User> userList = user.getUsers();
        JSONArray jsonUsers = new JSONArray();

        for (int i = 0; i < userList.size(); i++) {
            jsonUsers.add(getUserJSON(userList.get(i)));
        }

        try (FileWriter file = new FileWriter(USER_FILE_NAME)) {

            file.write(jsonUsers.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject getUserJSON(User user) {
        JSONObject userDetails = new JSONObject();
        userDetails.put(USER_NAME, user.getUsername());
        userDetails.put(FIRST_NAME, user.getFirstName());
        userDetails.put(LAST_NAME, user.getLastName());
        userDetails.put(EMAIL, user.getEmail());
        userDetails.put(PASSWORD, user.getPassword());
        userDetails.put(USER_TYPE, user.getUserType());

        return userDetails;
    }

    // Students
    public boolean saveStudents() {
        UserList User = UserList.getInstance();
        ArrayList<User> userList = User.getUsers();
        JSONArray jsonUsers = new JSONArray();

        for (int i = 0; i < userList.size(); i++) {
            jsonUsers.add(getStudentJSON(userList.get(i)));
        }

        try (FileWriter file = new FileWriter(STUDENT_FILE_NAME)) {

            file.write(jsonUsers.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject getStudentJSON(Student student) {
        JSONObject studentDetails = new JSONObject();
        // Username, Major, and requirement availability not added in constants
        studentDetails.put(STUDENT_USER_NAME, student.getCourseID());
        studentDetails.put(STUDENT_AVAILABLE, student.getCourseID());
        studentDetails.put(STUDENT_MAJOR, student.getMajor());
        
        studentDetails.put(STUDENT_REQUIREMENT, student.getRequirementCreditHours());
        studentDetails.put(STUDENT_REQUIREMENTS_LIST, student.getRequirements());

        JSONArray studentArray = new JSONArray();
        studentArray.add(STUDENT_COURSE, student.getCourseID());
        studentArray.add(STUDENT_SEMESTER_TAKEN, student.getCourseName());
        studentArray.add(STUDNET_YEAR_TAKEN, student.getCreditHours());
        studentArray.add(STUDENT_SEASON, student.getCourseDescription());
        studentArray.add(STUDENT_GRADE, student.getSemesterAvailability());
        studentArray.add(STUDENT_COURSE_TYPE, student.getCourseType());
        studentArray.add(STUDENT_REQUIREMENT, student.getRequirementCreditHours());
        studentArray.add(STUDENT_REQUIREMENTS_LIST, student.getRequiements());
        studentArray.add(STUDENT_AVAILABLE, student.getSemesterAvailability());


        studentArray.put(STUDENT_CREDITS, student.getCredits());

        studentDetails.put(STUDENT_NOTE, student.getNotes());

        return studentDetails;
    }

    // Majors
    public boolean saveMajors() {
        MajorList course = MajorList.getInstance();
        ArrayList<Major> courseList = MajorList.getMajors();
        JSONArray jsonCourses = new JSONArray();

        for (int i = 0; i < majorList.size(); i++) {
            jsonMajor.add(getMajorJSON(courseList.get(i)));
        }

        try (FileWriter file = new FileWriter(MAJOR_FILE_NAME)) {

            file.write(jsonMajor.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject getMajorJSON(Major major) {
        JSONObject majorDetails = new JSONObject();
        majorDetails.put(MAJOR_ID, major.getMajorID().toString());
        majorDetails.put(MAJOR_NAME, major.getName());
        majorDetails.put(MAJOR_SCHOOL, major.getSchool());
        majorDetails.put(MAJOR_DEPARTMENT, major.getDepartment());
        majorDetails.put(MAJOR_REQUIREMENTS, major.getRequirements());

        return majorDetails;
    }
}