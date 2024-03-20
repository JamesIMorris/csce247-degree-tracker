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
        loadCourses();
    }

    private static ArrayList<Course> loadCourses(){
        ArrayList<Course> courses = new ArrayList<Course>();
        try{
            FileReader reader = new FileReader(COURSE_FILE_NAME);
            JSONParser parsec = new JSONParser();
            JSONArray coursesJSON = (JSONArray)parsec.parse(reader);
        
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
                //for(String string : semesterAvailableStrings){
                    //semesterAvailability.add(Season.fromString(string));
               // }
                CourseType type = CourseType.fromString((String)courseJSON.get(COURSE_TYPE));
        
                courses.add(new Course(id, courseName, courseDescription, creditHours, semesterAvailability, type));
            }
            for(int i=0; i<courses.size(); i++){
                JSONObject courseJSON = (JSONObject)coursesJSON.get(i);
               
                ArrayList<Course> preRequisites = toCourseArrayList((JSONArray)courseJSON.get(COURSE_PRE_REQUISISTES));
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


    private static ArrayList<Course> toCourseArrayList(JSONArray courseIDsJSON) {
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
        try{
            FileReader reader = new FileReader(MAJOR_FILE_NAME);
            JSONParser parsec = new JSONParser();
            JSONArray majorsJSON = (JSONArray)new JSONParser().parse(reader);

            for(Object major : majorsJSON){
                JSONObject majorJSON = (JSONObject)major.get(i);
                UUID id = UUID.fromString((String)majorJSON.get(MAJOR_ID));
                String name = (String)majorJSON.get(MAJOR_NAME);
                String school = (String)majorJSON.get(MAJOR_SCHOOL);
                String department = (String)majorJSON.get(MAJOR_DEPARTMENT);
                ArrayList<String> requirements = (ArrayList<String>)majorJSON.get(MAJOR_REQUIREMENTS);

                major.add(new Major(majorID, name, school, department, requirements));
            }
            return major;
        } catch(Exeption e) {
            e.printStackTrace();
        }
    
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

    public static void main(String[] args){
        ArrayList<Course> courses = DataLoader.loadCourses();

        for(Course course: courses){
            System.out.println(course);
        }
    }
    }