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
        System.out.println();
        System.out.println("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (!degreeTracker.login(username, password)) {
            System.out.println("Invalid username or password.");
            return;
        }
        System.out.println(username + "is now logged in");

        User currentUser = degreeTracker.getCurrentUser();

        if (currentUser != null) {
            System.out.println(currentUser.getFirstName() + " " + currentUser.getLastName());

            if (currentUser instanceof Student) {

                Student student = (Student) currentUser;

                System.out.println("Major: " + student.getMajor());
            } else {

                System.out.println("User is not a student.");
            }
        } else {
            System.out.println("User not found.");
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

        if (!degreeTracker.login("NewUser123", "NewPassword@123")) {
            System.out.println("Sorry we couldn't login.");
        } else {
            System.out.println("NewUser123 is now logged in");
        }

        System.out.println("Would you like to logout? (Yes/No)");
        String logout = scanner.nextLine();

        if (logout.equalsIgnoreCase("yes")) {
            degreeTracker.logout();
        } else if (logout.equalsIgnoreCase("no")) {
            scenario2();
        } else {
            System.out.println("Invalid choice, must enter yes or no");

            scenario2();

        }

    }

    public static void main(String[] args) {
        UI degreeInterface = new UI();
        degreeInterface.run();

    }

}