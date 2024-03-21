import java.util.ArrayList;
import java.util.UUID;

public class MajorList {
    private static MajorList majorList;
    private ArrayList<Major> majors;

    private MajorList() {
        majors = new ArrayList<>();
    }

    public ArrayList<Major> getMajors() {
        return majors;
    }

    public static MajorList getInstance() {
        if (majorList == null)
            majorList = new MajorList();
        return majorList;
    }

    public boolean addMajor(UUID id, String name, String school, String department, ArrayList<Requirement> requirements) {
        Major major = new Major(id, name, school, department, requirements);
        return majors.add(major);
    }

    public boolean addMajor(Major major) {
        return majors.add(major);
    }

    public ArrayList<Major> addMajors(ArrayList<Major> majors) {
        majors.addAll(majors);
        return majors;
    }

    public Major getMajor(String name) {
        for (Major major : majors) {
            if (major.getName().equals(name)) {
                return major;
            }
        }
        throw new RuntimeException("Major not found: " + name);
    }

    
      public Major getMajorId(UUID id) {
        for(Major major : majors) {
            if(major.getId().equals(id)) {
                return major;
            }
        }
        throw new RuntimeException("Major not found with name: " + id);
      }


}