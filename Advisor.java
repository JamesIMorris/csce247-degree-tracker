import java.util.ArrayList;

public class Advisor extends User{
    private ArrayList<Student> advisees;

    
    public Advisor(String username, String password, String firstName, String lastName, String email){
        super(username, password, firstName, lastName, email);
        this.advisees = new ArrayList<Student>();
    }
    public Advisor(String username, String password, String firstName, String lastName, String email, ArrayList<Student> advisees){
        super(username, password, firstName, lastName, email);
        this.advisees = advisees;
    }

    public ArrayList<Student> getAdvisees() {
        return advisees;
    }
    public void setAdvisees(ArrayList<Student> advisees) {
        this.advisees = advisees;
    }
    public boolean addAdvisee(Student advisee){
        if(advisee == null)
            return false;
        if(!advisees.contains(advisee))
            advisees.add(advisee);
        return true;
    }
    public boolean addAdvisee(String username){
        User user = UserList.getInstance().findUser(username);
        if(user == null || user.getUserType() != UserType.STUDENT)
            return false;
        return addAdvisee((Student)user);
    }
    public boolean removeAdvisee(Student advisee){
        if(advisee == null)
            return false;
        if(advisees.contains(advisee))
            advisees.remove(advisee);
        return true;
    }
    public boolean removeAdvisee(String username){
        User user = UserList.getInstance().findUser(username);
        if(user == null || user.getUserType() != UserType.STUDENT)
            return false;
        return addAdvisee((Student)user);
    }


    @Override
    public UserType getUserType() {
        return UserType.ADVISOR;
    }
}