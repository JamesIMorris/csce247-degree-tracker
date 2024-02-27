public class UI {
    private DegreeTracker degreeTracker;

    UI() {
        degreeTracker = DegreeTracker.getInstance();

    }

    public void run() {
        scenario1();
        scenario2();

    }

    public void scenario1() {
        System.out.println();

        if (!degreeTracker.login("JD123", "DoeJohn@5")) {
            System.out.println("Sorry we couldn't login.");
            return;
        }
        System.out.println("John Doe is now logged in");
    }

    public void scenario2() {
        System.out.println();

        if (degreeTracker.signup("NewUser123", "NewPassword@123", "New", "User", "newuser@example.com",
                UserType.STUDENT)) {
            System.out.println("Account created successfully.");
        } else {
            System.out.println("Failed to create an account.");
            return;
        }

        if (!degreeTracker.login("NewUser123", "NewPassword@123")) {
            System.out.println("Sorry we couldn't login.");
        } else {
            System.out.println("NewUser123 is now logged in");
        }
    }

    public static void main(String[] args) {
        UI degreeInterface = new UI();
        degreeInterface.run();

    }

}