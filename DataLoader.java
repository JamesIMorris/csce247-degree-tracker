import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

public class DataLoader extends DataConstants{
    private static DataLoader dataLoader;

    private DataLoader(){
        loadData();
    }

    public static DataLoader getInstance(){
        if(dataLoader == null)
            dataLoader = new DataLoader();
        return dataLoader;
    }

    public void loadData(){
        ArrayList<Course> courses = DataLoader.loadCourses();
        CourseList.getInstance().setCourses(courses);

        ArrayList<Major> majors = loadMajors();
        MajorList.getInstance().addMajors(majors);
        //loadCourses();
    }

     static ArrayList<Course> loadCourses(){
        ArrayList<Course> courses = new ArrayList<Course>();
        try{
            FileReader reader = new FileReader(COURSE_FILE_NAME);
            JSONParser parsec = new JSONParser();
            JSONArray coursesJSON = (JSONArray)parsec.parse(reader);

            System.out.println("1");
        
            for(Object course : coursesJSON){
                JSONObject courseJSON = (JSONObject)course;
                String id = (String)courseJSON.get(COURSE_ID);
                String courseName = (String)courseJSON.get(COURSE_NAME);
                String courseDescription = (String)courseJSON.get(COURSE_DESCRIPTION);
                int creditHours = ((Long)courseJSON.get(CREDIT_HOURS)).intValue();

                JSONArray semesterAvailabilityJSON = (JSONArray)courseJSON.get(SEMESTER_AVAILABILITY);
                //Change into a new string to avoid error of incompatible types  
                String[] semesterAvailableStrings = new String[semesterAvailabilityJSON.size()];
                //String[] semesterAvailableStrings = (String[])semesterAvailabilityJSON.toArray();
                ArrayList<Season> semesterAvailability = new ArrayList<Season>();
                for(int i = 0; i< semesterAvailabilityJSON.size(); i++) {
                    semesterAvailableStrings[i] = (String) semesterAvailabilityJSON.get(i);
                }
                for(String string : semesterAvailableStrings){
                    semesterAvailability.add(Season.fromString(string));
                    System.out.println(string);
                }
                CourseType type = CourseType.fromString((String)courseJSON.get(COURSE_TYPE));
        
                courses.add(new Course(id, courseName, courseDescription, creditHours, semesterAvailability, type));
                System.out.println("2");
            }
            for(int i=0; i<courses.size(); i++){
                JSONObject courseJSON = (JSONObject)coursesJSON.get(i);
                
                ArrayList<Course> preRequisites = toCourseArrayList((JSONArray)courseJSON.get(COURSE_PRE_REQUISISTES));
                System.out.println("3");
                ArrayList<Course> coRequisites = toCourseArrayList((JSONArray)courseJSON.get(COURSE_CO_REQUISITES));
                
                //ArrayList<Course> preRequisites = toCourseArrayList((JSONArray)courseJSON.get(COURSE_PRE_REQUISISTES));
                //ArrayList<Course> coRequisites = toCourseArrayList((JSONArray)courseJSON.get(COURSE_CO_REQUISITES));
                courses.get(i).setPreRequisites(preRequisites);
                courses.get(i).setCoRequisites(coRequisites);
                
            }
            

            return courses;
        } catch(Exception e) {
            e.printStackTrace();
            return courses;
        }
    }

    
    static ArrayList<Course> toCourseArrayList(JSONArray courseIDsJSON) {
    ArrayList<Course> returnList = new ArrayList<Course>();
    for (Object obj : courseIDsJSON) {
        if (obj instanceof String) {
            String courseId = (String) obj;
            Course course = CourseList.getInstance().getCourseID(courseId);
            if (course != null) {
                returnList.add(course);
            } else {
                System.out.println("Course with ID " + courseId + " not found.");
            }
        }
    }
    return returnList;
}


    /* 
    private static ArrayList<Course> toCourseArrayList(JSONArray courseIDsJSON){
        ArrayList<Course> returnList = new ArrayList<Course>();
        String[] courseIDs = (String[])courseIDsJSON.toArray();
        for(String string : courseIDs){
            returnList.add(CourseList.getInstance().getCourse(string));
        }
        return returnList;
    }
    */
  

        static ArrayList<Major> loadMajors(){
        ArrayList<Major> majors = new ArrayList<>();
        try{
            FileReader reader = new FileReader(MAJOR_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray majorsJSON = (JSONArray) parser.parse(reader);
            //JSONArray majorsJSON = (JSONArray)new JSONParser().parse(reader);

            for(Object major : majorsJSON){
                JSONObject majorJSON = (JSONObject)major;
                UUID id = UUID.fromString((String)majorJSON.get(MAJOR_ID));
                String name = (String)majorJSON.get(MAJOR_NAME);
                String school = (String)majorJSON.get(MAJOR_SCHOOL);
                String department = (String)majorJSON.get(MAJOR_DEPARTMENT);

                JSONArray requirementsJSON = (JSONArray) majorJSON.get(MAJOR_REQUIREMENTS);
                ArrayList<Requirement> requirements = new ArrayList<>();
                for(Object req : requirementsJSON){
                    String requirementName = (String) req;
                    Requirement requirement = new Requirement(requirementName, null, null, 0);
                    requirements.add(requirement);
                }
                //ArrayList<String> requirements = (ArrayList<String>)majorJSON.get(MAJOR_REQUIREMENTS);

                Major newMajor = new Major(id, name, school, department, requirements);
                majors.add(newMajor);

                //major.add(new Major(majorID, name, school, department, requirements));
            }
           
        } catch(Exception e) {
            e.printStackTrace();
        }
        return majors;
    }
    


     static ArrayList<User> loadUsers(){
        ArrayList<User> users = new ArrayList<>();
        try {
            FileReader reader = new FileReader(USER_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray usersArray = (JSONArray)parser.parse(reader);

            for(Object obj : usersArray) {
                JSONObject userObj = (JSONObject)obj;
                String userType = (String)userObj.get(USER_TYPE);
                String username = (String)userObj.get(USER_NAME);
                String password = (String)userObj.get(PASSWORD);
                String firstName = (String)userObj.get(FIRST_NAME);
                String lastName = (String)userObj.get(LAST_NAME);
                String email = (String)userObj.get(EMAIL);

                switch(userType) {
                    case "STUDENT":

                    break;
                    case "Advisor":

                    break;
                    case "ADMIN":

                    break;
                    default:
                    System.out.println("Invalid");
                    break;
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        //return false;
    }

    static ArrayList<Student> loadStudents() {
        ArrayList<Student> students = new ArrayList<>();
        try {
            FileReader reader = new FileReader(STUDENT_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray studentsArray = (JSONArray)parser.parse(reader);

        for(Object obj : studentsArray) {
            JSONObject studentObj = (JSONObject) obj;
            String username = (String)studentObj.get(STUDENT_USERNAME);
            String major = (String)studentObj.get("major");
            JSONArray creditsArray = (JSONArray)studentObj.get(STUDENT_CREDITS);
            JSONArray requirementsArray = (JSONArray)studentObj.get(STUDENT_REQUIREMENTS_LIST);
            JSONArray notesArray = (JSONArray)studentObj.get(STUDENT_NOTES);

            ArrayList<Credit> credits = new ArrayList<>();
            HashMap<Requirement, ArrayList<Credit>> requirements = new HashMap<>();
            ArrayList<String> notes = new ArrayList<>();

            for(Object creditObj : creditsArray) {
                JSONObject creditJSON = (JSONObject)creditObj;
                String courseID = (String) creditJSON.get(STUDENT_COURSE);
                int grade = ((Long)creditJSON.get("grade")).intValue();
                credits.add(new Credit(courseID, grade));
            }

            for(Object requirementObj : requirementsArray) {
                JSONObject requirementJSON = (JSONObject)requirementObj;
                String requirementName = (String)requirementJSON.get()
            }

            for(Object noteObj : notesArray) {
                String note = (String)noteObj;
            }



            Student student = new Student(username, major, credits, requirements, notes);
            students.add(student);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return students;
} 


    /* 
    static ArrayList<Student> loadStudent(){
        ArrayList<Student> students = new ArrayList<>();
        try {
            FileReader reader = new FileReader(STUDENT_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray studentsJSON = (JSONArray)parser.parse(reader);

            for(Object student : studentsJSON) {
                JSONObject studentJSON = (JSONObject) student;
                String username = (String)studentJSON.get(STUDENT_USER_NAME);
                String password = (String)studentJSON.get(STUDENT_PASSWORD);
                String firstName = (String)studentJSON.get(STUDENT_FIRSTNAME);
                String lastName = (String)studentJSON.get(STUDENT_LASTNAME);
                String email = (String)studentJSON.get(STUDENT_EMAIL);

                Major major = parseMajor((JSONObject)studentJSON.get(STUDENT_MAJOR));
                ArrayList<Credit> credits = parseCredits((JSONArray)studentJSON.get(STUDENT_CREDITS));
                HashMap<Requirement, ArrayList<Credit>> requirements = parseRequirements((JSONArray)studentJSON.get(STUDENT_REQUIREMENT));
                ArrayList<String> notes = parseNotes((JSONArray)studentJSON.get(STUDENT_NOTES));

                Student newStudent = new Student(userName, password, firstName, lastName, email, major, credits, requirements, notes);
                students.add(newStudent);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return students;
    }
    */
        /* 
        // Get vars from students.json
        Major major;
        ArrayList<Credit> credits;
        HashMap<Requirement, ArrayList<Credit>> requirements;
        ArrayList<String> notes;
        users.add(new Student(userName, password, firstName, lastName, email, major, credits, requirements, notes));
        return false;
    }
    */

    /*
    static ArrayList<Advisor> loadAdvisor(String userName, String password, String firstName, String lastName, String email){
        // Get vars from admin.json
        ArrayList<Student> advisees;
        users.add(new Advisor(userName, password, firstName, lastName, email, advisees));
        return false;
    }
    private boolean loadAdmin(){
        
    }
    */
    

    public static void main(String[] args) {
        DataLoader dataLoader = DataLoader.getInstance();
        ArrayList<Course> courses = DataLoader.loadCourses();
        ArrayList<Major> majors = DataLoader.loadMajors();
      
        for (Course course : courses) {
            System.out.println("\n" + course.getCourseID());
            System.out.println(course);
            System.out.println("Course Description: " + course.getCourseDescprition());
            System.out.println("Credit Hours: " + course.getCreditHours());
            System.out.println("Semester Availability: " + course.getSemeseterAvailabilty());
            System.out.println("Pre-Requisites: " + course.getPreRequisites());
            System.out.println("Co-Requisites: " + course.getCoRequisites());
            System.out.println("Type: " + course.getType());
        }
        
        

        for(Major major : majors) {
            System.out.println("\n" + major.getName());
            System.out.println("Major ID: " + major.getId());
            System.out.println("Major School: " + major.getSchool());
            System.out.println("Major Department: " + major.getDepartment());
            System.out.println("Major " + major.getRequirements());
        }

        for(Student : Students)
    }
}