import java.util.ArrayList;
import java.util.Locale.Category;

public class Major {
    private String name;
    private String school;
    private String department;
    private ArrayList<Requirement> requirements;

    public boolean addRequirement(String name, Category category, ArrayList<String> courseIDs, int creditsRequired) {
        return false;
    }

    public boolean addRequirement(Requirement requirement) {
        return false;
    }

    public Requirement getRequirement(String name) {

    }
}
