import java.util.ArrayList;

public class MajorList {
    private static MajorList majorList;
    private ArrayList<Major> majors;

    private MajorList() {

    }

    public static MajorList getInstance() {
        if(majorList == null)
            majorList = new MajorList;
        return majorList;
    }

    public ArrayList<Major> getMajors(){

    }
    public void setMajors(ArrayList<Major> majors){
        this.majors = majors;
    }

    public boolean addMajor(String name, String school, String department, ArrayList<Requirement> requirements) {

    }

    public boolean addMajor(Major major) {
        return false;
    }

    public ArrayList<Major> addMajors(ArrayList<Major> majors) {

    }

    public Major getMajor(String name) {

    }

    public Major getMajor(String id) {

    }

    public Major removeMajor(Major major) {

    }
}