import java.util.ArrayList;
import java.util.HashMap;

public class Student extends User{
    private Major major;
    private ArrayList<Credit> credits;
    private HashMap<Requirement, ArrayList<Credit>> requirements;
    private ArrayList<String> notes;
    private boolean changeInProgress;
    private Major backUpMajor;
    private ArrayList<Credit> backupCredits;
    private HashMap<Requirement, ArrayList<Credit>> backupRequirements;

    public Student(String username, String password, String firstName, String lastName, String email){
        super(username, password, firstName, lastName, email);
        this.credits = new ArrayList<Credit>();
        this.requirements = new HashMap<Requirement, ArrayList<Credit>>();
        this.notes = new ArrayList<String>();
        this.changeInProgress = true; 
    }
    public Student(String username, String password, String firstName, String lastName, String email, Major major){
        super(username, password, firstName, lastName, email);
        this.major = major;
        this.credits = new ArrayList<Credit>();
        this.requirements = new HashMap<Requirement, ArrayList<Credit>>();
        this.notes = new ArrayList<String>();
        this.changeInProgress = true; 
    }
    public Student(String username, String password, String firstName, String lastName, String email, Major major, ArrayList<Credit> credits){
        super(username, password, firstName, lastName, email);
        this.major = major;
        this.credits = credits;
        this.requirements = new HashMap<Requirement, ArrayList<Credit>>();
        this.notes = new ArrayList<String>();
        this.changeInProgress = true; 
    }
    public Student(String username, String password, String firstName, String lastName, String email, Major major, ArrayList<Credit> credits, HashMap<Requirement, ArrayList<Credit>> requirements, ArrayList<String> notes){
        super(username, password, firstName, lastName, email);
        this.major = major;
        this.credits = credits;
        this.requirements = requirements;
        this.notes = notes;
        this.changeInProgress = false; 
    }
    public Student(String username, Major major, ArrayList<Credit> credits, HashMap<Requirement, ArrayList<Credit>> requirements, ArrayList<String> notes){
        super(username);
        this.major = major;
        this.credits = credits;
        this.requirements = requirements;
        this.notes = notes;
    }

    public Major getMajor() {
        return major;
    }
    public ArrayList<Credit> getCredits() {
        return credits;
    }
    public HashMap<Requirement, ArrayList<Credit>> getRequirements() {
        return requirements;
    }
    public ArrayList<String> getNotes() {
        return notes;
    }
    public void setNotes(ArrayList<String> notes){
        this.notes = notes;
    }

    public boolean changeIsInProgress() {
        return changeInProgress;
    }
    public boolean changeMajor(Major major){
        return false;
    }
    public boolean addCredit(Credit credit){
        return false;
    }
    public boolean addCredit(Course course, Semester semester){
        return false;
    }
    public boolean removeCredit(Credit credit){
        return false;
    }
    public boolean removeCredit(Course course, Semester semester){
        return false;
    }
    public Credit getCredit(Course course, Semester semeseter){
        return null;
    }
    public ArrayList<Credit> getCredits(Course course){
        return null;
    }
    public ArrayList<Credit> getCredits(Semester semester){
        return null;
    }
    public ArrayList<Credit> getCredits(Requirement requirement){
        return null;
    }
    public boolean assignCredit(Credit credit, Requirement requirement){
        return false;
    }
    public boolean unassignCredit(Credit credit, Requirement requirement){
        return false;
    }
    public int getRequirementCreditHours(Requirement requirement){
        return 0;
    }
    public boolean addNote(String note){
        return false;
    }
    public boolean removeNote(String note){
        return false;
    }
    public boolean removeNote(int index){
        return false;
    }
    public boolean finalizeChange(){
        return false;
    }
    public boolean revertChange(){
        return false;
    }
    public void notifyCredits(){
    }
    private boolean startChange(){
        return false;
    }
    private boolean checkChange(){
        return false;
    }
    private boolean generateEightSemesterPlan(){
        return false;
    }

    public UserType getUserType(){
        return UserType.STUDENT;
    }
}