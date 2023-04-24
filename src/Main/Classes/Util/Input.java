package Main.Classes.Util;

import java.util.Scanner;

public class Input {
    // ----------------------------------------
    // Character Only Input
    // ----------------------------------------
    public static boolean isCharInput(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isLetter(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    // ----------------------------------------
    // Number Only Input
    // ----------------------------------------
    public static boolean isNumInput(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    // ----------------------------------------
    // Email Input
    // ----------------------------------------
    public static boolean isEmailInput(String input) {
        return input.contains("@") && input.contains(".");
    }

    // ----------------------------------------
    // Password Input
    // ----------------------------------------
    public static boolean isPasswordInput(String input) {
        return input.length() >= 8
                && input.matches(".*[0-9].*")
                && input.matches(".*[a-z].*")
                && input.matches(".*[A-Z].*");
    }

    // ----------------------------------------
    // Getting Input And Looping Until Valid
    // ----------------------------------------
    public static String getInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your input: ");
        return scanner.nextLine();
    }
}
