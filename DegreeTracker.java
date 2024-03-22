public class DegreeTracker {
    private static DegreeTracker degreeTracker;
    private String error;
    private UserList userList;
    private MajorList majorList;
    private CourseList courseList;
    private boolean isLoggedIn;

    private DegreeTracker() {
        error = "";
        courseList = CourseList.getInstance();
        userList = UserList.getInstance();
        majorList = MajorList.getInstance();
        isLoggedIn = false;
    }

    public static DegreeTracker getInstance() {
        if (degreeTracker == null)
            degreeTracker = new DegreeTracker();
        return degreeTracker;
    }

    public void addError(String errorMessage) {
        error += "\n" + errorMessage;
    }

    public boolean login(String username, String password) {
        return userList.login(username, password);
    }

    public boolean signup(String username, String password, String firstName, String lastName, String email,
            UserType type) {
        if (userList.signup(username, password, firstName, lastName, email, type) == null)
            return false;
        return true;
    }

    public void logout() {
        isLoggedIn = false;
        userList.setCurrentUser(null);
        System.out.println("Logout successful");
    }

    public User getCurrentUser() {
        return userList.getCurrentUser();
    }

}
