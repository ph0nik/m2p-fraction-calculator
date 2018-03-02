import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FractionCalculatorAdvanced {

    private static final String INPUT_FORMAT = "(([-]*\\d+)(/([-]*[1-9]\\d*))*)\\s*([+-/*=])\\s*(([-]*\\d+)(/([-]*[1-9]\\d*))*)|[qQ]";

    public static void main(String[] args) {
        System.out.println("This program is fraction calculator.\n" +
                "It will add, subtract, multiply and divide fraction until you type Q to quit.\n" +
                "Valid operations are of the form \"[FRAC] [OPERATION] [FRAC]\".\n" +
                "[FRAC] can be either a single integer or two integers separated by \"/\".\n" +
                "[OPERATION] can be +, -, *, / or =.");
        printLine();
        Scanner scanner = new Scanner(System.in);
        checkInput(scanner);
    }

    private static void printLine() {
        for (int i = 0; i < 80; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private static void checkInput(Scanner input) {
        Pattern pat = Pattern.compile(INPUT_FORMAT);
        boolean finished = false;
        while (!finished) {
            System.out.print("Enter an operation (q to quit): ");
            String next = input.nextLine();
            if (next.matches(INPUT_FORMAT)) {
                try {
                    if ("q".equals(next.trim().toLowerCase())) {
                        finished = true;
                    } else {
                        Matcher matcher = pat.matcher(next);
                        matcher.matches();
                        int a = Integer.parseInt(matcher.group(2));
                        int b = (matcher.group(4) != null) ? Integer.parseInt(matcher.group(4)) : 1;
                        String op = matcher.group(5);
                        int x = Integer.parseInt(matcher.group(7));
                        int y = (matcher.group(9) != null) ? Integer.parseInt(matcher.group(9)) : 1;
                        Fraction frac1 = new Fraction(a, b);
                        Fraction frac2 = new Fraction(x, y);
                        if (op.equals("+")) {
                            System.out.println(frac1 + " + " + frac2 + " = " + frac1.add(frac2));
                        } else if (op.equals("-")) {
                            System.out.println(frac1 + " - " + frac2 + " = " + frac1.subtract(frac2));
                        } else if (op.equals("/")) {
                            System.out.println(frac1 + " / " + frac2 + " = " + frac1.divide(frac2));
                        } else if (op.equals("*")) {
                            System.out.println(frac1 + " * " + frac2 + " = " + frac1.multiply(frac2));
                        } else {
                            System.out.println(frac1 + " = " + frac2 + " is " + frac1.equals(frac2));
                        }
                    }
                } catch (IllegalArgumentException ex) {
                    System.out.println("Error, you cannot divide by 0!");
                }

            } else {
                System.out.println("Invalid operation. Must be \"[FRAC] [OPERATION] [FRAC]\"");
            }
        }
    }
}
