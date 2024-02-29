import java.util.ArrayList;

public class Credit {
    private Course course;
    private Semester semesterTaken;
    private int grade;
    private CreditType type;
    private int requirementsAssignedTo;
    private ArrayList<Requirement> possibleRequirements;
    private String note;

    public Credit(Course course) {
        this.course = course;
    }

    public Credit(String courseID) {

    }

    public Credit(Course course, Semester semesterTaken, int grade, CreditType type, int requirementsAssignedTo,
            String note) {
        this.course = course;
        this.semesterTaken = semesterTaken;
        this.grade = grade;
        this.type = type;
        this.requirementsAssignedTo = requirementsAssignedTo;
        this.note = note;
        this.possibleRequirements = new ArrayList<>();
    }

    public Credit(String courseID, Semester semesterTaken, int grade, CreditType type, int requirementsAssignedTo,
            String note) {

    }

    public String getCourseName() {
        return "";
    }

    public String getCourseID() {
        return "";
    }

    public String getSubject() {
        return "";
    }

    public int getCourseNumber() {
        return 0;
    }

    public int getCreditHours() {
        return 0;
    }

    public boolean isOverlay() {
        return false;
    }

    public boolean isPlacement() {
        return false;
    }

    public boolean isTransfer() {
        return false;
    }

    public boolean wasWithdrawn() {
        return false;
    }

}
