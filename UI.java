import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class UI {
    private DegreeTracker degreeTracker;
    private Scanner scanner;

    UI() {
        degreeTracker = DegreeTracker.getInstance();
        scanner = new Scanner(System.in);
    }

    public void run() {
        scenario1();
        scenario2();

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

            System.out.println("Courses yet to take: ");
            String unsatisifedRequirements = degreeTracker.studentUnsatisfiedRequirements(loginUsername);
            System.out.println(unsatisifedRequirements);

            System.out.println("\nPossible credits for the following requirements:");
            String[] requirements = unsatisifedRequirements.split(","); // Split by comma assuming requirements are
                                                                        // separated by comma
            for (int i = 0; i < requirements.length; i++) {
                String possibleCredits = degreeTracker.studentPossibleRequirementCredits(loginUsername,
                        requirements[i]);
                System.out.println((i + 1) + ". " + requirements[i] + ": " + possibleCredits);
            }

            System.out.print("\nEnter the course ID you want to select: ");
            String courseID = scanner.nextLine();

            System.out.println("\nSelect semester (Spring, Fall, Winter, Summer): ");
            String semesterTaken = scanner.nextLine();

            if (!(semesterTaken.equalsIgnoreCase("Spring") || semesterTaken.equalsIgnoreCase("Fall")
                    || semesterTaken.equalsIgnoreCase("Winter") || semesterTaken.equalsIgnoreCase("Summer"))) {
                System.out.println("Invalid semester choice.");
                return;
            }

            boolean success = degreeTracker.studentAssignCourse(loginUsername, courseID, semesterTaken,
                    requirements[0]); // Assuming the first requirement for simplicity
            if (success) {
                System.out.println("Course " + courseID + " successfully assigned for " + requirements[0]); // Assuming
                                                                                                            // the first
                                                                                                            // requirement
                                                                                                            // for
                                                                                                            // simplicity
            } else {
                System.out.println("Failed to assign course " + courseID + " for " + requirements[0]); // Assuming the
                                                                                                       // first
                                                                                                       // requirement
                                                                                                       // for simplicity
            }

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
                boolean successCourse = degreeTracker.studentAssignCourse(loginUsername, info[0], info[1], info[2]);
                if (successCourse) {
                    System.out.println("Course " + info[0] + " successfully assigned for " + info[2]);
                } else {
                    System.out.println("Failed to assign course " + info[0] + " for " + info[2]);
                }
            }

            // eight semester plan

            degreeTracker.logout();
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
            degreeTracker.adivsorHomePage(username);
            degreeTracker.findStudentFromID(uscID);

            degreeTracker.studentUnsatisfiedRequirements(username);

            degreeTracker.logout();
        }
    }

    public static void main(String[] args) {
        UI degreeInterface = new UI();
        degreeInterface.run();

    }

}