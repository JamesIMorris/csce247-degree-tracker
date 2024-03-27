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
        scenario1();
        scenario2();
        scanner.close();
        // degreeTracker.writeData();
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

    private void signupStudent(){

    }

    private void signupAdvisor(){

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
        String homePage = degreeTracker.studentHomePage(username);
    }

    public void scenario1() {
        boolean loggedIn = false;

        while (!loggedIn) {
            System.out.print("Enter Username for login: ");
            String loginUsername = scanner.nextLine();
            System.out.print("Enter password for login: ");
            String loginPassword = scanner.nextLine();

            if (degreeTracker.login(loginUsername, loginPassword)) {
                System.out.println(loginUsername + " is now logged in");
                loggedIn = true;

            } else {
                System.out.println("Sorry we couldn't log you in. Do you want to try again? (yes/no)");
                String choice = scanner.nextLine();
                if (!choice.equalsIgnoreCase("yes")) {
                    System.out.println("Exiting login process.");
                    break;
                }
            }

            String homePage = degreeTracker.studentHomePage(loginUsername);
            System.out.println(homePage);
            boolean continueSession = true;

            while (continueSession) {
                System.out.println("\nOptions:");
                System.out.println("1. View courses yet to take");
                System.out.println("2. Assign Courses");
                System.out.println("3. Set Application Area");
                System.out.println("4. Generate Eight-Semester Plan");
                System.out.println("5. Logout");

                System.out.print("Enter your choice: ");
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        System.out.println("Courses yet to take: ");
                        String unsatisifedRequirements = degreeTracker.studentUnsatisfiedRequirements(loginUsername);
                        System.out.println(unsatisifedRequirements);
                        break;
                    case 2:

                        System.out.println("\nPossible credits for requirements:");
                        String possibleCredits = degreeTracker.studentPossibleRequirementCredits(loginUsername, "GFL");
                        System.out.println(possibleCredits);

                        System.out.print("\nEnter the course ID for the course you would like to take: ");
                        String courseID = scanner.nextLine();

                        System.out.println("\nSelect semester for the course (Spring, Fall, Winter, Summer): ");
                        String semesterTaken = scanner.nextLine();

                        if (!(semesterTaken.equalsIgnoreCase("Spring") || semesterTaken.equalsIgnoreCase("Fall")
                                || semesterTaken.equalsIgnoreCase("Winter")
                                || semesterTaken.equalsIgnoreCase("Summer"))) {
                            System.out.println("Invalid semester choice. Exiting scenario.");
                            return;
                        }

                        boolean success = degreeTracker.studentAssignCourse(loginUsername, courseID, semesterTaken,
                                "GFL");
                        if (success) {
                            System.out.println("Course " + courseID + " successfully assigned.");
                        } else {
                            System.out.println("Failed to assign course " + courseID + " .");
                        }
                        break;

                    case 3:

                        System.out.println("\nApplication Area Topics:");
                        System.out.println("1. Science");
                        System.out.println("2. Math");
                        System.out.println("3. Digital Design");
                        System.out.println("4. Robotics");
                        System.out.println("5. Speech");

                        System.out.print("Enter the number of your choice: ");
                        int applicationOption = scanner.nextInt();
                        scanner.nextLine();

                        String applicationArea;
                        switch (applicationOption) {
                            case 1:
                                applicationArea = "Science";
                                break;
                            case 2:
                                applicationArea = "Math";
                                break;
                            case 3:
                                applicationArea = "Digital Design";
                                break;
                            case 4:
                                applicationArea = "Robotics";
                                break;
                            case 5:
                                applicationArea = "Speech";
                                break;
                            default:
                                System.out.println("Invalid option selected. Please select a valid application area.");
                                return;
                        }

                        degreeTracker.setApplicationArea(loginUsername, applicationArea);
                        System.out.println("Application area set to: " + applicationArea);

                        System.out.println("\nSelect courses for Digital Design:");

                        // courses here

                        System.out.println(
                                "\nSelect courses for Digital Design (Enter course ID, semester taken, and requirement, or type 'done' to finish):");
                        ArrayList<String[]> selectedCourses = new ArrayList<>();
                        String[] courseInfo;
                        while (true) {
                            System.out.print("Enter course ID: ");
                            String selectedCourseID = scanner.nextLine();
                            if (selectedCourseID.equalsIgnoreCase("done")) {
                                break;
                            }
                            System.out.print("Enter semester taken (e.g., Fall, Spring, Summer, Winter): ");
                            String selectedSemesterTaken = scanner.nextLine();
                            System.out.print("Enter requirement: ");
                            String selectedRequirement = scanner.nextLine();
                            courseInfo = new String[] { selectedCourseID, selectedSemesterTaken, selectedRequirement };
                            selectedCourses.add(courseInfo);
                        }

                        for (String[] info : selectedCourses) {
                            boolean successCourse = degreeTracker.studentAssignCourse(loginUsername, info[0], info[1],
                                    info[2]);
                            if (successCourse) {
                                System.out.println("Course " + info[0] + " successfully assigned for " + info[2]);
                            } else {
                                System.out.println("Failed to assign course " + info[0] + " for " + info[2]);
                            }
                        }
                        break;
                    case 4:
                        System.out.println("\nGenerating 8 semester plan...");
                        System.out.print("Enter the file name to save the plan: ");
                        String fileName = scanner.nextLine();
                        boolean generationSuccess = degreeTracker.eightSemesterPlanToTextFile(fileName, loginUsername);
                        if (generationSuccess) {
                            System.out.println("Eight semester plan generated and saved to file: " + fileName + ".txt");
                        } else {
                            System.out.println("Failed to generate the eight semester plan.");
                        }
                        break;
                    case 5:
                        degreeTracker.logout();
                        continueSession = false;
                        return;
                    default:
                        System.out.println("Invalid option.  Please select a valid option");
                }

            }
        }
    }

    public void scenario2() {
        System.out.println("Creating a new advisor account...");

        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        if (degreeTracker.advisorSignup(username, password, firstName, lastName, email)) {
            System.out.println("Advisor account created successfully.");
        } else {
            System.out.println("Failed to create an advisor account.");
            return;
        }

        boolean loggedIn = false;

        while (!loggedIn) {
            System.out.print("Enter Username for login: ");
            String loginUsername = scanner.nextLine();
            System.out.print("Enter password for login: ");
            String loginPassword = scanner.nextLine();

            if (degreeTracker.login(loginUsername, loginPassword)) {
                System.out.println(loginUsername + " is now logged in");
                loggedIn = true;
            } else {
                System.out.println("Sorry we couldn't log you in. Do you want to try again? (yes/no)");
                String choice = scanner.nextLine();
                if (!choice.equalsIgnoreCase("yes")) {
                    System.out.println("Exiting login process.");
                    break;
                }
            }
        }

        if (degreeTracker.getCurrentUser().getUserType() == UserType.ADVISOR) {
            Advisor advisor = (Advisor) degreeTracker.getCurrentUser();
            boolean continueSession = true;

            degreeTracker.adivsorHomePage(username);

            while (continueSession) {
                System.out.println("\nOptions:");
                System.out.println("1. Add an advisee");
                System.out.println("2. View another student's progress");
                System.out.println("3. Add a note to the student's account");
                System.out.println("4. Logout");

                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        System.out.print("Enter Student ID: ");
                        String studentID = scanner.nextLine();
                        String studentUsername = degreeTracker.findStudentFromID(studentID);
                        if (studentUsername != null) {
                            Student studentUser = (Student) degreeTracker.getUserList().findUser(studentUsername);
                            advisor.addAdvisee(studentUser);
                            System.out.println("Student added as an advisee.");
                        } else {
                            System.out.println("Student not found.");
                        }
                        break;
                    case 2:
                        System.out.print("Enter Student ID: ");
                        String studentIDToView = scanner.nextLine();
                        String studentUsernameToView = degreeTracker.findStudentFromID(studentIDToView);
                        if (studentUsernameToView != null) {
                            System.out.println("Viewing Student's current progress:");
                            String studentHomePage = degreeTracker.studentHomePage(studentUsernameToView);
                            System.out.println(studentHomePage);
                        } else {
                            System.out.println("Student not found.");
                        }
                        break;
                    case 3:
                        System.out.print("Enter Student ID: ");
                        String studentIDForNote = scanner.nextLine();
                        String studentUsernameForNote = degreeTracker.findStudentFromID(studentIDForNote);
                        if (studentUsernameForNote != null) {
                            System.out.println("Enter note: ");
                            String note = scanner.nextLine();
                            degreeTracker.addNote(studentUsernameForNote, note);
                            System.out.println("Note added successfully.");
                        } else {
                            System.out.println("Student not found.");
                        }
                        break;
                    case 4:
                        degreeTracker.logout();
                        continueSession = false;
                        break;
                    default:
                        System.out.println("Invalid option.  Please select a valid option");
                }
            }
        }
    }

    public static void main(String[] args) {
        UI degreeInterface = new UI();
        degreeInterface.run();

    }

}