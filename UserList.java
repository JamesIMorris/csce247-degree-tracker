import java.util.ArrayList;
import java.util.HashMap;

public class UserList{
    private static UserList userList;
    private ArrayList<User> users;
    
    private UserList(){
        loadUsers();
    }

    public static UserList getInstance(){
        if(userList == null)
            userList = new UserList();
        return userList;
    }

    public ArrayList<User> getUsers(){
        return users;
    }

    public boolean createNewUser(String username, String password, String firstName, String lastName, String email, String userType){
        return false;
    }
    
    public Student createNewStudent(String username, String password, String firstName, String lastName, String email, Major major){
        if(username == null
                || password == null
                || firstName == null
                || lastName == null
                || email == null
                || major == null)
            return null;
        if(!usernameAvailable(username)
                || username.length() < 1
                || password.length() < 1
                || firstName.length() < 1
                || lastName.length() < 1
                || email.length() < 1)
            return null;
        return new Student(username, password, firstName, lastName, email, major);
    }
    public Student createNewStudent(String username, String password, String firstName, String lastName, String email, Major major, ArrayList<Credit> credits, HashMap<Requirement, ArrayList<Credit>> requirements){
        if(username == null
                || password == null
                || firstName == null
                || lastName == null
                || email == null
                || major == null
                || credits == null
                || requirements == null)
            return null;
        if(!usernameAvailable(username)
                || username.length() < 1
                || password.length() < 1
                || firstName.length() < 1
                || lastName.length() < 1
                || email.length() < 1)
            return null;
        return new Student(username, password, firstName, lastName, email, major, credits, requirements, new ArrayList<String>());
    }
    public Advisor createNewAdvisor(String username, String password, String firstName, String lastName, String email){
        if(username == null
                || password == null
                || firstName == null
                || lastName == null
                || email == null)
            return null;
        if(!usernameAvailable(username)
                || username.length() < 1
                || password.length() < 1
                || firstName.length() < 1
                || lastName.length() < 1
                || email.length() < 1)
            return null;
        return new Advisor(username, password, firstName, lastName, email);
    }

    public boolean usernameAvailable(String username){
        for( User user : users){
            if(user.getUsername().equals(username))
                return false;
        }
        return true;
    }

    public User findUser(String username){
        for( User user : users){
            if(user.getUsername().equals(username))
                return user;
        }
        return null;
    }

    public ArrayList<User> searchUser(String string){
        string = string.toLowerCase();
        ArrayList<User> returnList = new ArrayList<User>();
        for( User user : users){
            if(user.getUsername().toLowerCase().contains(string) 
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
    private void addUserToList(ArrayList<User> list, User newUser){
        for( User listUser : list){
            if(listUser == newUser)
                return;
        }
        list.add(newUser);
        return;
    }

    private boolean loadUsers(){
        this.users = DataLoader.getUsers();
        if(users == null)
            return false;
        return true;
    }


}