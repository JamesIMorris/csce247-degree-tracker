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
            Course course = CourseList.getInstance().getCourse(courseId);
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
  

    
    private boolean loadMajors(){
        ArrayList<Major> majors = new ArrayList<Major>();
        try{
            FileReader reader = new FileReader(MAJOR_FILE_NAME);
            JSONParser parsec = new JSONParser();
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

                Major newMajor = new Major(name, school, department, requirements);
                majors.add(newMajor);

                //major.add(new Major(majorID, name, school, department, requirements));
            }
           
        } catch(Exeption e) {
            e.printStackTrace();
        }
        return majors;
    }
    


    private boolean loadUsers(){
        return false;
    }

/* 
    private boolean loadStudent(String userName, String password, String firstName, String lastName, String email){
        // Get vars from students.json
        Major major;
        ArrayList<Credit> credits;
        HashMap<Requirement, ArrayList<Credit>> requirements;
        ArrayList<String> notes;
        users.add(new Student(userName, password, firstName, lastName, email, major, credits, requirements, notes));
        return false;
    }
    private boolean loadAdvisor(String userName, String password, String firstName, String lastName, String email){
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
        }
    }
}