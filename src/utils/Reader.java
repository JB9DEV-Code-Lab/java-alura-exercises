package utils;

import java.util.Scanner;

public class Reader {
    private final Scanner scanner = new Scanner(System.in);
    private final String defaultQuestionSufix = "\n> ";

    public String askForInput(String question) {
        System.out.print(question + defaultQuestionSufix);
        return scanner.next();
    }

    public int askForInteger(String question) {
        System.out.print(question + defaultQuestionSufix);
        return scanner.nextInt();
    }

    public double askForDouble(String question) {
        System.out.print(question + defaultQuestionSufix);
        return scanner.nextDouble();
    }
}
