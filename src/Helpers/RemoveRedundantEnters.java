package Helpers;

public class RemoveRedundantEnters {
    public static String remove(String string) {
        return string.replaceAll("\n{2,}", "\n");
    }
}
