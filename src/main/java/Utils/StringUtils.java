package Utils;

public class StringUtils {
    static String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

    public static String getRandomString(int stringLength) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stringLength; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }
}
