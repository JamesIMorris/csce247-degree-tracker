import java.io.File;
import java.io.FileWriter;

public class DegreeTracker {
    private static DegreeTracker degreeTracker;
    private String error;
    private UserList userList;
    private MajorList majorList;
    private CourseList courseList;
    private DataLoader dataLoader;
    private DataWriter dataWriter;

    private DegreeTracker() {
        error = "";
        courseList = CourseList.getInstance();
        majorList = MajorList.getInstance();
        userList = UserList.getInstance();
        dataLoader = DataLoader.getInstance();
        dataWriter = DataWriter.getInstance();
        userList.setDegreeTracker(this);
    }

    public static DegreeTracker getInstance() {
        if (degreeTracker == null)
            degreeTracker = new DegreeTracker();
        return degreeTracker;
    }

    public void addError(String errorMessage) {
        error += "\n" + errorMessage;
    }

    public String popError() {
        error = "";
        return error;
    }

    public void clearError() {
        error = "";
    }

    public String getError() {
        return error;
    }

    public boolean login(String username, String password) {
        return userList.login(username, password);
    }

    public boolean logout() {
        return userList.logout();
    }

    public boolean checkSignup(String username, String password) {
        return userList.checkSignup(username, password);
    }

    public boolean studentSignup(String username, String password, String firstName, String lastName, String email,
            String uscID) {
        return userList.studentSignup(username, password, firstName, lastName, email, uscID);
    }

    public boolean advisorSignup(String username, String password, String firstName, String lastName, String email) {
        return userList.advisorSignup(username, password, firstName, lastName, email);
    }

    public boolean studentAssignCourse(String username, String courseID, String semesterTaken, String requirement) {
        Student student = (Student)userList.findUser(username);
        return student.addCredit(courseID, semesterTaken, requirement); //TODO
    }

    public boolean setApplicationArea(String username, String applicationArea) {
        ((Student) userList.findUser(username)).setApplicationArea("Digital Design");
        //((Student) userList.findUser(username)).setApplicationArea(applicationArea);
        return true;
    }

    public String findStudentFromID(String uscID) {
        return userList.findStudentFromID(uscID);
    }

    public boolean addNote(String username, String note) {
        return ((Student) userList.findUser(username)).addNote(note += "\n-" 
                    + userList.getCurrentUser().getFirstName() + " " + userList.getCurrentUser().getLastName());
    }

    public String studentHomePage(String username) {
        return UIFormatter.studentHomePage(username);
    }

    public String studentUnsatisfiedRequirements(String username) {
        return UIFormatter.studentUnsatisfiedRequirements(username);
    }

    public String studentPossibleRequirementCredits(String username, String requirement) {
        return UIFormatter.studentPossibleRequirementCredits(username, requirement);
    }

    public String adivsorHomePage(String username) {
        return UIFormatter.adivsorHomePage(username);
    }

    public String homePage(String username) {
        if (userList.getCurrentUser().getUserType().equals(UserType.STUDENT))
            return studentHomePage(username);
        return adivsorHomePage(username);
    }

    public static String advisorStudentPage(String studentUsername) {
        return UIFormatter.advisorStudentPage(studentUsername);
    }

    public static String advisorNotes(String username) {
        return UIFormatter.advisorNotes(username);
    }

    public String getCurrentUsername() {
        return userList.getCurrentUser().getUsername();
    }

    public User getCurrentUser() {
        return userList.getCurrentUser();
    }

    public boolean eightSemesterPlanToTextFile(String fileName, String studentUsername){
        if(userList.findUser(studentUsername).getUserType() != UserType.STUDENT)
            return false;
        String eightSemesterPlan = "***** Eight Semester Plan *****\n";
        eightSemesterPlan += UIFormatter.studentHomePage(studentUsername);
        eightSemesterPlan += "\n***** Unassigned Requirements *****\n";
        eightSemesterPlan += UIFormatter.studentUnsatisfiedRequirements(studentUsername);
        try{
            File planFile = new File(fileName + ".txt");
            planFile.createNewFile();
        }
        catch(Exception e){
            addError("Error creating semester plan file");
            e.printStackTrace();
            return false;
        }
        try{
            FileWriter fileWriter = new FileWriter(fileName + ".txt");
            fileWriter.write(eightSemesterPlan);
            fileWriter.close();
        }
        catch(Exception e){
            addError("Error writing semester plan to file");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean eightSemesterPlanToTextFile(String fileName){
        return eightSemesterPlanToTextFile(fileName, userList.getCurrentUser().getUsername());
    }

    public boolean checkAdditionalInfo(String[] additionalInfo){
        return userList.checkAdditionalInfo(additionalInfo);
    }    

    public boolean studentHasRequirement(String username, String requirement){
        return userList.studentHasRequirement(username, requirement);
    }

    public String possibleApplicationAreas(String username){
        return UIFormatter.possibleApplicationAreas(username);
    }

    public boolean courseExists(String courseID){
        return courseList.getCourseFromID(courseID) != null;
    }

    public boolean addAdvisee(String advisorUsername, String studentUsername){
        return userList.addAdvisee(advisorUsername, studentUsername);
    }

    public MajorList getMajorList() {
        return majorList;
    }

    public CourseList getCourseList() {
        return courseList;
    }

    public UserList getUserList() {
        return userList;
    }

    public String getAAAbreviation(String applicationArea){
        return majorList.getAAAbreviation(applicationArea);
    }
}
