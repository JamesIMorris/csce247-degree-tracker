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
            FileReader reader = new FileReader(COURSES_FILE_NAME);
            JSONParser parsec = new JSONParser();
            JSONArray coursesJSON = (JSONArray) parsec.parse(reader);

            for (Object courseObject : coursesJSON) {

                JSONObject courseJSON = (JSONObject) courseObject;
                String id = (String) courseJSON.get(ID);
                String courseName = (String) courseJSON.get(COURSE_NAME);
                String courseDescription = (String) courseJSON.get(COURSE_DESCRIPTION);
                int creditHours = ((Long) courseJSON.get(CREDIT_HOURS)).intValue();
                ArrayList<Season> semesterAvailability = new ArrayList<Season>();
                CourseType type = CourseType.fromString((String) courseJSON.get(TYPE));

                JSONArray semesterAvailabilityJSON = (JSONArray)courseJSON.get(SEMESTER_AVAILABILITY);

                for (int i = 0; i < semesterAvailabilityJSON.size(); i++) {
                    semesterAvailability.add(Season.fromString((String) semesterAvailabilityJSON.get(i)));
                }

                courseList.addCourse(
                        new Course(id, courseName, courseDescription, creditHours, semesterAvailability, type));
            }

            for (Object courseObject : coursesJSON) {
                JSONObject courseJSON = (JSONObject) courseObject;
                String id = (String) courseJSON.get(ID);
                Course course = courseList.getCourseFromID(id);

                JSONArray preReqsJSON = (JSONArray) courseJSON.get(CO_REQUISITES);
                JSONArray coReqsJSON = (JSONArray) courseJSON.get(PRE_REQUISISTES);

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
            FileReader reader = new FileReader(MAJORS_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray majorsJSON = (JSONArray) parser.parse(reader);
        
            for(Object major : majorsJSON){
                JSONObject majorJSON = (JSONObject)major;
                UUID id = UUID.fromString((String)majorJSON.get(ID));
                String name = (String)majorJSON.get(MAJOR_NAME);
                String school = (String)majorJSON.get(SCHOOL);
                String department = (String)majorJSON.get(DEPARTMENT);
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
                UUID id = UUID.fromString((String) reqJSON.get(ID));
                String name = (String) reqJSON.get(REQUIRMENT_NAME);
                String categoryName = (String)reqJSON.get(REQUIREMENT_CATEGORY);
                Category category = Category.fromAbbreviation(categoryName.toUpperCase());
                int creditHoursRequired = ((Long)reqJSON.get(REQUIREMENT_CREDIT_HOURS_REQUIRED)).intValue();
                ArrayList<String> courseIDs = new ArrayList<String>();

                JSONArray courseIDsJSON = (JSONArray) reqJSON.get(REQUIRMENT_COURSES);
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
            FileReader reader = new FileReader(USERS_FILE_NAME);
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
            FileReader reader = new FileReader(STUDENTS_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray studentsArray = (JSONArray) parser.parse(reader);

            for (int i=0; i<studentsArray.size(); i++) { 
                JSONObject studentJSON = (JSONObject)studentsArray.get(i);

                String username = (String) studentJSON.get(USERNAME);
                String password = null;
                String firstName = null;
                String lastName = null;
                String email = null;
                for(int j=0; j<userData.size(); j++){
                    JSONObject userJSON = (JSONObject)userData.get(j);
                    if(!userJSON.get(USERNAME).equals(username))
                        continue;
                    password = (String)userJSON.get(PASSWORD);
                    firstName = (String)userJSON.get(FIRST_NAME);
                    lastName = (String)userJSON.get(LAST_NAME);
                    email = (String)userJSON.get(EMAIL);
                }

                String uscID = (String) studentJSON.get(USC_ID);
                Major major = majorList.getMajorFromName((String)studentJSON.get(MAJOR));
                String applicationArea = (String) studentJSON.get(APPLICATION_AREA);
                ArrayList<Credit> credits = new ArrayList<Credit>();
                HashMap<Requirement, ArrayList<Credit>> requirements = new HashMap<Requirement, ArrayList<Credit>>();
                ArrayList<String> notes = new ArrayList<String>();

                JSONArray creditsArray = (JSONArray) studentJSON.get(STUDENT_CREDITS);
                for (int j=0; j<creditsArray.size(); j++) {
                    JSONObject creditJSON = (JSONObject)creditsArray.get(j);
                    UUID id = UUID.fromString((String) creditJSON.get(ID));
                    Course course = courseList.getCourseFromID((String)creditJSON.get(COURSE));;
                    Semester semesterTaken = Semester.fromString((String)creditJSON.get(SEMESTER_TAKEN));;
                    int grade = ((Long) creditJSON.get(GRADE)).intValue();
                    CreditType type = CreditType.fromString((String)creditJSON.get(TYPE));
                    int requirementsAssignedTo = ((Long) creditJSON.get(REQUIREMENTS_ASSIGNED_TO)).intValue();
                    String note = (String) creditJSON.get(CREDIT_NOTE);
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

                    UUID requirementID = UUID.fromString((String)requirementHashJSON.get(STUDENT_REQUIREMENT));
                    requirement = majorList.getRequirementFromID(requirementID);

                    JSONArray creditsArrayForRequirement = (JSONArray)requirementHashJSON.get(STUDENT_CREDITS);
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
            FileReader reader = new FileReader(ADVISORS_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray advisorsArray = (JSONArray) parser.parse(reader);

            for (int i=0; i<advisorsArray.size(); i++){
                JSONObject advisorJSON = (JSONObject)advisorsArray.get(i);
                
                String username = (String) advisorJSON.get(USERNAME);
                String password = null;
                String firstName = null;
                String lastName = null;
                String email = null;
                for(int j=0; j<userData.size(); j++){
                    JSONObject userJSON = (JSONObject)userData.get(j);
                    if(!userJSON.get(USERNAME).equals(username))
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
                
                String username = (String) adminJSON.get(USERNAME);
                String password = null;
                String firstName = null;
                String lastName = null;
                String email = null;
                for(int j=0; j<userData.size(); j++){
                    JSONObject userJSON = (JSONObject)userData.get(j);
                    if(!userJSON.get(USERNAME).equals(username))
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

    // TEST METHODS //

    public boolean loadCourses(String test){
        return loadCourses();
    }
    public boolean loadMajors(String test){
        return loadMajors();
    }
    public boolean loadRequirements(String test){
        return loadRequirements();
    }
    public boolean loadUsers(String test){
        return loadUsers();
    }
    public boolean loadUserData(String test){
        return loadUserData();
    }
    public boolean loadStudents(String test){
        return loadStudents();
    }
    public boolean loadAdvisors(String test){
        return loadAdvisors();
    }
    public boolean loadAdmin(String test){
        return loadAdmin();
    }
}
