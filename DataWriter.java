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
        CourseList courseList = CourseList.getInstance();
        ArrayList<Course> courses = CourseList.getCourse();
        JSONArray jsonCourses = new JSONArray();

        for (Course course : courses) {
            JSONObject courseJSON = getCourseJSON(course);
            jsonCourses.add(courseJSON);
        }

        try (FileWriter file = new FileWriter(COURSE_FILE_NAME)) {

            file.write(jsonCourses.toJSONString());
            file.flush();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static JSONObject getCourseJSON(Course course) {
        JSONObject courseDetails = new JSONObject();
        courseDetails.put(COURSE_ID, course.getCourseID().toString());
        courseDetails.put(COURSE_NAME, course.getCourseName());
        courseDetails.put(COURSE_DESCRIPTION, course.getCourseDescription());
        courseDetails.put(CREDIT_HOURS, course.getCreditHours());

        JSONArray semesterAvailability = new JSONArray();
        for(Season season : course.getSemesterAvailability()) {
            semesterAvailability.add(season.toString());
        }
        courseDetails.put("semesterAvailability", semesterAvailability);

        JSONArray preRequisites = new JSONArray();
        for(Course preReq : course.getPreRequisites()) {
            preRequisites.add(preReq.getCoursID());
        }
        courseDetails.put("preRequisites", preRequisites);

        JSONArray coRequisites = new JSONArray();
        for(Course coReq : course.getCoRequisites()) {
            coRequisites.add(coReq.getCourseID());
        }
        return courseJSON;
    }
        /* 
        courseJSON.put("coRequisites". coRequisites);
        courseDetails.put(SEMESTER_AVAILABILITY, jsonArrayFromArray(course.getSemesterAvailability()));
        courseDetails.put(COURSE_PRE_REQUISISTES, jsonArrayFromArray(course.getPreRequisites()));
        courseDetails.put(COURSE_CO_REQUISITES, jsonArrayFromArray(course.getCoRequisites()));
        courseDetails.put(COURSE_TYPE, course.getCourseType());
        return courseDetails;
    }

    private static JSONArray jsonArrayFromArray(ArrayList<String> list) {
        JSONArray jsonArray = new JSONArray();
        for(String item : list) {
            jsonArray.add(item);
        }
        return jsonArray;
    }
*/
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
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
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
        JSONArray jsonStudents = new JSONArray();

        for (int i = 0; i < userList.size(); i++) {
            jsonStudents.add(getStudentJSON(userList.get(i)));
        }

        try (FileWriter file = new FileWriter(STUDENT_FILE_NAME)) {

            file.write(jsonUsers.toJSONString());
            file.flush();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static JSONObject getStudentJSON(Student student) {
        JSONObject studentDetails = new JSONObject();
        studentDetails.put("username", student.getUsername());
        studentDetails.put("major", student.getMajor());

        JSONArray jsonCredits = new JSON Array();
        for (Credit credit : student.getCredits()){
            JSONObject jsonCredit = new JSONObject();
            jsonCredit.put(STUDENT_COURSE, credit.getCourse());
            jsonCredit.put(STUDENT_SEMESTER_TAKEN, credit.getSemesterTaken());
            jsonCredit.put("type", credit.getType());
            jsonCredit.put("requirementsAssignedTo", credit.getRequiementsAssignedTo());
        }
            JSONArray possibleRequirements = new JSONArray();
            for(Requirement requirement : credit.getPossibleRequirements()) {
                JSONOBject requirementObject = new JSONOBject();
                requirementObject.put("requirement", requirement.getRequirements());
                requirementObject.put(STUDENT_AVAILABLE, requirement.isAvailable());
                possibleRequirements.add(requirementObject);
            }
            jsonCredit.put("possibleRequirement", possibleRequirements);

            jsonCredit.put("note", credit.getNotes());
            jsonCredits.add(jsonCredits);
        }
        /* 
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
        */
    }

    // Majors
    public boolean saveMajors() {
        MajorList majorList = MajorList.getInstance();
        ArrayList<Major> majorList = majorList.getMajors();
        JSONArray jsonMajors = new JSONArray();

        for (Major major : majors) {
            jsonMajors.add(getMajorJSON(major));
        }

        try (FileWriter file = new FileWriter(MAJOR_FILE_NAME)) {

            file.write(jsonMajors.toJSONString());
            file.flush();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
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

    //Admin
    public boolean saveAdmin() {
        Admin adminList = Admin.getInstance();
        JSONArray jsonAdmin = new JSONArray();

        for(Admin admin : Admin.getAdmin()) {
            JSONOBject adminDetails = getAdminJSON(admin);
            jsonAdmin.add(adminDetails);
        }

    try (FileWrtier file = new FileWriter(ADMIN_FILE_NAME)) {
        file.write(jsonAdmins.toJSONString());
        return true;
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }

    public static JSONObject getAdminJSON(Admin admin) {
        JSONObject adminDetails = new JSONObject();
        adminDetails.put("username", admin.getUsername());
        return adminDetails;
    }
}

    //Advisors
    public boolean saveAdvisors(){
        JSONArray jsonAdvisors = new JSONArray();

        for(Advisor advisor : Advisor.getAdvisors()) {
            JSONObject advisorDetails = getAdvisorJSON(advisor);
            jsonAdvisors.add(advisorDetails);
        }

        try (FileWriter file = new FileWriter(ADVISOR_FILE_NAME)) {
            file.write(jsonAdvisors.toJSONString());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static JSONObject getAdvisorJSON(Advisor advisor) {
        JSONObject advisorDetails = new JSONObject();
        advisorDetails.put("username", advisor.getUsername());

        JSONArray adviseesArray = new JSONArray();
        for (String advisee : advisor.getAdvisees()) {
            adviseesArray.add(advisee);
        }
        advisorDetails.put(ADVISOR_ADVISEES, adviseesArray);

        return advisorDetails;
    }

    //Requirements
    public boolean saveRequiremnets() {
        Requirement requirementList = Requirement.getInstance();
        JSONArray jsonRequirements = new JSONArray();

        for (Requirement requirement : requirementList.getRequirements()) {
            jsonRequirements.add(getRequirementJSON(requirement));
        }

        try (FileWrtier file = new FileWrtier(REQUIREMENTS_FILE_NAME)) {
            file.write(jsonRequirements.toJSONString());
            file.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static JSONObject getRequirementJSON(Requirement requirement) {
        JSONObject requirementDetails = new JSONObject();
        requirementDetails.put(REQUIREMENTS_UUID, requirement.getCourseIDs());
        requirementDetails.put(REQUIRMENT_NAME, requirement.getName());
        requirementDetails.put(REQUIREMENT_CATEGORY, requirement.getCategory());

        JSONArray courseIDsArray = new JSONArray();
        for(String courseID : requirement.getCourseIDs()) {
            courseIDsArray.add(courseID);
        }
        requirementDetails.put(REQUIRMENT_COURSE_ID, courseIDsArray);

        requirementDetails.put(REQUIREMENT_CREDITS_REQUIRED, requirement.getCreditHoursRequired);

        return requirementDetails;
    }
}