import java.util.ArrayList;

public class Major {
    private String name;
    private String school;
    private String department;
    private ArrayList<Requirement> requirements;

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getSchool(){
        return this.school;
    }
    public void setSchool(String school){
        this.school = school;
    }
    public String getDepartment(){
        return department;
    }
    public ArrayList<Requirement> getRequirements(){
        return requirements;
    }
    public ArrayList<Requirement> setRequirements(){
        this.requirements = requirements;
    }

    public boolean addRequirement(String name, Category category, ArrayList<String> courseIDs, int creditsRequired) {
        return false;
    }

    public boolean addRequirement(Requirement requirement) {
        return false;
    }

    public boolean removeRequirement(Requirement requirement){
        return false;
    }

    public Requirement getRequirement(String name) {

    }
}
