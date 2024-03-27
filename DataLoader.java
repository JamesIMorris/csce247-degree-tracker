import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

public class DataLoader extends DataConstants {

    // VARIABLES //

    private static DataLoader dataLoader;
    private CourseList courseList;
    private MajorList majorList;
    private UserList userList;
    private JSONArray userData;

    // INITIALIZATION //

    private DataLoader() {
        courseList = CourseList.getInstance();
        majorList = MajorList.getInstance();
        userList = UserList.getInstance();
        loadData();
    }

    public static DataLoader getInstance() {
        if (dataLoader == null)
            dataLoader = new DataLoader();
        return dataLoader;
    }

    public void loadData() {
        loadCourses();
        loadMajors();
        loadUsers();
    }




    // LOADING //



    // LOAD COURSES //

    private boolean loadCourses() {
        try {
            FileReader reader = new FileReader(COURSE_FILE_NAME);
            JSONParser parsec = new JSONParser();
            JSONArray coursesJSON = (JSONArray) parsec.parse(reader);

            for (Object courseObject : coursesJSON) {
                
                JSONObject courseJSON = (JSONObject) courseObject;
                String id = (String) courseJSON.get(COURSE_ID);
                String courseName = (String) courseJSON.get(COURSE_NAME);
                String courseDescription = (String) courseJSON.get(COURSE_DESCRIPTION);
                int creditHours = ((Long) courseJSON.get(CREDIT_HOURS)).intValue();
                ArrayList<Season> semesterAvailability = new ArrayList<Season>();
                CourseType type = CourseType.fromString((String) courseJSON.get(COURSE_TYPE));

                JSONArray semesterAvailabilityJSON = (JSONArray)courseJSON.get(SEMESTER_AVAILABILITY);

                for (int i = 0; i < semesterAvailabilityJSON.size(); i++) {
                    semesterAvailability.add(Season.fromString((String) semesterAvailabilityJSON.get(i)));
                }

                courseList.addCourse(
                        new Course(id, courseName, courseDescription, creditHours, semesterAvailability, type));
            }

            for (Object courseObject : coursesJSON) {
                JSONObject courseJSON = (JSONObject) courseObject;
                String id = (String) courseJSON.get(COURSE_ID);
                Course course = courseList.getCourseFromID(id);

                JSONArray preReqsJSON = (JSONArray) courseJSON.get(COURSE_CO_REQUISITES);
                JSONArray coReqsJSON = (JSONArray) courseJSON.get(COURSE_PRE_REQUISISTES);

                for (int i = 0; i < preReqsJSON.size(); i++) {
                    Course preReq = courseList.getCourseFromID(id);
                    course.addPreRequirisite(preReq);
                }
                for (int i = 0; i < coReqsJSON.size(); i++) {
                    Course coReq = courseList.getCourseFromID(id);
                    course.addCoRequirisite(coReq);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }



    // LOAD MAJORS //

    private boolean loadMajors(){
        loadRequirements();
        try{
            FileReader reader = new FileReader(MAJOR_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray majorsJSON = (JSONArray) parser.parse(reader);
        
            for(Object major : majorsJSON){
                JSONObject majorJSON = (JSONObject)major;
                UUID id = UUID.fromString((String)majorJSON.get(MAJOR_ID));
                String name = (String)majorJSON.get(MAJOR_NAME);
                String school = (String)majorJSON.get(MAJOR_SCHOOL);
                String department = (String)majorJSON.get(MAJOR_DEPARTMENT);
                ArrayList<Requirement> requirements = new ArrayList<Requirement>();

                JSONArray requirementsJSON = (JSONArray)majorJSON.get(MAJOR_REQUIREMENTS);
                for (int i = 0; i < requirementsJSON.size(); i++) {
                    UUID requirementID = UUID.fromString((String)requirementsJSON.get(i));
                    Requirement requirement = majorList.getRequirementFromID(requirementID);
                    requirements.add(requirement);
                }
                
                majorList.addMajor(new Major(id, name, school, department, requirements));
            }
        
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean loadRequirements() {
        try {
            FileReader reader = new FileReader(REQUIREMENTS_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray requirementsArray = (JSONArray) parser.parse(reader);

            for (Object req : requirementsArray) {
                JSONObject reqJSON = (JSONObject) req;
                UUID id = UUID.fromString((String) reqJSON.get(REQUIREMENTS_UUID));
                String name = (String) reqJSON.get(REQUIRMENT_NAME);
                String categoryName = (String)reqJSON.get(REQUIREMENT_CATEGORY);
                Category category = Category.fromAbbreviation(categoryName.toUpperCase());
                int creditHoursRequired = ((Long)reqJSON.get(REQUIREMENT_CREDITS_REQUIRED)).intValue();
                ArrayList<String> courseIDs = new ArrayList<String>();

                JSONArray courseIDsJSON = (JSONArray) reqJSON.get(REQUIRMENT_COURSE_ID);
                for (int i = 0; i<courseIDsJSON.size(); i++) 
                    courseIDs.add((String)courseIDsJSON.get(i));

                majorList.addRequirement(new Requirement(id, name, category, courseIDs,
                        creditHoursRequired));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }



    // LOAD USERS //
    
    private boolean loadUsers() {
        if(loadUserData() && loadStudents() && loadAdvisors() && loadAdmin())
            return true;
        return false;
        
    }

    private boolean loadUserData() {
        try {
            FileReader reader = new FileReader(USER_FILE_NAME);
            JSONParser parser = new JSONParser();
            userData = (JSONArray)parser.parse(reader);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean loadStudents() {
        try {
            FileReader reader = new FileReader(STUDENT_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray studentsArray = (JSONArray) parser.parse(reader);

            for (int i=0; i<studentsArray.size(); i++) { 
                JSONObject studentJSON = (JSONObject)studentsArray.get(i);

                String username = (String) studentJSON.get(STUDENT_USERNAME);
                String password = null;
                String firstName = null;
                String lastName = null;
                String email = null;
                for(int j=0; j<userData.size(); j++){
                    JSONObject userJSON = (JSONObject)userData.get(j);
                    if(!userJSON.get(USER_NAME).equals(username))
                        continue;
                    password = (String)userJSON.get(PASSWORD);
                    firstName = (String)userJSON.get(FIRST_NAME);
                    lastName = (String)userJSON.get(LAST_NAME);
                    email = (String)userJSON.get(EMAIL);
                }

                String uscID = (String) studentJSON.get("uscID");
                Major major = majorList.getMajorFromName((String)studentJSON.get("major"));
                String applicationArea = (String) studentJSON.get("applicationArea");
                ArrayList<Credit> credits = new ArrayList<Credit>();
                HashMap<Requirement, ArrayList<Credit>> requirements = new HashMap<Requirement, ArrayList<Credit>>();
                ArrayList<String> notes = new ArrayList<String>();

                JSONArray creditsArray = (JSONArray) studentJSON.get(STUDENT_CREDITS);
                for (int j=0; j<creditsArray.size(); j++) {
                    JSONObject creditJSON = (JSONObject)creditsArray.get(j);
                    UUID id = UUID.fromString((String) creditJSON.get("id"));
                    Course course = courseList.getCourseFromID((String)creditJSON.get(STUDENT_COURSE));;
                    Semester semesterTaken = Semester.fromString((String)creditJSON.get("semesterTaken"));;
                    int grade = ((Long) creditJSON.get("grade")).intValue();
                    CreditType type = CreditType.fromString((String)creditJSON.get("type"));
                    int requirementsAssignedTo = ((Long) creditJSON.get("requirementsAssignedTo")).intValue();
                    String note = (String) creditJSON.get("note");
                    Credit credit = new Credit(id, course, semesterTaken, grade, type,
                            requirementsAssignedTo, note);
                    credits.add(credit);
                }

                JSONArray notesArray = (JSONArray) studentJSON.get(STUDENT_NOTES);
                for (int j=0; j<notesArray.size(); j++) {
                    String note = (String) notesArray.get(j);
                    notes.add(note);
                }

                Student student = new Student(username, password, firstName, lastName, email, uscID, major, applicationArea,
                        credits, notes);

                JSONArray requirementsArray = (JSONArray) studentJSON.get(STUDENT_REQUIREMENTS_LIST);
                for (int j=0; j<requirementsArray.size(); j++) {
                    JSONObject requirementHashJSON = (JSONObject)requirementsArray.get(j);
                    Requirement requirement = null;
                    ArrayList<Credit> creditsForRequirement = new ArrayList<>();

                    UUID requirementID = UUID.fromString((String)requirementHashJSON.get("requirement"));
                    requirement = majorList.getRequirementFromID(requirementID);

                    JSONArray creditsArrayForRequirement = (JSONArray)requirementHashJSON.get("credits");
                    for (int k=0; k<creditsArrayForRequirement.size(); k++) {
                        UUID creditID = UUID.fromString((String)creditsArrayForRequirement.get(k));
                        Credit credit = student.getCreditFromID(creditID);
                        creditsForRequirement.add(credit);
                    }
                    requirements.put(requirement, creditsForRequirement);
                }
                student.setRequirements(requirements);

                userList.addUser(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    } 

    private boolean loadAdvisors() {
        try {
            FileReader reader = new FileReader(ADVISOR_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray advisorsArray = (JSONArray) parser.parse(reader);

            for (int i=0; i<advisorsArray.size(); i++){
                JSONObject advisorJSON = (JSONObject)advisorsArray.get(i);
                
                String username = (String) advisorJSON.get(STUDENT_USERNAME);
                String password = null;
                String firstName = null;
                String lastName = null;
                String email = null;
                for(int j=0; j<userData.size(); j++){
                    JSONObject userJSON = (JSONObject)userData.get(j);
                    if(!userJSON.get(USER_NAME).equals(username))
                        continue;
                    password = (String)userJSON.get(PASSWORD);
                    firstName = (String)userJSON.get(FIRST_NAME);
                    lastName = (String)userJSON.get(LAST_NAME);
                    email = (String)userJSON.get(EMAIL);
                }
                ArrayList<Student> advisees = new ArrayList<Student>();

                JSONArray adviseesJSON = (JSONArray) advisorJSON.get(ADVISOR_ADVISEES);
                for (int j=0; j<adviseesJSON.size(); j++) {
                    String adviseeUsername = (String)adviseesJSON.get(j);
                    Student student = (Student)userList.findUser(adviseeUsername);
                    advisees.add(student);
                }
                userList.addUser(new Advisor(username, password, firstName, lastName, email, advisees));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean loadAdmin(){
        try {
            FileReader reader = new FileReader(ADMIN_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray adminArray = (JSONArray) parser.parse(reader);

            for (int i=0; i<adminArray.size(); i++){
                JSONObject adminJSON = (JSONObject)adminArray.get(i);
                
                String username = (String) adminJSON.get(STUDENT_USERNAME);
                String password = null;
                String firstName = null;
                String lastName = null;
                String email = null;
                for(int j=0; j<userData.size(); j++){
                    JSONObject userJSON = (JSONObject)userData.get(j);
                    if(!userJSON.get(USER_NAME).equals(username))
                        continue;
                    password = (String)userJSON.get(PASSWORD);
                    firstName = (String)userJSON.get(FIRST_NAME);
                    lastName = (String)userJSON.get(LAST_NAME);
                    email = (String)userJSON.get(EMAIL);
                }

                userList.addUser(new Admin(username, password, firstName, lastName, email));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public UserList getUserList() {
        return userList;
    }
    public MajorList getMajorList() {
        return majorList;
    }
    public CourseList getCourseList() {
        return courseList;
    }

    public static void main(String[] args) {
        DataLoader dataLoader = DataLoader.getInstance();
        CourseList courseList = dataLoader.getCourseList();
        MajorList majorList = dataLoader.getMajorList();
        UserList userList = dataLoader.getUserList();
        
    }
}
