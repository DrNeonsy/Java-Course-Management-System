package Main.Classes.Spec;

import Main.Classes.Gen.Users;

import java.util.ArrayList;

public class Administrator extends Users {
    private static final ArrayList<Users> users = new ArrayList<>();
    // ----------------------------------------
    // Attributes
    // ----------------------------------------

    // ----------------------------------------
    // Constructor
    // ----------------------------------------

    public Administrator() {
        createAccount();
    }

    // ----------------------------------------
    // Methods
    // ----------------------------------------

    // ----------------------------------------
    // Create Account
    // ----------------------------------------
    public void createAccount() {
        System.out.println("Account Setup Initiated!");

        setName();
        setSurname();
        setEmail();
        setPassword();
        setUsername();
        setRole();

        System.out.println("Account Created!");
    }
}
