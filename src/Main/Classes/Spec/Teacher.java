package Main.Classes.Spec;

import Main.Classes.Gen.Users;

import java.util.ArrayList;

public class Teacher extends Users {
    public static final ArrayList<String> options = new ArrayList<>(Student.options) {
        {
            add("4. Create Exercise");
            add("5. Delete Exercise");
        }
    };
    // ----------------------------------------
    // Attributes
    // ----------------------------------------

    // ----------------------------------------
    // Constructor
    // ----------------------------------------

    public Teacher() {
    }

}
