package Main.Classes.Spec;

import Main.Classes.Gen.Users;
import Main.Interfaces.ITeachers;

import java.util.ArrayList;

public class Teacher extends Users implements ITeachers {
    public static final ArrayList<String> options = new ArrayList<>(Student.options) {
        // ----------------------------------------
        // Attributes
        // ----------------------------------------

        {
            add("4. Create Exercise");
            add("5. Delete Exercise");
            add("6. Add Student");
            add("7. Remove Student");

        }
    };

    // ----------------------------------------
    // Constructor
    // ----------------------------------------

    public Teacher() {
    }

    // ----------------------------------------
    // Methods
    // ----------------------------------------
}
