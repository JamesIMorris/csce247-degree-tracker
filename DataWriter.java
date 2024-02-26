import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter {
    public boolean setCourse;
    public boolean setUsers;
    public boolean setMajors;


    public boolean saveCourses(){
        Course course = Course.getInstance();
        ArrayList<Course> courseList = course.getCourses();
        JSONArray jsonCourses = new JSONArray();

        for(int i=0; i < courseList.size(); i++) {
            jsonCourses.add(getCourseJSON(courseList.get(i)));
        }

        try (FileWriter file = new FileWriter(COURSE_FILE_NAME)) {

            file.write(jsonCourses.toJSONString());
            file.flush();

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject = getCourseJSON(Courses course) {
        JSONObject courseDetails = new JSONObject();
        courseDetails.put(COURSE_ID, course.getId().toString());
        courseDetails.put(COURSE_NAME, course.getCourseName());
        courseDetails.put(COURSE_DESCRIPTION, course.getCourseDescription());
        courseDetails.put(COURSE_CREDIT_HOURS, course.getCourseCreditHours());
        courseDetails.put(COURSE_SEMESTER_AVAILABILITY, course.getCourseSemesterAvailability());
        courseDetails.put(COURSE_PREREQUISITES, course.getCoursePreRequisites());
        courseDetails.put(COURSE_COREQUISITES, course.getCourseCoRequisites());
        courseDetails.put(COURSE_TYPE, course.getCourseType());
    }

    public boolean saveUsers(){
        Users user = Users.getInstance();
        ArrayList<Users> userList = user.getUsers();
        JSONArray jsonUsers = new JSONArray();

        for(int i=0; i < userList.size(); i++) {
            jsonUsers.add(getUserJSON(userList.get(i)));
        }

        try (FileWriter file = new FileWriter(COURSE_FILE_NAME)) {

            file.write(jsonCourses.toJSONString());
            file.flush();

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject = getCourseJSON(Courses course) {
        JSONObject courseDetails = new JSONObject();
        courseDetails.put(COURSE_ID, course.getId().toString());
        courseDetails.put(COURSE_NAME, course.getCourseName());
        courseDetails.put(COURSE_DESCRIPTION, course.getCourseDescription());
        courseDetails.put(COURSE_CREDIT_HOURS, course.getCourseCreditHours());
        courseDetails.put(COURSE_SEMESTER_AVAILABILITY, course.getCourseSemesterAvailability());
        courseDetails.put(COURSE_PREREQUISITES, course.getCoursePreRequisites());
        courseDetails.put(COURSE_COREQUISITES, course.getCourseCoRequisites());
        courseDetails.put(COURSE_TYPE, course.getCourseType());
    }
}