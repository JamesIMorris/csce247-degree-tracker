public class DegreeTracker{
    private static DegreeTracker degreeTracker;
    private User currentUser;
    private String error;

    private DegreeTracker(){
        error = "";
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
        return false;
    }

    public boolean signup(String username, String password, String firstName, String lastName, String email, UserType type){
        return false;
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