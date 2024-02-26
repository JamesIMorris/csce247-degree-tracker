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
        dataWriter.saveUsers();

        scanner.close();
    }

    private static void login(Scanner scanner) {
        boolean loggedIn = false;

        while (!loggedIn) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            User user = findUsername(username);

            if (user != null && user.passwordMathes(password)) {
                System.out.println("Login successful!");

                UserType userType = user.getUserType();

                if (userType == UserType.STUDENT) {
                    loadStudentPage((Student) user, scanner);
                } else if (userType == UserType.ADVISOR) {
                    loadAdvisorPage((Advisor) user, scanner);
                } else if (userType == UserType.ADMIN) {
                    loadAdminPage((Admin) user, scanner);
                }

                loggedIn = true; // Exit the login loop
                LogOut(scanner, user);
            } else {
                System.out.println("Login failed. Invalid username or password.");
            }
        }
    }

    private static User findUsername(String username) {
        for (User user : userList.users) {
            if (user.username.equals(username)) {
                return user;
            }
        }

        return null;
    }

    private static void LogOut(Scanner scanner, User user) {
        System.out.print("Do you want to logout? (yes/no): ");
        String logoutChoice = scanner.nextLine().toLowerCase();

        if (logoutChoice.equals("yes")) {
            System.out.println("Logout successful!");
        } else {

            UserType userType = user.getUserType();

            if (userType == UserType.STUDENT) {
                loadStudentPage((Student) user, scanner);
            } else if (userType == UserType.ADVISOR) {
                loadAdvisorPage((Advisor) user, scanner);
            } else if (userType == UserType.ADMIN) {
                loadAdminPage((Admin) user, scanner);
            }
        }
    }

    private static void loadStudentPage(Student student, Scanner scanner) {
        System.out.println("Welcome, " + student.getFirstName() + " " + student.getLastName());

        System.out.println("1. Generate Eight Semester Plan");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                displayEightSemesterPlan(student);
                break;
            default:
                System.out.println("Invalid choice. Returning to main menu.");
        }
    }

    private static void loadAdvisorPage(Advisor advisor, Scanner scanner) {
        System.out.println("Welcome, " + advisor.getFirstName() + " " + advisor.getLastName());

        System.out.println("1. View Advisee Information");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                displayAdviseeInformation(advisor.getAdvisees());
                break;

            default:
                System.out.println("Invalid choice. Returning to main menu.");
        }
    }

    private static void displayAdviseeInformation(ArrayList<Student> advisees) {
        Scanner scanner;
        if (advisees.isEmpty()) {
            System.out.println("No advisees to display.");
        } else {
            System.out.println("Advisee Information:");

            for (Student student : advisees) {
                System.out.println("Username: " + student.getUsername());
                System.out.println("Name: " + student.getFirstName() + " " + student.getLastName());
                System.out.println("Email: " + student.getEmail());
                System.out.println("Major: " + student.getMajor());

                System.out.println("Notes:");
                for (String note : student.notes) {
                    System.out.println("\t" + note);
                }

                System.out.print("Do you want to add a note? (yes/no): ");
                String addNoteResponse = scanner.nextLine().toLowerCase();

                if (addNoteResponse.equals("yes")) {
                    System.out.print("Enter the note: ");
                    String newNote = scanner.nextLine();
                    student.addNote(newNote);
                    System.out.println("Note added successfully.");
                }

                System.out.println("---------------");
            }
        }
    }

    private static void loadAdminPage(Admin admin, Scanner scanner) {
        System.out.println("Welcome, " + admin.getFirstName() + " " + admin.getLastName());

        System.out.println("1. Manage Courses");
        System.out.println("2. Manage Majors");
        System.out.println("3. Manage Users");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                
                break;
                 2:
                
                break;
            default:
                System.out.println("Invalid choice. Returning to main menu.");
        }
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

        if (!userList.usernameAvailable(username)) {
            System.out.println("Username is not available. Please choose another username.");
            return;
        }
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

        User newUser;
        if (type == UserType.ADMIN) {
            newUser = new Admin(username, password, firstName, lastName, email);
        } else if (type == UserType.STUDENT) {
            newUser = new Student(username, password, firstName, lastName, email);
        } else {
            newUser = new Advisor(username, password, firstName, lastName, email);
        }
    }
}