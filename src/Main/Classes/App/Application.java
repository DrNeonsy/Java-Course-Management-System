package Main.Classes.App;

import Main.Classes.Gen.Users;
import Main.Classes.Spec.Administrator;

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
}
