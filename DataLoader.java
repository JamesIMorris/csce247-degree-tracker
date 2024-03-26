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

                JSONArray semesterAvailabilityJSON = (JSONArray) courseJSON.get(SEMESTER_AVAILABILITY);

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
                for (int i = 0; i < courseIDsJSON.size(); i++) 
                    courseIDs.add((String)courseIDs.get(i));

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
        JSONArray userData = loadUserData();
        if(!loadStudents(userData))
            return false;
        if(!loadAdvisors(userData))
            return false;
        // TODO
        // if(!loadAdmin(userData))
        //     return false;
        return true;
        
    }

    private JSONArray loadUserData() {
        JSONArray usersArray = null;
        try {
            FileReader reader = new FileReader(USER_FILE_NAME);
            JSONParser parser = new JSONParser();
            usersArray = (JSONArray)parser.parse(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usersArray;
    }

    private boolean loadStudents(JSONArray userData) {
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
                for(int j=0; i<userData.size(); j++){
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
                for (int j=0; i<creditsArray.size(); j++) {
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

                Student student = new Student(username, uscID, major, applicationArea,
                        credits, requirements, notes);

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
                student.setRequirements();

                userList.addUser(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private ArrayList<Student> loadStudent() {
        ArrayList<Student> students = new ArrayList<>();
        try {
            FileReader reader = new FileReader(STUDENT_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray studentsJSON = (JSONArray) parser.parse(reader);

            for (Object student : studentsJSON) {
                JSONObject studentJSON = (JSONObject) student;
                String username = (String) studentJSON.get(STUDENT_USER_NAME);
                String password = (String) studentJSON.get(STUDENT_PASSWORD);
                String firstName = (String) studentJSON.get(STUDENT_FIRSTNAME);
                String lastName = (String) studentJSON.get(STUDENT_LASTNAME);
                String email = (String) studentJSON.get(STUDENT_EMAIL);

                Major major = parseMajor((JSONObject) studentJSON.get(STUDENT_MAJOR));
                ArrayList<Credit> credits = parseCredits((JSONArray) studentJSON.get(STUDENT_CREDITS));
                HashMap<Requirement, ArrayList<Credit>> requirements = parseRequirements(
                        (JSONArray) studentJSON.get(STUDENT_REQUIREMENT));
                ArrayList<String> notes = parseNotes((JSONArray) studentJSON.get(STUDENT_NOTES));

                Student newStudent = new Student(userName, password, firstName, lastName,
                        email, major, credits, requirements, notes);
                students.add(newStudent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    private ArrayList<Advisor> loadAdvisor(String userName, String password,
            String firstName, String lastName, String email) {
        // Get vars from admin.json
        ArrayList<Student> advisees;
        users.add(new Advisor(userName, password, firstName, lastName, email,
                advisees));
        return false;
    }

    private ArrayList<Admin> loadAdmins() {
        ArrayList<Admin> admins = new ArrayList<>();
        try {
            FileReader reader = new FileReader(ADMIN_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray adminsArray = (JSONArray) parser.parse(reader);

            for (Object obj : adminsArray) {
                JSONObject adminObj = (JSONObject) obj;
                String username = (String) adminObj.get(ADMIN_USER_NAME);

                Admin admin = new Admin(username, null, null, null, null);
                admins.add(admin);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admins;
    }

    private boolean loadAdvisors() {
        ArrayList<Advisor> advisors = new ArrayList<>();
        try {
            FileReader reader = new FileReader(ADVISOR_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray advisorsArray = (JSONArray) parser.parse(reader);

            for (Object obj : advisorsArray) {
                JSONObject advisorObj = (JSONObject) obj;
                String username = (String) advisorObj.get("username");
                JSONArray adviseesArray = (JSONArray) advisorObj.get(ADVISOR_ADVISEES);

                ArrayList<Student> advisees = new ArrayList<>();
                for (Object adviseeObj : adviseesArray) {
                    String adviseeUsername = (String) adviseeObj;

                    User user = UserList.getInstance().findUser(adviseeUsername);
                    if (user != null && user instanceof Student) {
                        advisees.add((Student) user);
                    } else {
                        System.out.println("Student with username " + adviseeUsername + "not found");
                    }

                }
                Advisor advisor = new Advisor(username, "password", "test", "test",
                        "email@test.com");
                advisor.setAdvisees(advisees);
                advisors.add(advisor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return advisors;
    }

    public static void main(String[] args) {
        // DataLoader dataLoader = DataLoader.getInstance();
        ArrayList<Course> courses = DataLoader.loadCourses();
        // ArrayList<Major> majors = DataLoader.loadMajors();

        for (Course course : courses) {
            System.out.println("\n" + course.getCourseID());
            System.out.println(course);
            System.out.println("Course Description: " + course.getCourseDescription());
            System.out.println("Credit Hours: " + course.getCreditHours());
            System.out.println("Semester Availability: " + course.getSemesterAvailability());
            // System.out.print("Pre-Requisites: ");

            ArrayList<Course> preRequisites = course.getPreRequisites();
            for (Course preReq : preRequisites) {
                System.out.print(preReq.getCourseID() + " ");
            }

            ArrayList<Course> coRequisites = course.getCoRequisites();
            for (Course preReq : preRequisites) {
                System.out.print(preReq.getCourseID() + " ");
            }
            System.out.println();
            // System.out.println("Co-Requisites: " + course.getCoRequisites());
            System.out.println("Type: " + course.getType());
        }

        /*
         * for(Major major : majors) {
         * System.out.println("\n" + major.getName());
         * System.out.println("Major ID: " + major.getId());
         * System.out.println("Major School: " + major.getSchool());
         * System.out.println("Major Department: " + major.getDepartment());
         * System.out.println("Major " + major.getRequirements());
         * }
         */

        // for(Student student : students){

        // }
    }
}
