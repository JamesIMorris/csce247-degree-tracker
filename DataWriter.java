import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter extends DataConstants {
    private static DataWriter dataWriter;
    private CourseList courseList;
    private MajorList majorList;
    private UserList userList;

    public DataWriter() {
        courseList = CourseList.getInstance();
        majorList = MajorList.getInstance();
        userList = UserList.getInstance();
    }

    public static DataWriter getInstance() {
        if (dataWriter == null)
            dataWriter = new DataWriter();
        return dataWriter;
    }

    public boolean saveData() {
        if (saveReferenceData() && saveUserData())
            return true;
        return false;
    }



    // REFERENCE DATA // (Not important to save unless an Admin UI is implemented)

    public boolean saveReferenceData() {
        if (saveCourses() && saveRequiremnets() && saveMajors())
            return true;
        return false;
    }

    private boolean saveCourses() {
        return true;
    }

    private boolean saveRequiremnets() {
        return true;
    }

    private boolean saveMajors() {
        return true;
    }



    // USER DATA //

    // SAVE USER DATA //

    public boolean saveUserData(){
        if (saveUsers() && saveStudents() && saveAdvisors() && saveAdmin())
            return true;
        return false;
    }

    private boolean saveUsers() {
        ArrayList<User> users = userList.getUsers();
        JSONArray usersJSON = new JSONArray();

        for (int i = 0; i < users.size(); i++)
            usersJSON.add(getUserJSON(users.get(i)));

        try {
            FileWriter file = new FileWriter(USERS_FILE_NAME);
            file.write(usersJSON.toJSONString());
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean saveStudents() {
        ArrayList<User> students = userList.searchUserByType(UserType.STUDENT);
        JSONArray studentsJSON = new JSONArray();

        for (User student : students)
            studentsJSON.add(getStudentJSON((Student)student));

        try {
            FileWriter file = new FileWriter(STUDENTS_FILE_NAME);
            file.write(studentsJSON.toJSONString());
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean saveAdvisors() {
        ArrayList<User> advisors = userList.searchUserByType(UserType.ADVISOR);
        JSONArray advisorsJSON = new JSONArray();
        
        for (User advisor : advisors)
            advisorsJSON.add(getAdvisorJSON((Advisor)advisor));

        try {
            FileWriter file = new FileWriter(ADVISORS_FILE_NAME);
            file.write(advisorsJSON.toJSONString());
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean saveAdmin() {
        ArrayList<User> admins = userList.searchUserByType(UserType.ADMIN);
        JSONArray adminsJSON = new JSONArray();

        for (User admin : admins) 
            adminsJSON.add(getAdminJSON((Admin)admin));

        try {
            FileWriter file = new FileWriter(ADMIN_FILE_NAME);
            file.write(adminsJSON.toJSONString());
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // GET USER JSONS //

    public JSONObject getUserJSON(User user) {
        JSONObject userJSON = new JSONObject();
        userJSON.put(TYPE, user.getUserType().toString());
        userJSON.put(USERNAME, user.getUsername());
        userJSON.put(PASSWORD, user.getPassword());
        userJSON.put(FIRST_NAME, user.getFirstName());
        userJSON.put(LAST_NAME, user.getLastName());
        userJSON.put(EMAIL, user.getEmail());
        return userJSON;
    }

    

    public JSONObject getStudentJSON(Student student) {
        JSONObject studentDetails = new JSONObject();
        studentDetails.put("username", student.getUsername());
        studentDetails.put("uscID", student.getUscID());
        studentDetails.put("applicationArea", student.getApplicationArea());
        studentDetails.put("major", student.getMajor());
        studentDetails.put("year", student.getYear());

        JSONArray jsonCredits = new JSONArray();
        for (Credit credit : student.getCredits()) {
            JSONObject jsonCredit = new JSONObject();
            jsonCredit.put(COURSE, credit.getCourse());
            jsonCredit.put(SEMESTER_TAKEN, credit.getSemesterTaken().getAbbreviation());
            jsonCredit.put("grade", credit.getGrade());
            jsonCredit.put("type", credit.getType());
            jsonCredit.put("requirementsAssignedTo", credit.getRequirementsAssignedTo());

            JSONArray possibleRequirements = new JSONArray();
            for (PossibleRequirement possibleRequirement : credit.getPossibleRequirements()) {
                JSONObject requirementObject = new JSONObject();

                requirementObject.put("requirement", possibleRequirement.getRequirement().getName());
                requirementObject.put("available", possibleRequirement.getPossible());
                possibleRequirements.add(requirementObject);
            }
            jsonCredit.put("possibleRequirement", possibleRequirements);

            jsonCredit.put("note", credit.getNote());
            jsonCredits.add(jsonCredits);

        }
        studentDetails.put("credits", jsonCredits);

        JSONArray jsonRequirements = new JSONArray();
        for (Requirement requirement : student.getMajor().getRequirements()) {
            JSONObject requirementObj = new JSONObject();
            requirementObj.put("requirement", requirement.getID());
            JSONArray creditsArray = new JSONArray();
            for (Credit credit : student.getCredits(requirement)) {
                creditsArray.add(credit.getID());
            }
            requirementObj.put("credits", creditsArray);
            jsonRequirements.add(requirementObj);
        }
        studentDetails.put("requirements", jsonRequirements);
        JSONArray notesArray = new JSONArray();
        for (String note : student.getNotes()) {
            notesArray.add(note);
        }
        studentDetails.put("notes", notesArray);
        return studentDetails;
    }
    /*
     * studentDetails.put(STUDENT_USER_NAME, student.getCourseID());
     * studentDetails.put(STUDENT_AVAILABLE, student.getCourseID());
     * studentDetails.put(STUDENT_MAJOR, student.getMajor());
     * 
     * studentDetails.put(STUDENT_REQUIREMENT, student.getRequirementCreditHours());
     * studentDetails.put(STUDENT_REQUIREMENTS_LIST, student.getRequirements());
     * 
     * JSONArray studentArray = new JSONArray();
     * studentArray.add(STUDENT_COURSE, student.getCourseID());
     * studentArray.add(STUDENT_SEMESTER_TAKEN, student.getCourseName());
     * studentArray.add(STUDNET_YEAR_TAKEN, student.getCreditHours());
     * studentArray.add(STUDENT_SEASON, student.getCourseDescription());
     * studentArray.add(STUDENT_GRADE, student.getSemesterAvailability());
     * studentArray.add(STUDENT_COURSE_TYPE, student.getCourseType());
     * studentArray.add(STUDENT_REQUIREMENT, student.getRequirementCreditHours());
     * studentArray.add(STUDENT_REQUIREMENTS_LIST, student.getRequiements());
     * studentArray.add(STUDENT_AVAILABLE, student.getSemesterAvailability());
     * 
     * 
     * studentArray.put(STUDENT_CREDITS, student.getCredits());
     * 
     * studentDetails.put(STUDENT_NOTE, student.getNotes());
     * 
     * return studentDetails;
     */
    // }

    // Admin
    

    public JSONObject getAdminJSON(Admin admin) {
        JSONObject adminDetails = new JSONObject();
        adminDetails.put("username", admin.getUsername());
        return adminDetails;
    }



    public JSONObject getAdvisorJSON(Advisor advisor) {
        JSONObject advisorDetails = new JSONObject();
        advisorDetails.put("username", advisor.getUsername());

        JSONArray adviseesArray = new JSONArray();
        for (Student advisee : advisor.getAdvisees()) {
            adviseesArray.add(advisee);
        }
        advisorDetails.put(ADVISOR_ADVISEES, adviseesArray);

        return advisorDetails;
    }
}