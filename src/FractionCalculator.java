import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FractionCalculator {

    private static final String FRACTION_FORMAT = "([-]*\\d+)(/([-]*[1-9]\\d*))*";
    private static final String OPERATION_FORMAT = "[+-/*=qQ]";

    public static void main(String[] args) {


        System.out.println("Whis program is a fraction calculator\nIt will add, subtract, " +
                "multiply and divide fractions until you type Q to quit.\nPlease enter your " +
                "fractions in the form a/b, where a and b are integers.");
        printLine();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String operation = getOperation(scanner);
            if (operation.toLowerCase().equals("q")) {
                break;
            } else {
                try {
                    Fraction fraction1 = getFraction(scanner);
                    Fraction fraction2 = getFraction(scanner);
                    if (operation.equals("+")) {
                        System.out.println(fraction1 + " + " + fraction2 + " = " + fraction1.add(fraction2));
                    } else if (operation.equals("-")) {
                        System.out.println(fraction1 + " - " + fraction2 + " = " + fraction1.subtract(fraction2));
                    } else if (operation.equals("/")) {
                        System.out.println(fraction1 + " / " + fraction2 + " = " + fraction1.divide(fraction2));
                    } else if (operation.equals("*")) {
                        System.out.println(fraction1 + " * " + fraction2 + " = " + fraction1.multiply(fraction2));
                    } else {
                        System.out.println(fraction1 + " = " + fraction2 + " is " + fraction1.equals(fraction2));
                    }
                } catch (IllegalArgumentException ex) {
                    System.out.println("Error, you cannot divide by 0!");
                }
            }
        }
    }

    private static void printLine() {
        for (int i = 0; i < 80; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private static String getOperation(Scanner input) {
        Pattern pat = Pattern.compile(OPERATION_FORMAT);
        System.out.print("Please enter an operation (+, -, /, *, = or Q to quit): ");
        while (true) {
            String operationInput = input.next();
            if (operationInput.matches(OPERATION_FORMAT)) {
                Matcher match = pat.matcher(operationInput);
                match.matches();
                return match.group(0);
            } else {
                System.out.print("Invalid input (+, -, /, *, = or Q to quit): ");
            }
        }
    }

    private static boolean validFraction(String input) {
        return input.matches(FRACTION_FORMAT);
    }

    private static Fraction getFraction(Scanner input) {
        Pattern pat = Pattern.compile(FRACTION_FORMAT);
        System.out.print("Please enter a fraction (a/b) or integer (a): ");
        while (true) {
            String userInput = input.next();
            if (validFraction(userInput)) {
                Matcher match = pat.matcher(userInput);
                match.matches();
                int nom = Integer.parseInt(match.group(1));
                int den = 1;
                if (match.group(3) != null) {
                    den = Integer.parseInt(match.group(3));
                }
                return new Fraction(nom, den);
            } else {
                System.out.print("Invalid fraction. Please enter (a/b) or (a), where a and b are integers and b is not zero: ");
            }
        }
    }
}
