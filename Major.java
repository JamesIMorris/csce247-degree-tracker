import java.util.ArrayList;
import java.util.Locale.Category;

public class Major {
    private String name;
    private String school;
    private String department;
    private ArrayList<Requirement> requirements;

    public Major(String name, String school, String department, ArrayList<Requirement> requirements) {
        this.name = name;
        this.school = school;
        this.department = department;
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
        throw new RuntimeException("Requirement not found: " + name);
    }

}