import challenges.fakeBankApp.FakeBankApp;
import challenges.fakeMusicApp.FakeMusicApp;
import challenges.shoppingSimulator.ShoppingSimulator;
import games.NumberGuessingGame;
import utils.Menu;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu("Exercises");
        menu.addItem(1, new NumberGuessingGame());
        menu.addItem(2, new FakeBankApp());
        menu.addItem(3, new FakeMusicApp());
        menu.addItem(4, new ShoppingSimulator());
        menu.askForChoosingAnOption("""
        Hi, welcome to Alura Exercises!

        Here you can interact with the exercises from Alura Java courses using it as a
        "CLI" application. Below you are presented to the exercises menu, where you can
        choose one of them to play around.""");
    }
}
