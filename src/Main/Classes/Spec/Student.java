package Main.Classes.Spec;

import Main.Classes.Gen.Users;

import java.util.ArrayList;

public class Student extends Users {
    public static final ArrayList<String> options = new ArrayList<>() {
        {
            add("0. Exit");
            add("1. Show Account Information");
            add("2. Enter Course");
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

}
