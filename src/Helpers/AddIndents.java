package Helpers;

public class AddIndents {
    public static String add(String string) {
        StringBuilder finalString = new StringBuilder();
        for (String str : string.split("\n")) {
            if (str.contains("    ") || str.charAt(0) == '.' || str.charAt(str.length() - 1) == ':') {
                finalString.append(str).append('\n');
                continue;
            }
            finalString.append("\t").append(str).append('\n');
        }
        return finalString.toString();
    }
}
