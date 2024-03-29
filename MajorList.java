import java.util.ArrayList;
import java.util.UUID;

public class MajorList {
    private static MajorList majorList;
    private ArrayList<Major> majors;
    private ArrayList<Requirement> requirements;

    private MajorList() {
        majors = new ArrayList<Major>();
        requirements = new ArrayList<Requirement>();
    }

    public ArrayList<Major> getMajors() {
        return majors;
    }
    public void setMajors(ArrayList<Major> majors){
        this.majors = majors;
    }

    public static MajorList getInstance() {
        if (majorList == null)
            majorList = new MajorList();
        return majorList;
    }

    public void setRequirements(ArrayList<Requirement> requirements){
        this.requirements = requirements;
    }
    public ArrayList<Requirement> getRequirements(){
        return this.requirements;
    }
    public boolean addRequirement(Requirement requirement){
        if(requirements.contains(requirement) || requirement == null)
            return false;
        return requirements.add(requirement);
    }
    public Requirement getRequirementFromID(UUID id){
        for(Requirement requirement : requirements)
            if(requirement.getID().equals(id))
                return requirement;
        return null;
    }

    public boolean addMajor(UUID id, String name, String school, String department,
            ArrayList<Requirement> requirements) {
        Major major = new Major(id, name, school, department, requirements);
        return majors.add(major);
    }

    public boolean addMajor(Major major) {
        return majors.add(major);
    }

    public ArrayList<Major> addMajors(ArrayList<Major> majors) {
        ArrayList<Major> addMajors = new ArrayList<Major>();
        for(Major major : majors){
            if(!this.majors.contains(major))
                addMajors.add(major);
        }
        this.majors.addAll(addMajors);
        return addMajors;
    }

    public Major getMajorFromName(String name) {
        for (Major major : majors) {
            if (major.getName().equals(name)) {
                return major;
            }
        }
        DegreeTracker.getInstance().addError("Major not found: " + name);
        return null;
    }

    public Major getMajorFromID(UUID id) {
        for (Major major : majors) {
            if (major.getId().equals(id)) {
                return major;
            }
        }
        DegreeTracker.getInstance().addError("Major not found with name: " + id);
        return null;
    }
}