import java.util.ArrayList;
import java.util.HashMap;

public class Student extends User {
    private Major major;
    private ArrayList<Credit> credits;
    private HashMap<Requirement, ArrayList<Credit>> requirements;
    private ArrayList<String> notes;
    private boolean changeInProgress;
    private Major backUpMajor;
    private ArrayList<Credit> backupCredits;
    private HashMap<Requirement, ArrayList<Credit>> backupRequirements;

    public Student(String username, String password, String firstName, String lastName, String email) {
        super(username, password, firstName, lastName, email);
        this.credits = new ArrayList<Credit>();
        this.requirements = new HashMap<Requirement, ArrayList<Credit>>();
        this.notes = new ArrayList<String>();
        this.changeInProgress = true;
    }

    public Student(String username, String password, String firstName, String lastName, String email, Major major) {
        super(username, password, firstName, lastName, email);
        this.major = major;
        this.credits = new ArrayList<Credit>();
        this.requirements = new HashMap<Requirement, ArrayList<Credit>>();
        this.notes = new ArrayList<String>();
        this.changeInProgress = true;
    }

    public Student(String username, String password, String firstName, String lastName, String email, Major major,
            ArrayList<Credit> credits) {
        super(username, password, firstName, lastName, email);
        this.major = major;
        this.credits = credits;
        this.requirements = new HashMap<Requirement, ArrayList<Credit>>();
        this.notes = new ArrayList<String>();
        this.changeInProgress = true;
    }

    public Student(String username, String password, String firstName, String lastName, String email, Major major,
            ArrayList<Credit> credits, HashMap<Requirement, ArrayList<Credit>> requirements, ArrayList<String> notes) {
        super(username, password, firstName, lastName, email);
        this.major = major;
        this.credits = credits;
        this.requirements = requirements;
        this.notes = notes;
        this.changeInProgress = false;
    }

    public Student(String username, Major major, ArrayList<Credit> credits,
            HashMap<Requirement, ArrayList<Credit>> requirements, ArrayList<String> notes) {
        super(username);
        this.major = major;
        this.credits = credits;
        this.requirements = requirements;
        this.notes = notes;
        notifyForAllRequirements();
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

    public void setNotes(ArrayList<String> notes) {
        this.notes = notes;
    }

    public boolean changeIsInProgress() {
        return changeInProgress;
    }

    public void changeMajor(Major major) {
        startChange();
        this.major = major;
        generateEightSemesterPlan();
    }

    public boolean addCredit(Credit credit) {
        if (credit == null || credits.contains(credit))
            return false;
        credits.add(credit);
        return true;
    }

    public boolean addCredit(Course course, Semester semester) {
        return false;
    }

    public boolean removeCredit(Credit credit) {
        return false;
    }

    public boolean removeCredit(Course course, Semester semester) {
        return false;
    }

    public Credit getCredit(Course course, Semester semeseter) {
        return null;
    }

    public ArrayList<Credit> getCredits(Course course) {
        return null;
    }

    public ArrayList<Credit> getCredits(Semester semester) {
        return null;
    }

    public ArrayList<Credit> getCredits(Requirement requirement) {
        return null;
    }

    public boolean assignCredit(Credit credit, Requirement requirement) {
        return false;
    }

    public boolean unassignCredit(Credit credit, Requirement requirement) {
        return false;
    }

    public int getRequirementCreditHours(Requirement requirement) {
        return 0;
    }

    public boolean addNote(String note) {
        return false;
    }

    public boolean removeNote(String note) {
        return false;
    }

    public boolean removeNote(int index) {
        return false;
    }

    public boolean finalizeChange() {
        return false;
    }

    public boolean revertChange() {
        return false;
    }

    public void notifyForAllRequirements() {
        ArrayList<Requirement> majoRequirements = major.getRequirements();
        for (Requirement majoRequirement : majoRequirements) {
            notifyCredits(majoRequirement);
        }
    }

    public void notifyCredits(Requirement requirement) {
        int requiredHours = requirement.getCreditHoursRequired();
        ArrayList<Credit> requirementCredits = requirements.get(requirement);
        int totalHours = 0;
        for (Credit credit : requirementCredits) {
            totalHours += credit.getCreditHours();
        }
        if (totalHours >= requiredHours)
            notifyCredits(requirement, false);
        else
            notifyCredits(requirement, true);
    }

    public void notifyCredits(Requirement requirement, boolean open) {
        for (Credit credit : credits) {
            credit.update(requirement, open);
        }
    }

    private void startChange() {
        this.changeInProgress = true;
        this.backUpMajor = this.major;
        this.backupCredits = this.credits;
        this.backupRequirements = this.requirements;
    }

    private boolean checkChange() {
        return false;
    }

    private boolean generateEightSemesterPlan() {
        return false;
    }

    public UserType getUserType() {
        return UserType.STUDENT;
    }

}