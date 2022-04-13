import java.util.Scanner;

/**
 * @author Stepan Cupriyanovich
 * @version 1.0
 */

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Вы запустили калькулятор");
        System.out.println("Для выполенеия операции введите выражение и нажмите enter. Значения вводите через пробел");
        System.out.println("Для завершения работы введите end");

       while (true) {
           String expression = scanner.nextLine();
           if (expression.equals("end")){
               System.out.println("Завершение работы");
               break;
           }
           String result = calc(expression);
           System.out.println(result);
       }
    }

    public static String calc(String input) throws Exception {
        if(!Validator.isMathOperation(input))
            throw new Exception("Строка не является математической операцией");
        if(!Validator.isValidFormat(input))
            throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");

        String[] values = input.split(" ");
        String strValueOne = values[0].trim();
        String strValueTwo = values[2].trim();
        String operation = values[1].trim();

        float valueOne = 0;
        float valueTwo = 0;
        String result = "";

        if(!Validator.isSameType(strValueOne, strValueTwo))
            throw new Exception("Используются одновременно разные системы счисления");

        boolean isArabNumber = Validator.isArabNumber(strValueOne);
        boolean isRomanNumber = Validator.isRomanNumber(strValueOne);

        if (isArabNumber) {
            valueOne = Float.parseFloat(values[0].trim());
            valueTwo = Float.parseFloat(values[2].trim());

            if (!Validator.isValidSizeArab(valueOne, valueTwo))
                throw new Exception("Диапозон чисел должен быть от 1 до 10 включительно");
            if(!Validator.isInteger(strValueOne, strValueTwo))
                throw new Exception("Вводится должны только целые числа");
        }

        if (isRomanNumber) {
            if (!Validator.isValidSizeRoman(strValueOne, strValueTwo))
                throw new Exception("Диапозон чисел должен быть от 1 до 10 включительно");

            valueOne = ConvertNumbers.romanToArabic(strValueOne);
            valueTwo = ConvertNumbers.romanToArabic(strValueTwo);

            if(valueOne <= valueTwo && operation.equals("-")) throw
                    new Exception("В римской системе исчисления нет отрицательных чисел и нуля");
        }

        switch (operation) {
            case "+" -> result = String.valueOf((int)(valueOne + valueTwo));
            case "-" -> result = String.valueOf((int)(valueOne - valueTwo));
            case "*" -> result = String.valueOf((int)(valueOne * valueTwo));
            case "/" -> result = String.valueOf((int)(valueOne / valueTwo));
        }

        if (isRomanNumber) result = ConvertNumbers.arabicToRoman(result);

        return result;
    }
}
