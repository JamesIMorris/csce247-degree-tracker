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
        return false;
    }
    public boolean addAdvisee(String username){
        return false;
    }
    public boolean removeAdvisee(Student advisee){
        return false;
    }
    public boolean removeAdvisee(String username){
        return false;
    }


    @Override
    public UserType getUserType() {
        return UserType.ADVISOR;
    }
}