public class DegreeTracker{
    private static DegreeTracker degreeTracker;
    private User currentUser;
    private String error;
    private UserList userList;
    private MajorList majorList;
    private CourseList courseList;

    private DegreeTracker(){
        error = "";
        userList = UserList.getInstance();
        majorList = MajorList.getInstance();
        courseList = CourseList.getInstance();
    }

    public DegreeTracker getInstance(){
        if(degreeTracker == null)
            degreeTracker = new DegreeTracker();
        return degreeTracker;
    }

    public void addError(String errorMessage){
        error += "\n" + errorMessage;
    }

    public boolean login(String username, String password){
        User loginUser = userList.findUser(username);
        if(loginUser == null)
            return false;
        if(!loginUser.passwordMathes(password))
            return false;
        this.currentUser = loginUser;
        return true;
            
    }

    public boolean signup(String username, String password, String firstName, String lastName, String email, UserType type){
        boolean success = true;
        if( username.length() < 1
                || password.length() <1
                || firstName.length() < 1
                || lastName.length() < 1
                || email.length() < 1
                || type == null){
            error += "All fields must be filled";
            return false;
        }
            
        if(!userList.usernameAvailable(username)){
            error += "Username unavailable";
            success = false;
        }
        if(!checkPassword(password))
            success = false;
        
    }

    private boolean checkPassword(String password){
        return false;
    }

    public boolean writeUsers(){
        return true;
    }
    public boolean writeMajors(){
        return true;
    }
    public boolean writeCourses(){
        return true;
    }
}