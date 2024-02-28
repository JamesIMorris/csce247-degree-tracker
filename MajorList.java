import java.util.ArrayList;

public class MajorList {
    private static MajorList majorList;
    private ArrayList<Major> majors;

    private MajorList() {
        majors = new ArrayList<>();
    }

    public static MajorList getInstance() {
        if (majorList == null)
            majorList = new MajorList();
        return majorList;
    }

    public boolean addMajor(String name, String school, String department, ArrayList<Requirement> requirements) {
        Major major = new Major(name, school, department, requirements);
        return addMajor(major);
    }

    public boolean addMajor(Major major) {
        return majors.add(major);
    }

    public ArrayList<Major> addMajors(ArrayList<Major> majors) {
        majors.addAll(majors);
        return majors;
    }

    public Major getMajor(String name) {

    }

    public Major getMajor(String id) {

    }

}