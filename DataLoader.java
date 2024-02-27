import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class DataLoader extends DataConstants{
  ArrayList<User> users = new ArrayList<User>();
  ArrayList<Major> major = new ArrayList<Major>();
  ArrayList<Courses> courses = new ArrayList<Courses>();

  public ArrayList<User> getUsers(){
    try{
      FileReader reader = new FileReader(USER_FILE_NAME);
      JSONParser parsec = new JSONParser();
      JSONArray studentJSON = (JSONArray)new JSONParser().parse(reader);
  
      for(int i=0; i < studentJSON.size(); i++){
        JSONObject studentJSON = (JSONObject)studentJSON.get(i);
        UserType userType = (UserType)studentJSON.get(USER_TYPE);
        String userName = (String)studentJSON.get(STUDENT_USER_NAME);
        String password;
        String firstName = (String)studentJSON.get(STUDENT_FIRST_NAME);
        String lastName = (String)studentJSON.get(STUDENT_LAST_NAME);
        String email = (String)studentJSON.get(STUDENT_EMAIL);
        String major = (String)studentJSON.get(STUDENT_MAJOR);
        ArrayList<String> notes = (ArrayList<String>)studentJSON.get(STUDENT_NOTES);
  
        if(userType == UserType.STUDENT){
          loadStudent(userName, password, firstName, lastName, email);
        }
        else if(userType == UserType.ADVISOR){
          loadAdvisor(userName, password, firstName, lastName, email);
        }
        else
          users.add(new Admin(userName, password, firstName, lastName, email));
        
      }
      return users;
    } catch(Exeption e) {
      e.printStackTrace();
    }
    return null;
  }

  

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
  return null;

  try{
    FileReader reader = new FileReader(COURSES_FILE_NAME);
    JSONParser parsec = new JSONParser();
    JSONArray coursesJSON = (JSONArray)new JSONParser().parse(reader);

    for(int i=0; i < coursesJSON.size(); i++){
      JSONObject coursesJSON = (JSONObject)coursesJSON.get(i);
      String id = (String)coursesJSON.get(COURSE_ID);
      String courseName = (String)coursesJSON.get(COURSE_NAME);
      String courseDescription = (String)coursesJSON.get(COURSE_DESCRIPTION);
      Int creditHours = (Int)coursesJSON.get(COURSE_CREDIT_HOURS);
      ArrayList<String> semesterAvailability = (ArrayList<String>)coursesJSON.get(COURSE_SEMESTER_AVAILABILITY);
      ArrayList<String> preRequisites = (ArrayList<String>)coursesJSON.get(COURSE_PREREQUISITES);
      ArrayList<String> coRequisites = (ArrayList<String>)coursesJSON.get(COURSE_COREQUISITES);
      String type = (String)coursesJSON.get(COURSE_TYPE);

      users.add(new Course(courseId, courseName, courseDescription, creditHours, semesterAvailability, preRequisites, coRequisites, type));
    }
    return users;
  } catch(Exeption e) {
    e.printStackTrace();
  }
  return null;

  private boolean loadStudent(String userName, String password, String firstName, String lastName, String email){
    // Get vars from student.json
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
}