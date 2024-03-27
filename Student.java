import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Student extends User{
    private String uscID;
    private Major major;
    private String applicationArea;
    private ArrayList<Credit> credits;
    private HashMap<Requirement, ArrayList<Credit>> requirements;
    private ArrayList<String> notes;
    private boolean changeInProgress;
    private Major backUpMajor;
    private ArrayList<Credit> backupCredits;
    private HashMap<Requirement, ArrayList<Credit>> backupRequirements;

    public Student(String username, String password, String firstName, String lastName, String email, String uscID){
        super(username, password, firstName, lastName, email);
        this.credits = new ArrayList<Credit>();
        this.requirements = new HashMap<Requirement, ArrayList<Credit>>();
        this.notes = new ArrayList<String>();
        this.uscID = uscID;
        this.applicationArea = "Undecided";
        this.changeInProgress = true; 
    }
    public Student(String username, String password, String firstName, String lastName, String email, String uscID, Major major, ArrayList<Credit> credits){
        super(username, password, firstName, lastName, email);
        this.uscID = uscID;
        this.major = major;
        this.credits = credits;
        this.requirements = new HashMap<Requirement, ArrayList<Credit>>();
        this.notes = new ArrayList<String>();
        this.applicationArea = "Undecided";
        this.changeInProgress = true;
        populateHashMap();
    }
    public Student(String username, String password, String firstName, String lastName, String email, String uscID, Major major, String applicationArea, ArrayList<Credit> credits, ArrayList<String> notes){
        super(username, password, firstName, lastName, email);
        this.uscID = uscID;
        this.major = major;
        this.applicationArea = applicationArea;
        this.credits = credits;
        this.requirements = new HashMap<Requirement, ArrayList<Credit>>();
        this.notes = notes;
        this.changeInProgress = false;
        populateHashMap();
        populateBackups();
    }
    public Student(String username, String password, String firstName, String lastName, String email, String uscID, Major major, String applicationArea, ArrayList<Credit> credits, HashMap<Requirement, ArrayList<Credit>> requirements, ArrayList<String> notes){
        super(username, password, firstName, lastName, email);
        this.uscID = uscID;
        this.major = major;
        this.applicationArea = applicationArea;
        this.credits = credits;
        this.requirements = requirements;
        this.notes = notes;
        this.changeInProgress = false;
        populateHashMap();
        populateBackups();
    }
    public Student(String username, String uscID, Major major, String applicationArea, ArrayList<Credit> credits, HashMap<Requirement, ArrayList<Credit>> requirements, ArrayList<String> notes){
        super(username);
        this.uscID = uscID;
        this.major = major;
        this.applicationArea = applicationArea;
        this.credits = credits;
        this.requirements = requirements;
        this.notes = notes;
        populateHashMap();
        notifyForAllRequirements();
        populateBackups();
    }

    private void populateHashMap(){
        for(Requirement requirement : major.getRequirements())
            if(requirements.get(requirement) == null)
                requirements.put(requirement, new ArrayList<Credit>());
    }

    private void populateBackups() {
        this.backUpMajor = major;
        this.backupCredits = credits;
        this.backupRequirements = requirements;
    }

    public String getUscID(){
        return this.uscID;
    }
    public void setUscID(String uscID){
        if(uscID == null)
            return;
        this.uscID = uscID;
    }

    public String getApplicationArea(){
        return this.applicationArea;
    }
    public void setApplicatioNArea(String applicationArea){
        if(applicationArea == null)
            return;
        this.applicationArea = applicationArea;
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
        for (Credit credit : credits) {
            if (credit.getCourse().equals(course) && credit.getSemesterTaken().equals(semester))
                return false;
        }
        credits.add(new Credit(course, semester));
        return true;
    }

    public boolean removeCredit(Credit credit) {
        return credits.remove(credit);
    }

    public boolean removeCredit(Course course, Semester semester) {
        // TODO
        return false;
    }

    public Credit getCredit(Course course, Semester semester) {
        for (Credit credit : credits) {
            if (credit.getCourse().equals(course) && credit.getSemesterTaken().equals(semester))
                return credit;
        }
        return null;
    }

    public Credit getCreditFromID(UUID id){
        for(Credit credit : credits)
            if(credit.getID().equals(id))
                return credit;
        return null;
    }

    public ArrayList<Credit> getCredits(Course course) {
        ArrayList<Credit> returnList = new ArrayList<Credit>();
        for (Credit credit : credits) {
            if (credit.getCourse().equals(course))
                returnList.add(credit);
        }
        return returnList;
    }

    public void setRequirements(HashMap<Requirement, ArrayList<Credit>> requirements){
        this.requirements = requirements;
        populateHashMap();
    }

    public ArrayList<Credit> getCredits(Semester semester) {
        ArrayList<Credit> returnList = new ArrayList<Credit>();
        for (Credit credit : credits) {
            if (credit.getSemesterTaken().equals(semester))
                returnList.add(credit);
        }
        return returnList;
    }

    public ArrayList<Credit> getCredits(Requirement requirement) {
        return requirements.get(requirement);
    }

    public boolean assignCredit(Credit credit, Requirement requirement) {
        if (credit == null || requirement == null)
            return false;
        boolean open = checkRequierment(requirement);
        if (open)
            requirements.get(requirement).add(credit);
        checkRequierment(requirement);
        return open;
    }

    public boolean unassignCredit(Credit credit, Requirement requirement) {
        if (credit == null || requirement == null)
            return false;
        if (!requirements.get(requirement).contains(credit))
            return false;
        requirements.get(requirement).remove(credit);
        checkRequierment(requirement);
        return true;
    }

    public boolean checkRequierment(Requirement requirement) {
        int requiredHours = requirement.getCreditHoursRequired();
        int totalHours = 0;
        for (Credit credit : requirements.get(requirement)) {
            totalHours += credit.getCreditHours();
        }
        boolean open = (totalHours < requiredHours);
        notifyCredits(requirement, open);
        return open;
    }

    public int getRequirementCreditHours(Requirement requirement) {
        ArrayList<Credit> creditList = requirements.get(requirement);
        int totalHours = 0;
        for (Credit credit : creditList) {
            totalHours += credit.getCreditHours();
        }
        return totalHours;
    }

    public boolean addNote(String note) {
        if (note == null)
            return false;
        return notes.add(note);
    }

    public boolean removeNote(String note) {
        if (note == null)
            return false;
        return notes.remove(note);
    }

    public boolean removeNote(int index) {
        if (index >= notes.size())
            return false;
        notes.remove(index);
        return true;
    }

    public boolean finalizeChange() {
        if (!checkChange())
            return false;
        changeInProgress = false;
        populateBackups();
        return true;
    }

    public void revertChange() {
        this.major = backUpMajor;
        this.credits = backupCredits;
        this.requirements = backupRequirements;
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
        //TODO
        // for (Credit credit : credits) {

        // }
        return false;
    }
    public boolean coreqSatisfied(ArrayList<Course> courses, int requiredHours){
        return false;
    }

    private boolean generateEightSemesterPlan() {
        //TODO
        // for (Requirement requirement : major.getRequirements()) {
            
        // }
        return false;
    }

    public boolean assignCredit(String courseID, String semesterTaken, String requirement){
        Credit creditToAssign = null;
        for(Credit credit : credits){
            if(credit.getCourseID().equalsIgnoreCase(courseID) && credit.getSemesterTaken().equals(Semester.fromString(semesterTaken))){
                creditToAssign = credit;
            }
        }
        Requirement requirementToAssign = null;
        for(Requirement majorRequirement : major.getRequirements()){
            if(majorRequirement.getName().equalsIgnoreCase(requirement)){
                requirementToAssign = majorRequirement;
            }
        }
        if(creditToAssign == null || requirementToAssign == null)
            return false;
        requirements.get(requirementToAssign).add(creditToAssign);
        return true;
    }

    public int totalCreditHoursComplete(){
        int totalHours = 0;
        for(Credit credit : credits)
            if(Status.getStatus(credit).equals(Status.COMPLETE))
                totalHours += credit.getCreditHours();
        return totalHours;
    }

    public String getYear(){
        int totalHours = totalCreditHoursComplete();
        if(totalHours < 30)
            return "Freshman";
        if(totalHours < 60)
            return "Sophmore";
        if(totalHours < 90)
            return "Junior";
        return "Senior";
    }

    public UserType getUserType() {
        return UserType.STUDENT;
    }

}