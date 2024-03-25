import java.util.ArrayList;
import java.util.HashMap;

public class UserList {
    private static UserList userList;
    private ArrayList<User> users;
    private User currentUser;
    private DegreeTracker degreeTracker;

    private UserList() {
        DataLoader.getInstance();
        this.degreeTracker = DegreeTracker.getInstance();

    }

    public static UserList getInstance() {
        if (userList == null)
            userList = new UserList();
        return userList;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        if (user != null)
            currentUser = user;
    }

    public boolean login(String username, String password) {
        User loginUser = findUser(username);
        if (loginUser == null)
            return false;
        if (!loginUser.passwordMathes(password))
            return false;
        this.currentUser = loginUser;
        return true;
    }

    public Student createNewStudent(String username, String password, String firstName, String lastName, String email,
            String uscID) {
        if (username == null
                || password == null
                || firstName == null
                || lastName == null
                || email == null
                || uscID == null)
            return null;
        if (!usernameAvailable(username)
                || username.length() < 1
                || password.length() < 1
                || firstName.length() < 1
                || lastName.length() < 1
                || email.length() < 1)
            return null;
        return new Student(username, password, firstName, lastName, email, uscID);
    }

    public Student createNewStudent(String username, String password, String firstName, String lastName, String email,
            String uscID, Major major, String applicationArea, ArrayList<Credit> credits, HashMap<Requirement, ArrayList<Credit>> requirements) {
        if (username == null
                || password == null
                || firstName == null
                || lastName == null
                || email == null
                || major == null
                || credits == null
                || requirements == null)
            return null;
        if (!usernameAvailable(username)
                || username.length() < 1
                || password.length() < 1
                || firstName.length() < 1
                || lastName.length() < 1
                || email.length() < 1)
            return null;
        return new Student(username, password, firstName, lastName, email, uscID, major, applicationArea, credits, requirements,
                new ArrayList<String>());
    }

    public Advisor createNewAdvisor(String username, String password, String firstName, String lastName, String email) {
        if (username == null
                || password == null
                || firstName == null
                || lastName == null
                || email == null)
            return null;
        if (!usernameAvailable(username)
                || username.length() < 1
                || password.length() < 1
                || firstName.length() < 1
                || lastName.length() < 1
                || email.length() < 1)
            return null;
        return new Advisor(username, password, firstName, lastName, email);
    }

    public boolean usernameAvailable(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username))
                return false;
        }
        return true;
    }

    public boolean emailAvailable(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email))
                return false;
        }
        return true;
    }

    public User findUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username))
                return user;
        }
        return null;
    }

    public ArrayList<User> searchUser(String string) {
        string = string.toLowerCase();
        ArrayList<User> returnList = new ArrayList<User>();
        for (User user : users) {
            if (user.getUsername().toLowerCase().contains(string)
                    || string.contains(user.getUsername().toLowerCase())
                    || user.getFirstName().toLowerCase().contains(string)
                    || string.contains(user.getFirstName().toLowerCase())
                    || user.getLastName().toLowerCase().contains(string)
                    || string.contains(user.getLastName().toLowerCase())
                    || user.getEmail().toLowerCase().contains(string)
                    || string.contains(user.getEmail().toLowerCase()))
                addUserToList(returnList, user);
        }
        return returnList;
    }

    private boolean checkPassword(String password) {
        if (password.length() < 3) {
            degreeTracker.addError("Password must be at least 3 characters long");
            return false;
        }
        return true;
    }

    private void addUserToList(ArrayList<User> list, User newUser) {
        for (User listUser : list) {
            if (listUser == newUser)
                return;
        }
        list.add(newUser);
        return;
    }

    public boolean logout(){
        //TODO
        //DataWriter.getInstance().writeData();
        this.currentUser = null;
        return true;
    }

    public boolean checkSignup(String username, String password){
        if(!usernameAvailable(username)){
            DegreeTracker.getInstance().addError("The username \"" + username + "\" is not available");
            return false;
        }
        return checkPassword(password);
    }
    public boolean studentSignup(String username, String password, String firstName, String lastName, String email, String uscID){
        users.add(new Student(username, password, firstName, lastName, email, uscID));
        return true;
    }
    public boolean advisorSignup(String username, String password, String firstName, String lastName, String email){
        users.add(new Advisor(username, password, firstName, lastName, email));
        return true;
    }
    public String findStudentFromID(String uscID){
        for(User user : users){
            if(user.getUserType() != UserType.STUDENT)
                continue;
            Student student = (Student)user;
            if(student.getUscID().equalsIgnoreCase(uscID))
                return student.getUsername();
        }
        DegreeTracker.getInstance().addError("USCID Not Found");
        return null;
    }

    public ArrayList<User> searchUserByType(UserType userType) {
        ArrayList<User> usersByType = new ArrayList<>();
        for(User user : users) {
            if(user instanceof Student && userType == UserType.STUDENT){
                usersByType.add(user);
            } else if(user instanceof Advisor && userType == UserType.ADVISOR) {
                usersByType.add(user);
            } else if(user instanceof Admin && userType == UserType.ADMIN) {
                usersByType.add(user);
            }
        }
        return usersByType;
    }

}