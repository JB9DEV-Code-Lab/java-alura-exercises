package games;

import utils.RunnableMenuOption;

import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame extends RunnableMenuOption {
  public NumberGuessingGame() {
    super("Number Guessing Game");
  }

  public void run() {
    // jogo de adivinha

    int guessBound = 100;
    int chosenNumber = new Random().nextInt(guessBound);
    String lessOrGreater = ""; // TODO: Refact into an enum
    int tryies = 5;

    System.out.printf("""
        Welcome to %s!

        You should guess a number between 0 and %d within %d tryies. Let see in how many tryies you get this.

        Good luck! %n""", this.getName(), guessBound, tryies);

    System.out.println("Ok, I chose the number, let's start!");
    Scanner reader = new Scanner(System.in);
    while (tryies > 0) {
      System.out.printf("Choose a number between 0 and %d: ", guessBound);
      int guessing = reader.nextInt();
      tryies--;
      if (guessing == chosenNumber) {
        System.out.printf("You rocked! %d is the number and you guessed in %d try(ies)!", chosenNumber, tryies);
        break;
      }

      if (chosenNumber < guessing) {
        lessOrGreater = "less";
      } else {
        lessOrGreater = "greater";
      }

      if (tryies <= 0) {
        System.out.printf("You have no more tryies, the number is %d", chosenNumber);
      } else {
        System.out.printf(
            "Not yet, the number is %s than %d. You have %d try(ies) left. Try again: ",
            lessOrGreater, guessing, tryies);
      }
    }
  }
}
