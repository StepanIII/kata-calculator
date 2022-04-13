import java.util.HashMap;
import java.util.Map;

/**
 * @author Stepan Cupriyanovich
 * @version 1.0
 */

interface ConvertNumbers {
    static String arabicToRoman(String number) {
        StringBuilder result = new StringBuilder();
        int numberInt = (int)Float.parseFloat(number);
        int decRemain = numberInt / 10;
        int remain = numberInt % 10;

        result.append(arabToRomanBasic(remain));

        if (numberInt < 40 && numberInt >= 10) result.insert(0, "X".repeat(decRemain));
        if (numberInt < 50 && numberInt >= 40) result.insert(0,"XL");
        if (numberInt < 90 && numberInt >= 50) {
            result.insert(0,"X".repeat(decRemain - 5));
            result.insert(0,"L");
        }
        if (numberInt < 100 && numberInt >= 90) result.insert(0,"XC");
        if (numberInt == 100) result.insert(0, "C");

        return result.toString();
    }

    private static String arabToRomanBasic(int number) {
        StringBuilder result = new StringBuilder();

        if (number < 4) result.append("I".repeat(number));
        if (number == 4) result.append("IV");
        if (number < 9 && number >= 5) {
            result.append("V");
            result.append("I".repeat(number - 5));
        }
        if (number == 9) result.append("IX");

        return result.toString();
    }

    static int romanToArabic(String number) {
        Map<String, Integer> romanArabMap = new HashMap<>();
        romanArabMap.put("I", 1);
        romanArabMap.put("II", 2);
        romanArabMap.put("III", 3);
        romanArabMap.put("IV", 4);
        romanArabMap.put("V", 5);
        romanArabMap.put("VI", 6);
        romanArabMap.put("VII", 7);
        romanArabMap.put("VIII", 8);
        romanArabMap.put("IX", 9);
        romanArabMap.put("X", 10);

        return romanArabMap.get(number);
    }
}

