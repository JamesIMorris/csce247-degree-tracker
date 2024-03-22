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

    public boolean createNewUser(String username, String password, String firstName, String lastName, String email,
            UserType userType) {
        return false;
    }

    public User signup(String username, String password, String firstName, String lastName, String email,
            UserType type) {
        boolean success = true;
        if (username.length() < 1
                || password.length() < 1
                || firstName.length() < 1
                || lastName.length() < 1
                || email.length() < 1
                || type == null) {
            degreeTracker.addError("All fields must be filled");
            return null;
        }
        if (!usernameAvailable(username)) {
            degreeTracker.addError("Username unavailable");
            success = false;
        }
        if (!checkPassword(password))
            success = false;
        if (!emailAvailable(email)) {
            degreeTracker.addError("Email already in use");
            success = false;
        }
        if (!success)
            return null;
        success = createNewUser(username, password, firstName, lastName, email, type);
        return findUser(username);
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
            Major major) {
        if (username == null
                || password == null
                || firstName == null
                || lastName == null
                || email == null
                || major == null)
            return null;
        if (!usernameAvailable(username)
                || username.length() < 1
                || password.length() < 1
                || firstName.length() < 1
                || lastName.length() < 1
                || email.length() < 1)
            return null;
        return new Student(username, password, firstName, lastName, email, major);
    }

    public Student createNewStudent(String username, String password, String firstName, String lastName, String email,
            Major major, ArrayList<Credit> credits, HashMap<Requirement, ArrayList<Credit>> requirements) {
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
        return new Student(username, password, firstName, lastName, email, major, credits, requirements,
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
}