import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    private static UserList userList;

    public static void main(String[] args) {
        userList = UserList.getInstance();

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("1. Login");
            System.out.println("2. Create Account");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    login(scanner);
                    break;
                case 2:
                    createAccount(scanner);
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        DataWriter dataWriter = new DataWriter();
        dataWriter.saveUsers(userList.getUsers());

        scanner.close();
    }

    private static boolean login(Scanner scanner) {
        boolean loggedIn = false;

        while (!loggedIn) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            User user = userList.authenticateUser(username, password);

            if (user != null) {
                System.out.println("Login successful!");

                UserType userType = user.getUserType();

                if (userType == UserType.STUDENT) {

                    ((Student) user).generateEightSemesterPlan();

                } else if (userType == UserType.ADVISOR) {

                } else if (userType == UserType.ADMIN) {

                }

                loggedIn = true; // Exit the login loop
            } else {
                System.out.println("Login failed. Invalid username or password.");
            }
        }

        // continue the main loop after logout
        return false;
    }

    private static void createAccount(Scanner scanner) {
        System.out.println("Select the type of account to create:");
        System.out.println("1. Administrator");
        System.out.println("2. Student");
        System.out.println("3. Advisor");
        System.out.print("Enter your choice: ");

        int accountTypeChoice = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        UserType type;
        switch (accountTypeChoice) {
            case 1:
                type = UserType.ADMIN;
                break;
            case 2:
                type = UserType.STUDENT;
                break;
            case 3:
                type = UserType.ADVISOR;
                break;
            default:
                System.out.println("Invalid choice. Account creation canceled.");
                return;
        }
    }

    private static void logout() {
        System.out.println("Logging out...");

    }
}
