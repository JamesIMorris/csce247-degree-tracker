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

                User currentUser = degreeTracker.getCurrentUser();
                if (currentUser != null) {
                    System.out.println("Welcome, " + currentUser.getFirstName() + " " + currentUser.getLastName());

                    if (currentUser instanceof Student) {
                        Student student = (Student) currentUser;
                        System.out.println("Major: " + student.getMajor());
                        /*
                         * method to get student's year
                         * System.out.println("Year: " + student.getYear());
                         */

                        System.out.println("Choose an option:");
                        System.out.println("1. View degree progress");
                        System.out.println("2. Logout");
                        int option = scanner.nextInt();
                        scanner.nextLine();

                        switch (option) {
                            case 1:
                                viewProgress(student);
                                break;
                            case 2:
                                degreeTracker.logout();
                                break;
                            default:
                                System.out.println("Invalid option. Please choose again.");
                        }
                    } else {
                        System.out.println("User is not a student.");
                    }
                } else {
                    System.out.println("User not found.");
                }
            } else {
                System.out.println("Sorry we couldn't log you in. Do you want to try again? (yes/no)");
                String choice = scanner.nextLine();
                if (!choice.equalsIgnoreCase("yes")) {
                    System.out.println("Exiting login process.");
                    break;
                }
            }
        }
    }

    public void viewProgress(Student student) {
        System.out.println("Your Progress:");
        System.out.println("---------------");
        System.out.println("Major: " + student.getMajor());
        System.out.println("Credits:");
        for (Credit credit : student.getCredits()) {
            System.out.println("- Course: " + credit.getCourse().getCourseName() + ", Semester: "
                    + credit.getSemesterTaken() + ", Grade: " + credit.getGrade());
        }
        System.out.println("Requirements:");
        for (Map.Entry<Requirement, ArrayList<Credit>> entry : student.getRequirements().entrySet()) {
            Requirement requirement = entry.getKey();
            ArrayList<Credit> credits = entry.getValue();
            System.out.println("- " + requirement.getName() + " (" + requirement.getCategory() + "):");
            System.out.println("  Credits earned:");
            for (Credit credit : credits) {
                System.out.println("    - Course: " + credit.getCourse().getCourseName() + ", Semester: "
                        + credit.getSemesterTaken() + ", Grade: " + credit.getGrade());
            }

            ArrayList<Course> coursesNeeded = new ArrayList<>(requirement.getCourses());
            for (Credit credit : credits) {
                coursesNeeded.remove(credit.getCourse());
            }

            System.out.println("  Courses yet to take:");
            for (Course course : coursesNeeded) {
                System.out.println("    - " + course.getCourseName());
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

        if (degreeTracker.signup(username, password, firstName, lastName, email, UserType.ADVISOR)) {
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
            System.out.println("Adding an advisee...");
            System.out.print("Enter the username of the advisee: ");
            String adviseeUsername = scanner.nextLine();
            if (advisor.addAdvisee(adviseeUsername)) {
                System.out.println("Advisee added successfully.");
            } else {
                System.out.println("Failed to add advisee.");
            }
        }

        Student student = (Student) UserList.getInstance().findUser(studentid);
        if (student != null) {
            System.out.print("Enter the note: ");
            String note = scanner.nextLine();
            student.addNote(note);
            System.out.println("Note added successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public static void main(String[] args) {
        UI degreeInterface = new UI();
        degreeInterface.run();

    }

}