package Main.Classes.App;

import Main.Classes.Gen.User;
import Main.Classes.Spec.Administrator;
import Main.Classes.Util.Input;
import Main.Data.Course;

import java.util.ArrayList;

public class Application {
    // ----------------------------------------
    // Attributes
    // ----------------------------------------
    private static final Administrator root = new Administrator();
    public static final ArrayList<User> users = new ArrayList<>();
    public static final ArrayList<Course> courses = new ArrayList<>();
    private static boolean initialized = false;

    // ----------------------------------------
    // Methods
    // ----------------------------------------
    public static void init() {
        if (!initialized) {
            System.out.println("Initializing...\n");
            root.createUserAccount();
            initialized = true;
        }
    }

    public static void mainMenu() {
        while (true) {
            System.out.println("\nWelcome To The Main Menu!");

            switch (Input.getInput("""
                    0. Exit
                    1. Login
                                    
                    """, "Option")) {
                case "0" -> {
                    System.out.println("\nExiting...");
                    System.exit(0);
                }
                case "1" -> login();
                default -> {
                    System.out.println("Invalid Input!");
                    System.out.printf("%n%s%n%n", "=".repeat(50));
                }
            }
        }
    }

    private static void login() {
        System.out.println("\nWelcome To The Login Menu!");

        String username = Input.getInput("", "Username");
        String password = Input.getInput("", "Password");

        boolean loggedIn = false;

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                loggedIn = true;
                System.out.println("\nLogin Successful!");
                user.mainMenu();
                break;
            }
        }

        if (!loggedIn) {
            System.out.println("\nLogin Failed!");
            System.out.printf("%n%s%n%n", "=".repeat(50));
        }
    }
}
