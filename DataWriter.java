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
        ArrayList<Course> courses = courseList.getCourses();
        JSONArray jsonCourses = new JSONArray();

        for (Course course : courses) {
            JSONObject courseJSON = getCourseJSON(course);
            jsonCourses.add(courseJSON);
        }

        try (FileWriter file = new FileWriter(COURSES_FILE_NAME)) {

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
        courseDetails.put(ID, course.getCourseID().toString());
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
            preRequisites.add(preReq.getCourseID());
        }
        courseDetails.put("preRequisites", preRequisites);

        JSONArray coRequisites = new JSONArray();
        for(Course coReq : course.getCoRequisites()) {
            coRequisites.add(coReq.getCourseID());
        }
        return courseDetails;
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

        for(User user : students) {
            if(user instanceof Student) {
                Student student = (Student)user;
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
        for (Credit credit : student.getCredits()){
            JSONObject jsonCredit = new JSONObject();
            jsonCredit.put(COURSE, credit.getCourse());
            jsonCredit.put(SEMESTER_TAKEN, credit.getSemesterTaken().getAbbreviation());
            jsonCredit.put("grade", credit.getGrade());
            jsonCredit.put("type", credit.getType());
            jsonCredit.put("requirementsAssignedTo", credit.getRequirementsAssignedTo());
        
            JSONArray possibleRequirements = new JSONArray();
            for(PossibleRequirement possibleRequirement : credit.getPossibleRequirements()) {
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
        for(Requirement requirement : student.getMajor().getRequirements()) {
            JSONObject requirementObj = new JSONObject();
            requirementObj.put("requirement", requirement.getID());
            JSONArray creditsArray = new JSONArray();
            for(Credit credit : student.getCredits(requirement)){
                creditsArray.add(credit.getID());
            }
            requirementObj.put("credits", creditsArray);
            jsonRequirements.add(requirementObj);
        }
        studentDetails.put("requirements", jsonRequirements);
        JSONArray notesArray = new JSONArray();
        for(String note : student.getNotes()){
            notesArray.add(note);
        }
        studentDetails.put("notes", notesArray);
        return studentDetails;
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
   // }

    // Majors
    public boolean saveMajors() {
        MajorList majorList = MajorList.getInstance();
        ArrayList<Major> majors = majorList.getMajors();
        JSONArray jsonMajors = new JSONArray();

        for (Major major : majors) {
            jsonMajors.add(getMajorJSON(major));
        }

        try (FileWriter file = new FileWriter(MAJORS_FILE_NAME)) {

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
        majorDetails.put(ID, major.getId().toString());
        majorDetails.put(MAJOR_NAME, major.getName());
        majorDetails.put(SCHOOL, major.getSchool());
        majorDetails.put(DEPARTMENT, major.getDepartment());
        majorDetails.put(MAJOR_REQUIREMENTS, major.getRequirements());

        return majorDetails;
    }

    //Admin
    public boolean saveAdmin() {
        UserList userList = UserList.getInstance();
        //Admin adminList = Admin.getInstance();
        JSONArray jsonAdmin = new JSONArray();
        ArrayList<User> admins = userList.searchUserByType(UserType.ADMIN);

        for(User admin : admins) {
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


    //Advisors
    public boolean saveAdvisors(){
        UserList userList = UserList.getInstance();
        JSONArray jsonAdvisors = new JSONArray();
        ArrayList<User> advisors = userList.searchUserByType(UserType.ADVISOR);

        for(User user : advisors) {
            Advisor advisor = (Advisor)user;
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

    //Requirements
    public boolean saveRequiremnets() {
        MajorList majorList = MajorList.getInstance();
        JSONArray jsonRequirements = new JSONArray();

        for (Major major : majorList.getMajors()) {
            for(Requirement requirement : major.getRequirements()){
                jsonRequirements.add(getRequirementJSON(requirement));
            }
        }

        try (FileWriter file = new FileWriter(REQUIREMENTS_FILE_NAME)) {
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
        requirementDetails.put(ID, requirement.getCourseIDs());
        requirementDetails.put(REQUIRMENT_NAME, requirement.getName());
        requirementDetails.put(REQUIREMENT_CATEGORY, requirement.getCategory());

        JSONArray courseIDsArray = new JSONArray();
        for(String courseID : requirement.getCourseIDs()) {
            courseIDsArray.add(courseID);
        }
        requirementDetails.put(REQUIRMENT_COURSES, courseIDsArray);

        requirementDetails.put(REQUIREMENT_CREDIT_HOURS_REQUIRED, requirement.getCreditHoursRequired());

        return requirementDetails;
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