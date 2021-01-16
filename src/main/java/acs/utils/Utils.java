package acs.utils;

public class Utils {

    public static String upperCaseToCamelCase(String upperCase){
        String words[] = upperCase.split("_");
        String camelCase = "";
        for (String word: words) {
            camelCase += word.charAt(0) + word.substring(1).toLowerCase();
        }
        camelCase = Character.toLowerCase(camelCase.charAt(0)) + camelCase.substring(1);
        return camelCase;
    }

}
