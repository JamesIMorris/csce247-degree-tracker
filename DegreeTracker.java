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
        User newUser = userList.signup(username, password, firstName, lastName, email, type);
        if(newUser == null)
            return false;
        currentUser = newUser;
        return true;
    }
}