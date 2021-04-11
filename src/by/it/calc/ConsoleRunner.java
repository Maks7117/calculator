package by.it.calc;

import java.util.Scanner;

public class ConsoleRunner {
    public static void main(String[] args) throws CalcException {
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();
        Printer printer = new Printer();
        Logger logger = Logger.GET;
        try {
            Var.load();
        } catch (CalcException e) {
            System.out.println();
        }
        for (; ; ) {
            String expression = sc.nextLine();
            if (expression.equals("end")) break;
            try {
                Var result = parser.calc(expression);
                printer.print(result);
            } catch (CalcException e) {
                String message = e.getMessage();
                logger.log(message);
                System.out.println(message);
            }
        }
    }
}
