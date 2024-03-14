import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

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

    }

    private boolean LoadCourses(){
        ArrayList<Course> courses = new ArrayList<Course>();
        try{
            FileReader reader = new FileReader(COURSES_FILE_NAME);
            JSONParser parsec = new JSONParser();
            JSONArray coursesJSON = (JSONArray)new JSONParser().parse(reader);
        
            for(Object course : coursesJSON){
                JSONObject courseJSON = (JSONObject)course;
                String id = (String)courseJSON.get(COURSE_ID);
                String courseName = (String)courseJSON.get(COURSE_NAME);
                String courseDescription = (String)courseJSON.get(COURSE_DESCRIPTION);
                int creditHours = (int)courseJSON.get(CREDIT_HOURS);
                ArrayList<String> semesterAvailability = (ArrayList<String>)courseJSON.get(COURSE_SEMESTER_AVAILABILITY);
                ArrayList<String> preRequisites = (ArrayList<String>)courseJSON.get(COURSE_PREREQUISITES);
                ArrayList<String> coRequisites = (ArrayList<String>)courseJSON.get(COURSE_COREQUISITES);
                String type = (String)courseJSON.get(COURSE_TYPE);
        
                courses.add(new Course(id, courseName, courseDescription, creditHours, semesterAvailability, preRequisites, coRequisites, type));
            }
            return users;
        } catch(Exception e) {
        e.printStackTrace();
        }
    }

  

  
    private boolean loadMajors(){
        try{
            FileReader reader = new FileReader(MAJOR_FILE_NAME);
            JSONParser parsec = new JSONParser();
            JSONArray majorJSON = (JSONArray)new JSONParser().parse(reader);

            for(int i=0; i < majorJSON.size(); i++){
                JSONObject majorJSON = (JSONObject)majorJSON.get(i);
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

    }


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
    }