import java.util.ArrayList;
import java.util.UUID;

public class Major {
    private UUID id;
    private String name;
    private String school;
    private String department;
    private ArrayList<Requirement> requirements;

    public Major(UUID id, String name, String school, String department, ArrayList<Requirement> requirements) {
        this.id = id;
        this.name = name;
        this.school = school;
        this.department = department;
        this.requirements = requirements;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public ArrayList<Requirement> getRequirements() {
        return requirements;
    }

    public void setRequirements(ArrayList<Requirement> requirements) {
        this.requirements = requirements;
    }

    public boolean addRequirement(String name, Category category, ArrayList<String> courseIDs, int creditsRequired) {
        Requirement requirement = new Requirement(name, category, courseIDs, creditsRequired);
        return addRequirement(requirement);
    }

    public boolean addRequirement(Requirement requirement) {
        return requirements.add(requirement);
    }

    public Requirement getRequirement(String name) {
        for (Requirement requirement : requirements) {
            if (requirement.getName().equals(name)) {
                return requirement;
            }
        }
        DegreeTracker.getInstance().addError("Requirement not found: " + name);
        return null;
    }

}