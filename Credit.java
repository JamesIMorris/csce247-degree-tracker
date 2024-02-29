import java.util.ArrayList;

public class Credit {
    public class PossibleRequirement{
        Requirement requirement;
        boolean possible;

        public PossibleRequirement(Requirement requirement){
            this.requirement = requirement;
        }
        public PossibleRequirement(Requirement requirement, boolean possible){
            this.possible = possible;
        }

        public Requirement getRequirement(){
            return requirement;
        }
        public void setRequirement(Requirement requirement){
            this.requirement = requirement;
        }
        public boolean getPossible(){
            return possible;
        }
        public void setPossible(boolean possible){
            this.possible = possible;
        }
    }

    private Course course;
    private Semester semesterTaken;
    private int grade;
    private CreditType type;
    private int requirementsAssignedTo;
    private PossibleRequirement possibleRequirements;
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

    public Course getCourse(){
        return course;
    }
    public void setCourse(Course course){
        this.course = course;
    }
    public Semester getSemesterTaken(){
        return semesterTaken;
    }
    public void setSemesterTaken(Semester semeseter){
        semeseterTaken = semester;
    }
    public int getGrade(){
        return grade;
    }
    public void setGrade(int grade){
        this.grade = grade;
    }
    public CreditType getType(){
        return type;
    }
    public void setType(CreditType type){

    }
    public int getRequirementsAssignedTo(){
        return requirementsAssignedTo;
    }
    public String getNote(){
        return note;
    }
    public void setNote(String note){
        this.note = note;
    }
    public PossibleRequirement getPossibleRequirements(){
        return possibleRequirements;
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
