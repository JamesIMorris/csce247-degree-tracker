import java.util.ArrayList;
import java.util.UUID;


public class Credit {
    private UUID id;
    private Course course;
    private Semester semesterTaken;
    private int grade;
    private CreditType type;
    private int requirementsAssignedTo;
    private ArrayList<PossibleRequirement> possibleRequirements;
    private String note;

    public Credit(Course course) {
        this.id = UUID.randomUUID();
        this.course = course;
    }

    public Credit(String courseID) {
        this.id = UUID.randomUUID();
        this.course = CourseList.getInstance().getCourseFromName(courseID);
    }

    public Credit(Course course, Semester semesterTaken){
        this.id = UUID.randomUUID();
        this.id = UUID.randomUUID();
        this.course = course;
        this.semesterTaken = semesterTaken;
    }

    public Credit(UUID id, Course course, Semester semesterTaken, int grade, CreditType type, int requirementsAssignedTo,
            String note) {
        this.id = id;
        this.course = course;
        this.semesterTaken = semesterTaken;
        this.grade = grade;
        this.type = type;
        this.requirementsAssignedTo = requirementsAssignedTo;
        this.note = note;
        this.possibleRequirements = new ArrayList<PossibleRequirement>();
    }

    // public Credit(String courseID, Semester semesterTaken, int grade, CreditType type, int requirementsAssignedTo,
    //         String note) {

    // }

    public UUID getID(){
        return this.id;
    }
    public void setID(UUID id){
        this.id = id;
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
    public void setSemesterTaken(Semester semester){
        this.semesterTaken = semester;
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
        this.type = type;
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
    public ArrayList<PossibleRequirement> getPossibleRequirements(){
        return possibleRequirements;
    }
    public void populatePossibleRequirements(){
        ArrayList<Major> majors = MajorList.getInstance().getMajors();
        for(Major major : majors){
            ArrayList<Requirement> requirements = major.getRequirements();
            for(Requirement requirement : requirements){
                if(requirement.getCourses().contains(this.course)){
                    addPossibleRequirement(requirement);
                }
            }
        }
    }
    public void addPossibleRequirement(Requirement requirement){
        boolean addPossibleRequirement = true;
        for(PossibleRequirement possibleRequirement : possibleRequirements){
            if(possibleRequirement.getRequirement() == requirement)
                addPossibleRequirement = false;
        }
        if(addPossibleRequirement)
            possibleRequirements.add(new PossibleRequirement(requirement));
    }

    public String getCourseName() {
        return this.course.getCourseName();
    }

    public String getCourseID() {
        return this.course.getCourseID();
    }

    public String getSubject() {
        return this.course.getSubject();
    }

    public int getCourseNumber() {
        return this.course.getCourseNumber();
    }

    public int getCreditHours() {
        return this.course.getCreditHours();
    }

    public boolean isOverlay() {
        return this.course.isOverlay();
    }

    public boolean isPlacement() {
        return this.course.isPlacement();
    }

    public boolean isTransfer() {
        if(type == CreditType.TRANSFER)
            return true;
        return false;
    }

    public boolean wasWithdrawn() {
        if(type == CreditType.WITHDRAWN)
            return true;
        return false;
    }

    public boolean update(Requirement requirement, boolean open){
        boolean hasRequirement = false;
        for(PossibleRequirement possibleRequirement : possibleRequirements){
            if(possibleRequirement.getRequirement() == requirement){
                possibleRequirement.setPossible(open);
                hasRequirement = true;
            }
        }
        return hasRequirement;
    }
}
