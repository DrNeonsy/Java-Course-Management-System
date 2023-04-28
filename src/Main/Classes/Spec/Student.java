package Main.Classes.Spec;

import Main.Classes.Gen.User;

import java.util.ArrayList;

public class Student extends User {
    public static final ArrayList<String> options = new ArrayList<>() {
        {
            add("0. Exit");
            add("1. Show / Change Account Information");
            add("2. Show Course Assignments");
            add("3. Show Course Members");
        }
    };

    // ----------------------------------------
    // Attributes
    // ----------------------------------------

    // ----------------------------------------
    // Constructor
    // ----------------------------------------

    public Student() {
    }

    // ----------------------------------------
    // Methods
    // ----------------------------------------
}
