package Main.Classes.Spec;

import Main.Classes.Gen.Users;
import Main.Classes.Util.Input;
import Main.Interfaces.IAdmins;

import java.util.ArrayList;

import static Main.Classes.App.Application.users;
import static Main.Classes.Util.Util.isValidEnum;

public class Administrator extends Users implements IAdmins {
    // ----------------------------------------
    // Attributes
    // ----------------------------------------
    public static final ArrayList<String> options = new ArrayList<>(Teacher.options) {
        {
            add("8. Show All Courses");
            add("9. Show All IUsers");
            add("10. Create Account");
            add("11. Delete Account");
            add("12. Create Course");
            add("13. Delete Course");
        }
    };
    private static boolean isRoot = true; // False After Root Creation / Startup

    // ----------------------------------------
    // Constructor
    // ----------------------------------------

    public Administrator() {
    }

    // ----------------------------------------
    // Methods
    // ----------------------------------------

    // ----------------------------------------
    // Create Account
    // ----------------------------------------
    public void createAccount() {
        System.out.println("Account Setup Initiated!");

        if (!isRoot) { // Default Execution For All IUsers Except Root
            users.add(defineType());
        } else { // Executed Only Once
            users.add(this);
            isRoot = false;
        }

        // Calling Account Information Setters For The New User

        users.get(users.size() - 1).setName();
        users.get(users.size() - 1).setSurname();
        users.get(users.size() - 1).setEmail();
        users.get(users.size() - 1).setPassword();
        users.get(users.size() - 1).setUsername();
        users.get(users.size() - 1).setRole();

        System.out.println("Account Created!");
    }

    private static Users defineType() {
        enum Type {
            STUDENT,
            TEACHER,
            ADMINISTRATOR
        }

        while (true) {
            String input = Input.getInput("""
                    Enter The Type Of User You Wish To Create:
                    1. Student
                    2. Teacher
                    3. Administrator
                                        
                    """, "Type");

            if (isValidEnum(input.toUpperCase(), Type.class)) {

                switch (Type.valueOf(input)) {
                    case STUDENT -> {
                        return new Student();
                    }
                    case TEACHER -> {
                        return new Teacher();
                    }
                    case ADMINISTRATOR -> {
                        return new Administrator();
                    }
                }
            } else {
                System.out.println("Invalid Input!\n");
            }
        }
    }
}
