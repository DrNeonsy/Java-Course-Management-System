package Main.Classes.App;

import Main.Classes.Gen.Users;
import Main.Classes.Spec.Administrator;
import Main.Classes.Util.Input;

import java.util.ArrayList;

public class Application {
    // ----------------------------------------
    // Attributes
    // ----------------------------------------
    private static final Administrator root = new Administrator();
    public static final ArrayList<Users> users = new ArrayList<>();
    private static boolean initialized = false;

    // ----------------------------------------
    // Methods
    // ----------------------------------------
    public static void Init() {
        if (!initialized) {
            root.createAccount();
            initialized = true;
        }
    }

    public static void MainMenu() {
        while (true) {
            System.out.println("\nWelcome To The Main Menu!");

            switch (Input.getInput("""
                    0. Exit
                    1. Login
                                    
                    """, "Option")) {
                case "0" -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                case "1" -> Login();
                default -> {
                    System.out.println("Invalid Input!");
                    System.out.printf("%n%s%n%n", "=".repeat(50));
                }
            }
        }
    }

    private static void Login() {
        System.out.println("\nWelcome To The Login Menu!");

        String username = Input.getInput("", "Username");
        String password = Input.getInput("", "Password");

        boolean loggedIn = false;

        for (Users user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                loggedIn = true;
                System.out.println("\nLogin Successful!");
                user.MainMenu();
                break;
            }
        }

        if (!loggedIn) {
            System.out.println("\nLogin Failed!");
            System.out.printf("%n%s%n%n", "=".repeat(50));
        }
    }
}
