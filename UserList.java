import java.util.ArrayList;
import java.util.HashMap;

public class UserList {
    private static UserList userList;
    private ArrayList<User> users;
    private User currentUser;
    private DegreeTracker degreeTracker;

    private UserList() {
        users = new ArrayList<User>();
    }

    public static UserList getInstance() {
        if (userList == null)
            userList = new UserList();
        return userList;
    }

    public void setDegreeTracker(DegreeTracker degreeTracker){
        this.degreeTracker = degreeTracker;
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
            degreeTracker.addError("The username \"" + username + "\" is not available");
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
        degreeTracker.addError("USCID Not Found");
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

    public boolean addUser(User user){
        if(users.contains(user) || user == null)
            return false;
        return users.add(user);
    }
        
    public void setUsers(ArrayList<User> users){
        this.users = users;
    }

    public boolean addAdvisee(String advisorUsername, String studentUsername){
        User user = findUser(advisorUsername);
        if(user.getUserType() != UserType.ADVISOR){
            degreeTracker.addError(advisorUsername + " is not an advisor");
            return false;
        }
        Advisor advisor = (Advisor)user;
        return advisor.addAdvisee(studentUsername);
    }

    public boolean checkAdditionalInfo(String[] additionalInfo){
        boolean success = true;
        String firstName = additionalInfo[0];
        String lastName = additionalInfo[1];
        String email = additionalInfo[2];
        if(firstName == null || firstName == ""){
            degreeTracker.addError("First Name was empty");
            success = false;
        }
        if(lastName == null || lastName == ""){
            degreeTracker.addError("Last Name was empty");
            success = false;
        }
        if(email == null || email == ""){
            degreeTracker.addError("Email was empty");
            success = false;
        }
        return success;
    }

    public boolean studentHasRequirement(String username, String requirement){
        Student student = (Student)findUser(username);
        Major major = student.getMajor();
        Requirement majorRequirement = major.getRequirementFromAbbreviation(requirement);
        if(majorRequirement == null || !studentHasRequirement(username, requirement))
            return false;
        return true;
    }

}