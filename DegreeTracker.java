public class DegreeTracker {
    private static DegreeTracker degreeTracker;
    private String error;
    private UserList userList;
    private MajorList majorList;
    private CourseList courseList;

    private DegreeTracker() {
        error = "";
        courseList = CourseList.getInstance();
        userList = UserList.getInstance();
        majorList = MajorList.getInstance();
    }

    public static DegreeTracker getInstance() {
        if (degreeTracker == null)
            degreeTracker = new DegreeTracker();
        return degreeTracker;
    }

    public void addError(String errorMessage) {
        error += "\n" + errorMessage;
    }

    public String popError(){
        error = "";
        return error;
    }
    public void clearError(){
        error = "";
    }
    public String getError(){
        return error;
    }

    public boolean login(String username, String password) {
        return userList.login(username, password);
    }

    public boolean logout(){
        return UserList.getInstance().logout();
    }

    public boolean checkSignup(String username, String password){
        return userList.checkSignup( username, password);
    }

    public boolean studentSignup(String username, String password, String firstName, String lastName, String email,
            String uscID) {
        return userList.studentSignup(username, password, firstName, lastName, email, uscID);
    }
    public boolean advisorSignup(String username, String password, String firstName, String lastName, String email){
        return userList.advisorSignup(username, password, firstName, lastName, email);
    }
    public boolean studentAssignCourse(String username, String courseID, String semesterTaken, String requirement){
        return ((Student)userList.findUser(username)).assignCredit(courseID, semesterTaken, requirement);
    }
    public boolean setApplicationArea(String username, String applicationArea){
        ((Student)userList.findUser(username)).setApplicatioNArea(applicationArea);
        return true;
    }
    public String findStudentFromID(String uscID){
        return userList.findStudentFromID(uscID);
    }
    public boolean addNote(String username, String note){
        return ((Student)userList.findUser(username)).addNote(note);
    }

    public static String studentHomePage(String username){
        return UIFormatter.studentHomePage(username);
    }
    public static String studentUnsatisfiedRequirements(String username){
        return UIFormatter.studentUnsatisfiedRequirements(username);
    }
    public static String studentPossibleRequirementCredits(String username, String requirement){
        return UIFormatter.studentPossibleRequirementCredits(username, requirement);
    }
    public static String adivsorHomePage(String username){
        return UIFormatter.adivsorHomePage(username);
    }
    public static String advisorStudentPage(String studentUsername){
        return UIFormatter.advisorStudentPage(studentUsername);
    }
    public static String advisorNotes(){
        return UIFormatter.advisorNotes();
    }

    public User getCurrentUser() {
        return userList.getCurrentUser();
    }

    public MajorList getMajorList(){
        return majorList;
    }
    public CourseList getCourseList(){
        return courseList;
    }
}
