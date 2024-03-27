import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter extends DataConstants {
    private static DataWriter dataWriter;

    public DataWriter() {

    }

    public static DataWriter getInstance() {
        if (dataWriter == null)
            dataWriter = new DataWriter();
        return dataWriter;
    }

    public boolean saveData() {
        if (saveReferenceData() && saveUsers())
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


    
    // SAVE USERS //

    public boolean saveUsers() {
        UserList user = UserList.getInstance();
        ArrayList<User> userList = user.getUsers();
        JSONArray jsonUsers = new JSONArray();

        for (int i = 0; i < userList.size(); i++) {
            jsonUsers.add(getUserJSON(userList.get(i)));
        }

        try (FileWriter file = new FileWriter(USERS_FILE_NAME)) {

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
        userDetails.put(USERNAME, user.getUsername());
        userDetails.put(FIRST_NAME, user.getFirstName());
        userDetails.put(LAST_NAME, user.getLastName());
        userDetails.put(EMAIL, user.getEmail());
        userDetails.put(PASSWORD, user.getPassword());
        userDetails.put(TYPE, user.getUserType());

        return userDetails;
    }

    // Students
    public boolean saveStudents() {
        UserList User = UserList.getInstance();
        ArrayList<User> students = User.searchUserByType(UserType.STUDENT);
        JSONArray jsonStudents = new JSONArray();

        for (User user : students) {
            if (user instanceof Student) {
                Student student = (Student) user;
                JSONObject studentJSON = getStudentJSON(student);
                jsonStudents.add(studentJSON);
            }
        }

        try (FileWriter file = new FileWriter(STUDENTS_FILE_NAME)) {

            file.write(jsonStudents.toJSONString());
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
    public boolean saveAdmin() {
        UserList userList = UserList.getInstance();
        // Admin adminList = Admin.getInstance();
        JSONArray jsonAdmin = new JSONArray();
        ArrayList<User> admins = userList.searchUserByType(UserType.ADMIN);

        for (User admin : admins) {
            JSONObject adminDetails = new JSONObject();
            adminDetails.put("username", admin.getUsername());
            jsonAdmin.add(adminDetails);
        }

        try (FileWriter file = new FileWriter(ADMIN_FILE_NAME)) {
            file.write(jsonAdmin.toJSONString());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static JSONObject getAdminJSON(Admin admin) {
        JSONObject adminDetails = new JSONObject();
        adminDetails.put("username", admin.getUsername());
        return adminDetails;
    }

    // Advisors
    public boolean saveAdvisors() {
        UserList userList = UserList.getInstance();
        JSONArray jsonAdvisors = new JSONArray();
        ArrayList<User> advisors = userList.searchUserByType(UserType.ADVISOR);

        for (User user : advisors) {
            Advisor advisor = (Advisor) user;
            JSONObject advisorDetails = getAdvisorJSON(advisor);
            jsonAdvisors.add(advisorDetails);
        }

        try (FileWriter file = new FileWriter(ADVISORS_FILE_NAME)) {
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
        for (Student advisee : advisor.getAdvisees()) {
            adviseesArray.add(advisee);
        }
        advisorDetails.put(ADVISOR_ADVISEES, adviseesArray);

        return advisorDetails;
    }

    public static void main(String[] args) {
        DataWriter writer = new DataWriter();

        boolean coursesSaved = writer.saveCourses();
        if (coursesSaved) {
            System.out.println("Courses saved successfully.");
        } else {
            System.out.println("Failed to save courses.");
        }

        boolean usersSaved = writer.saveUsers();
        if (usersSaved) {
            System.out.println("Users saved successfully.");
        } else {
            System.out.println("Failed to save users.");
        }

        boolean studentsSaved = writer.saveStudents();
        if (studentsSaved) {
            System.out.println("Students saved successfully.");
        } else {
            System.out.println("Failed to save students.");
        }

        boolean majorsSaved = writer.saveMajors();
        if (majorsSaved) {
            System.out.println("Majors saved successfully.");
        } else {
            System.out.println("Failed to save majors.");
        }

        boolean adminSaved = writer.saveAdmin();
        if (adminSaved) {
            System.out.println("Admin saved successfully.");
        } else {
            System.out.println("Failed to save admin.");
        }

        boolean advisorsSaved = writer.saveAdvisors();
        if (advisorsSaved) {
            System.out.println("Advisors saved successfully.");
        } else {
            System.out.println("Failed to save advisors.");
        }

        boolean requirementsSaved = writer.saveRequiremnets();
        if (requirementsSaved) {
            System.out.println("Requirements saved successfully.");
        } else {
            System.out.println("Failed to save requirements.");
        }

    }
}