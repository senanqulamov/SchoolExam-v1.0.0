package Helpers;
import static Helpers.PrintHelpers.*;

public class CalculationHelpers {

    public static int sum(int a, int b) {
        printSeparator();
        printFormattedInfo("Inputs for sum: a=%d, b=%d", a, b);
        int result = a + b;
        printFormattedSuccess("Result of sum is: %d", result);
        return result;
    }

    public static int sub(int a, int b) {
        printSeparator();
        printFormattedInfo("Inputs for subtraction: a=%d, b=%d", a, b);
        int result = a - b;
        printFormattedSuccess("Result of subtraction is: %d", result);
        return result;
    }

    public static int mul(int a, int b) {
        printSeparator();
        printFormattedInfo("Inputs for multiplication: a=%d, b=%d", a, b);
        int result = a * b;
        printFormattedSuccess("Result of multiplication is: %d", result);
        return result;
    }

    public static int div(int a, int b) {
        printSeparator();
        int result = 0;
        printFormattedInfo("Inputs for division: a=%d, b=%d", a, b);
        if (b == 0) {
            printFormattedError("Division by zero is not allowed.");
            /*throw new ArithmeticException("Division by zero");*/
        }else{
            result = a / b;
            printFormattedSuccess("Result of division is: %d", result);
        }
        return result;
    }
}