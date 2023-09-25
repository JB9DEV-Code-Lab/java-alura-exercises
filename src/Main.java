import challenges.fakeBankApp.FakeBankApp;
import challenges.fakeMusicApp.FakeMusicApp;
import games.NumberGuessingGame;
import utils.Menu;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu("Exercise categories");
        menu.addMenuItem(1, new FakeBankApp());
        menu.addMenuItem(2, new NumberGuessingGame());
        menu.addMenuItem(3, new FakeMusicApp());
        menu.askForChoosingAnOption();
    }
}
