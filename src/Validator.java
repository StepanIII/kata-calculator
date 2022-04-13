import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Stepan Cupriyanovich
 * @version 1.0
 */

interface Validator {
    static boolean isValidSizeArab(float numberOne, float numberTwo) {
        return numberOne <= 10 && numberTwo <= 10
                && numberOne >= 1 && numberTwo >= 1;
    }

    static boolean isValidSizeRoman(String numberOne, String numberTwo) {
        String reg = "(I{1,3}|IV|VI{0,3}|IX|X)";
        Pattern pattern = Pattern.compile(reg + " " + reg);
        Matcher matcher = pattern.matcher(numberOne + " " + numberTwo);
        return matcher.matches();
    }

    static boolean isInteger(String numberOne, String numberTwo) {
        if(numberOne.contains(".")) return false;
        return !numberTwo.contains(".");
    }

    static boolean isSameType(String numberOne, String numberTwo) {
        String regArab = "[0-9]+[.]?[0-9]*";
        Pattern patternArab = Pattern.compile(regArab + " " + regArab);
        Matcher matcherArab = patternArab.matcher(numberOne + " " + numberTwo);

        String regRoman = "X{0,3}(L?X{0,3}|C?)(I{1,3}|IV|VI{0,3}|IX|)";
        Pattern patternRoman = Pattern.compile(regRoman + " " + regRoman);
        Matcher matcherRoman = patternRoman.matcher(numberOne + " " + numberTwo);

        return matcherArab.matches() || matcherRoman.matches();
    }

    static boolean isRomanNumber(String number) {
        String reg = "X{0,3}(L?X{0,3}|C?)(I{1,3}|IV|VI{0,3}|IX|)";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

    static boolean isArabNumber(String number) {
        String reg = "(\\d+|\\d+[.]\\d+)";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

    static boolean isValidFormat(String expression) {
        Pattern pattern = Pattern.compile("(\\w+|\\d+.\\d+) ([+]|-|[*]|/) (\\w+|\\d+.\\d+)");
        Matcher matcher = pattern.matcher(expression);
        return matcher.matches();
    }

    static boolean isMathOperation(String expression) {
        String reg = "(\\w+|\\d+.\\d+) ([+]|-|[*]|/) (\\w+|\\d+.\\d)( ([+]|-|[*]|/) (\\w+|\\d+.\\d+))*";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(expression);
        return matcher.matches() ;
    }
}
