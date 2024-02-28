public class Admin extends User{
    public Admin(String username, String password, String firstName, String lastName, String email){
        super(username, password, firstName, lastName, email);
    }

    public UserType getUserType(){
        return UserType.ADMIN;
    }
}