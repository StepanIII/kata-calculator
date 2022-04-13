import java.util.Scanner;

/**
 * @author Stepan Cupriyanovich
 * @version 1.0
 */

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("�� ��������� �����������");
        System.out.println("��� ���������� �������� ������� ��������� � ������� enter. �������� ������� ����� ������");
        System.out.println("��� ���������� ������ ������� end");

       while (true) {
           String expression = scanner.nextLine();
           if (expression.equals("end")){
               System.out.println("���������� ������");
               break;
           }
           String result = calc(expression);
           System.out.println(result);
       }
    }

    public static String calc(String input) throws Exception {
        if(!Validator.isMathOperation(input))
            throw new Exception("������ �� �������� �������������� ���������");
        if(!Validator.isValidFormat(input))
            throw new Exception("������ �������������� �������� �� ������������� ������� - ��� �������� � ���� �������� (+, -, /, *)");

        String[] values = input.split(" ");
        String strValueOne = values[0].trim();
        String strValueTwo = values[2].trim();
        String operation = values[1].trim();

        float valueOne = 0;
        float valueTwo = 0;
        String result = "";

        if(!Validator.isSameType(strValueOne, strValueTwo))
            throw new Exception("������������ ������������ ������ ������� ���������");

        boolean isArabNumber = Validator.isArabNumber(strValueOne);
        boolean isRomanNumber = Validator.isRomanNumber(strValueOne);

        if (isArabNumber) {
            valueOne = Float.parseFloat(values[0].trim());
            valueTwo = Float.parseFloat(values[2].trim());

            if (!Validator.isValidSizeArab(valueOne, valueTwo))
                throw new Exception("�������� ����� ������ ���� �� 1 �� 10 ������������");
            if(!Validator.isInteger(strValueOne, strValueTwo))
                throw new Exception("�������� ������ ������ ����� �����");
        }

        if (isRomanNumber) {
            if (!Validator.isValidSizeRoman(strValueOne, strValueTwo))
                throw new Exception("�������� ����� ������ ���� �� 1 �� 10 ������������");

            valueOne = ConvertNumbers.romanToArabic(strValueOne);
            valueTwo = ConvertNumbers.romanToArabic(strValueTwo);

            if(valueOne <= valueTwo && operation.equals("-")) throw
                    new Exception("� ������� ������� ���������� ��� ������������� ����� � ����");
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
