package challenges.fakeBankApp;

import challenges.fakeBankApp.enums.AccountType;
import challenges.fakeBankApp.enums.OperationType;
import utils.Reader;
import utils.RunnableMenuOption;
import utils.Menu;

import java.text.NumberFormat;
import java.util.Locale;

/*
     * Requirements:
     * 1. [x] Show initial client data
     * 2. [x] Show operations menu list
     * 3. [x] Ask for input and always show the remaining amount
     * 4. [x] Validate if there is money enough to send
     * 5. [x] Validate the option passed
     *
     * Nice to have (my own ideas):
     * 1. [] Store data in some files, one with clients data and other with the movements. It can maybe use like a csv
     *       and work "like a database"
     * 2. [] Create a login / logout flow
     * 3. [] Add welcome message if it's the first time
     * 4. [] Add CRUD to user information
     * 5. [] Add an option to show how the "system" works and for what it's done
     * 6. [] Add language support both for portuguese and english
     *
     * */
public class FakeBankApp extends RunnableMenuOption {
    private final AccountType accountType = AccountType.CHECKING;
    private double amount = 10.0;
    private final String clientName = "John Doe";
    private final Locale currentLocale = new Locale.Builder().setLanguageTag("pt-PT").build();
    private final NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(currentLocale);
    private final Menu menu = new Menu(getName() + " operations");
    private final Reader reader = new Reader();

    public FakeBankApp() {
        super("Fake Bank App");
        menu.addItem(1, new CheckAmout());
        menu.addItem(2, new ReceiveMoney());
        menu.addItem(3, new SendMoney());
    }

    public String getFormattedAmount() {
            return currencyFormatter.format(amount);
        }

    @Override
    public void run() {
        menu.askForChoosingAnOption(getClientData());
    }

    private String getClientData() {
        return """
        CLIENT DATA
          Name:           %s
          Account type:   %s
          Amount:         %s
        """.formatted(clientName, accountType, currencyFormatter.format(amount));
    }

    private void showRemainingAmount(OperationType operationType, double operationAmount) {
        System.out.printf("%nAmount updated: you just %s $ %s and your current amount is %s.%n",
                            operationType.toString().toLowerCase(), currencyFormatter.format(operationAmount),
                            getFormattedAmount()
        );
    }

    private void wantToProceed() {
        int yesOrNo = reader.askForInteger("""
            Do you want to do any other operation?
              1 - Yes
              2 - No""");

        if (yesOrNo == 1) {
            menu.askForChoosingAnOption();
        } else {
            System.exit(0);
        }
    }

    // region private classes
    private class CheckAmout extends RunnableMenuOption {
        public CheckAmout() {
            super("Check Amount");
        }

        @Override
        public void run() {
            System.out.printf("Your current amount is %s %n%n", getFormattedAmount());
            wantToProceed();
        }
    }

    private class ReceiveMoney extends RunnableMenuOption {
        public ReceiveMoney() {
            super("Receive Money");
        }

        @Override
        public void run() {
            double value = reader.askForDouble("""
            \nRECEIVE AMOUNT
            How much did you receive?""");

            if (value <= 0) {
                System.out.println("Operation cancelend!\n");
                menu.askForChoosingAnOption();
                return;
            }

            amount += value;
            showRemainingAmount(OperationType.RECEIVED, value);
            wantToProceed();
        }
    }

    private class SendMoney extends RunnableMenuOption {
        private double value = -1;
        public SendMoney() {
            super("Send Money");
        }

        @Override
        public void run() {
            if (value < 0) {
                value = reader.askForDouble("""
                \nSEND AMOUNT
                How much do you want to send?""");
            }

            if (amount == 0) {
                System.out.println("Sorry, your amout is zero, you can't send any money for now.");
                menu.askForChoosingAnOption();
                return;
            }

            if (value <= 0) {
                System.out.println("Operation canceled!\n");
                menu.askForChoosingAnOption();
                return;
            }

            if (value > amount) {
                System.out.printf(
                        "Invalid value, you can't send %s once your amout is %s. Operation canceled! %n",
                        currencyFormatter.format(value), getFormattedAmount()
                );
                value = reader.askForDouble("Please provide a value that fits to your amount or 0 to cancel");
                this.run();
            } else {
                amount -= value;
                showRemainingAmount(OperationType.SENT, value);
                wantToProceed();
            }
        }
    }
    // endregion private classes

}
