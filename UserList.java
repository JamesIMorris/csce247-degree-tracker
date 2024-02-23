import java.util.ArrayList;

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

    public boolean createNewUser(String username, String password, String firstName, String lastName, String email, String userType){
        return false;
    }

    public boolean writeUsers(){
        return false;
    }

    public boolean usernameAvailable(String username){
        return false;
    }

    public User findUser(String username){
        return null;
    }

    public ArrayList<User> searchUser(String string){
        return null;
    }

    private boolean loadUsers(){
        users = null;
        return false;
    }


}