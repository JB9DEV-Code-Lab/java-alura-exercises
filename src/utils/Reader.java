package utils;

import java.util.Scanner;

public class Reader {
    private final Scanner scanner = new Scanner(System.in);

    public String askForInput(String question) {
        ask(question);
        return scanner.nextLine();
    }

    public int askForInteger(String question) {
        try {
            ask(question + " (type only numbers)");
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException exception) {
            System.out.println("It was expected an integer but got another type of data: " + exception);
        }

        return 0;
    }

    public double askForDouble(String question) {
        try {
            ask(question + " (type only numbers and it can have decimals)");
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException exception) {
            System.out.println("It was expected a double but got another type of data: " + exception);
        }

        return 0.0;
    }

    private void ask(String question) {
        System.out.print(question + "\n> ");
    }
}
