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
}
