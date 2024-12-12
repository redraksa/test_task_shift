import java.math.BigInteger;

public class StringCheck {

    protected static boolean tryInteger(String str) {
        try {
            new BigInteger(str);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    protected static boolean tryFloat(String str) {
        try {
            Double.parseDouble(str);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
}
