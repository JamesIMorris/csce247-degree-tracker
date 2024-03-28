import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    private DegreeTracker degreeTracker;
    private Scanner scanner;

    UI() {
        degreeTracker = DegreeTracker.getInstance();
        scanner = new Scanner(System.in);
    }

    public void run() {
        welcome();
        landingPage();
        scanner.close();
        logout();
    }

    private void welcome(){
        System.out.println("Welcome to the No Name Degree Tracker");
    }

    private void landingPage(){
        System.out.println( "\n"
                            + "1. Login\n"
                            + "2. Signup as Student\n"
                            + "3. Signup as Advisor");
        int lauchDecision = scanner.nextInt();
        scanner.nextLine();
        switch (lauchDecision) {
        case 1:
            login();
            break;
        case 2:
            signupStudent();
            break;
        case 3:
            signupAdvisor();
            break;
        default:
            System.out.println("That was not an option");
            landingPage();
            break;
        }
    }

    private void logout(){
        degreeTracker.logout();
    }

    private void signupStudent(){
        String[] usernameAndPassword = getUsernameAndPassword();
    }

    private void signupAdvisor(){
        String[] usernameAndPassword = getUsernameAndPassword();
        String username = usernameAndPassword[0];
        String password = usernameAndPassword[1];
        String[] additionalInfo = getAdditionalAdvisorInfo();
        String firstName = additionalInfo[0];
        String lastName = additionalInfo[1];
        String email = additionalInfo[2];
        degreeTracker.advisorSignup(username, password, firstName, lastName, email);
        degreeTracker.login(username, password);
        adivsorHomePage(username);
    }

    private String[] getUsernameAndPassword(){
        String[] usernameAndPassword = new String[2];
        System.out.print("Username: ");
        usernameAndPassword[0] = scanner.nextLine();
        System.out.print("Password: ");
        usernameAndPassword[1] = scanner.nextLine();
        if(degreeTracker.checkSignup(usernameAndPassword[0], usernameAndPassword[1]))
            return usernameAndPassword;
        System.out.println(degreeTracker.popError());
        return getUsernameAndPassword();
    }

    private String[] getAdditionalAdvisorInfo(){
        String[] additionalInfo = new String[3];
        System.out.print("First Name: ");
        additionalInfo[0] = scanner.nextLine();
        System.out.print("Last Name: ");
        additionalInfo[1] = scanner.nextLine();
        System.out.print("Email: ");
        additionalInfo[2] = scanner.nextLine();
        if(degreeTracker.checkAdditionalInfo(additionalInfo))
            return additionalInfo;
        System.out.println(degreeTracker.popError());
        return getAdditionalAdvisorInfo();
    }
 
    private void login(){
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        if(!degreeTracker.login(username, password)){
            System.out.println(degreeTracker.popError());
            login();
        }
        else{
            homePage(username);
        }
    }

    private void homePage(String username){
        System.out.println(username + " is now logged in!\n");
        // if(degreeTracker.isStudent(username))
            studentHomePage(username);
        // else
        //     adivsorHomePage(username);
    }

    private void studentHomePage(String username){
        System.out.println(degreeTracker.studentHomePage(username));

        System.out.println("Actions:\n"
                            + "1. View Unmet Requiremnts\n"
                            + "2. Set Application Area\n"
                            + "3. Export Eigh-Semester Plan"
                            + "4. Logout");
        int action = scanner.nextInt();
        switch (action) {
        case 1:
            unemetRequirements(username);
            break;
        case 2:
            applicationArea(username);
            break;
        case 3:
            export8SPlan(username);
            break;
        case 4:
            return;
        default:
                System.out.println("That wasn't an option");
                break;
        }
        studentHomePage(username);
    }

    private void unemetRequirements(String username){
        System.out.println(degreeTracker.studentUnsatisfiedRequirements(username));
        System.out.println("Please select a requirement to fill using its abbreviation\n"
                            + "(Ex: GFL) or enter nothing to return home");
        String requirement = scanner.nextLine();
        if(requirement == "")
            return;
        if(degreeTracker.studentHasRequirement(username, requirement))
            applicableRequirementCourses(username, requirement);
        else{
            System.out.println("That is not an available requirement");
            unemetRequirements(username);
        }
    }

    private void applicationArea(String username){
        System.out.println(degreeTracker.possibleApplicationAreas(username));
        System.out.println("Please enter the application area you would wish to take\n"
                            + "or enter nothing to return home");
        String applicationArea = scanner.nextLine();
        if(applicationArea == "")
            return;
        if(degreeTracker.setApplicationArea(username, applicationArea))
            applicableRequirementCourses(username, "Application Area"); //TODO is it "Application Area"?
        else{
            System.out.println("That is not an available application area");
            applicationArea(username);
        }
    }

    public void applicableRequirementCourses(String username, String requirement){
        System.out.println(degreeTracker.studentPossibleRequirementCredits(username, requirement));
        System.out.println("Please enter the course code for the course you would like to take\n"
                            + "(Ex: MATH141) or enter nothing to return home");
        String courseID = scanner.nextLine();
        if(courseID == "")
            return;
        if(!degreeTracker.courseExists(courseID)){
            System.out.println("This is not a valid course code");
            applicableRequirementCourses(username, requirement);
        }
        else{
            selectSemesterForCourse(username, requirement, courseID);
        }
    }

    public void selectSemesterForCourse(String username, String requirement, String courseID){
        System.out.println("Please enter when you would wish to take this course using the semester abbreviation\n"
                            + "(EX: FA22 or SP23)");
        String semester = scanner.nextLine();
        if(!degreeTracker.studentAssignCourse(username, courseID, semester, requirement)){
            System.out.println("This is not a valid semester abbreviation");
            selectSemesterForCourse(username, requirement, courseID);
        }
        else {
            System.out.println(courseID + " added to " + semester + " for " + requirement);
        }
    }


    private void export8SPlan(String username){
        System.out.println("What would you like to name the text file for your" 
                            + "Eight Semester Plan\n"
                            + "(Ommit the .txt) or enter nothing to return home");
        String fileName = scanner.nextLine();
        if(fileName == "")
            return;
        if(!degreeTracker.eightSemesterPlanToTextFile(fileName, username)){
            System.out.println("File didn't print\n" + degreeTracker.popError());
            export8SPlan(username);
        }
        else{
            System.out.println("Eight Semester Plan exported to " + fileName + ".txt");
        }
    }

    private void adivsorHomePage(String username){
        degreeTracker.adivsorHomePage(username);
        System.out.println("\nOptions:"
                            + "1. Add an advisee"
                            + "2. View a student's progress"
                            + "4. Logout");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
        case 1:
            addAdvisee(username);
            break;
        case 2:
            advisorFindStudentPage(username);
            break;
        case 3:
            return;
        default:
            System.out.println("That was not a valid input");
            adivsorHomePage(username);
            break;
        }
    }

    private void addAdvisee(String advisorUsername){
        System.out.println("Please input the USC ID of the student you wish"
                            + " to add to your advisee list."
                            + "(Ex: P04937659) or enter nothing to return home");
        String uscID = scanner.nextLine();
        if(uscID == "")
            return;
        String studentUsername = degreeTracker.findStudentFromID(uscID);
        if(studentUsername == null){
            System.out.println(degreeTracker.popError());
            addAdvisee(advisorUsername);
        }
        else{
            degreeTracker.addAdvisee(advisorUsername, studentUsername);
            System.out.println(studentUsername + " was added as an advisee");
        }
    }

    private void advisorFindStudentPage(String advisorUsername){
        System.out.println("Please enter the username of of the student that "
                            + "you wish to view the progress of\n"
                            + "or enter nothing to return home");
        String studentUsername = scanner.nextLine();
        if(studentUsername == "")
            return;
        if(degreeTracker.studentHomePage(studentUsername) == null){
            System.out.println(degreeTracker.popError());
            advisorFindStudentPage(advisorUsername);
        }
        else{
            System.out.println("Loading " + studentUsername + "'s page");
            advisorStudentPage(advisorUsername, studentUsername);
        }
    }

    private void advisorStudentPage(String advisorUsername, String studentUsername){
        System.out.println("***** STUDENT PAGE *****\n");
        System.out.println(degreeTracker.studentHomePage(studentUsername));
        System.out.println("\n************************\n");
        System.out.println("1. Add Note"
                            + "2. Return home");
        int choice = scanner.nextInt();
        switch (choice) {
        case 1:
            advisorAddNote(advisorUsername, studentUsername);
            advisorStudentPage(advisorUsername, studentUsername);
            break;
        case 2:
            return;
        default:
            System.out.println("That was not a valid input");
            advisorStudentPage(advisorUsername, studentUsername);
            break;
        }
    }

    private void advisorAddNote(String advisorUsername, String studentUsername){
        System.out.println("Enter nothing to return to the students page\n"
                            + "Or type a note to add it to the students profile: ");
        String note = scanner.nextLine();
        if(note == "")
            return;
        note += "\n-" + advisorUsername;
        if(!degreeTracker.addNote(studentUsername, note))
            System.out.println(degreeTracker.popError());
        else
            System.out.println("Note added to " + studentUsername + "'s profile");
    }

    public static void main(String[] args) {
        UI degreeInterface = new UI();
        degreeInterface.run();

    }

}