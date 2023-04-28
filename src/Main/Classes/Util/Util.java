package Main.Classes.Util;

public class Util {
    // --------------------------------------------------------------------------------
    // Helper Method To Check If A String Is A Valid Enum Value
    // --------------------------------------------------------------------------------
    public static boolean isValidEnum(String value, Class<? extends Enum<?>> enumClass) {
        for (Enum<?> e : enumClass.getEnumConstants()) {
            if (e.name().equals(value)) {
                return true;
            }
        }
        return false;
    }

    public static boolean decision(String msg) {

        char cKey, option1 = 'y', option2 = 'n';

        System.out.printf(msg);

        do {
            cKey = Input.getInput("", "Y | N").toLowerCase().charAt(0);
        } while (cKey != option1 && cKey != option2);

        return cKey == option1;
    }
}
