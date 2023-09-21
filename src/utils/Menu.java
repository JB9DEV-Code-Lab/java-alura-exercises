package utils;

import java.util.HashMap;
import java.util.Map;


public class Menu {
    private final Map<Integer, RunnableMenuOption> items = new HashMap<>();
    private final String title;
    private final Reader reader = new Reader();

    public Menu(String title) {
        this.title = title;
        items.put(0, new QuitFromApplication());
    }

    public void addMenuItem(Integer option, RunnableMenuOption runnableMenuOption) {
        if (option <= 0) {
            throw new RuntimeException("Invalid option value passed: " + option);
        }

        items.put(option, runnableMenuOption);
    }

    public void askForChoosingAnOption() {
        System.out.println(title.toUpperCase());

        for (int memuOption : items.keySet()) {
            System.out.println(memuOption + ": " + items.get(memuOption).getName());
        }

        runOption(reader.askForInteger("Which option do you want?"));
    }

    private void runOption(int option) {
        if (validateOption(option)) {
            items.get(option).run();
        }
    }

    private boolean validateOption(int option) {
        RunnableMenuOption runnableMenuOption = items.get(option);
        boolean isValid = runnableMenuOption != null;

        if (!isValid) {
            System.out.printf("Invalid option! %d doesn't exist in this menu", option);
        }

        return isValid;
    }
}

class QuitFromApplication extends RunnableMenuOption {
    public QuitFromApplication() {
        super("Quit");
    }
    public void run() {}
}