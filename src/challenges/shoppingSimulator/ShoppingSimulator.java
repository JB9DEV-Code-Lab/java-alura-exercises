package challenges.shoppingSimulator;

import challenges.shoppingSimulator.models.Product;
import utils.Menu;
import utils.Reader;
import utils.RunnableMenuOption;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class ShoppingSimulator extends RunnableMenuOption {
    private double amount = 1000;
    private final Locale locale = new Locale.Builder().setLanguageTag("pt-PT").build();
    private final NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
    private Menu menu;
    private Menu keepShoppingMenu;
    private List<Product> products = new ArrayList<>();
    private final Reader reader = new Reader();
    private final StopShopping stopShopping = new StopShopping();
    private double totalSpent = 0;
    public ShoppingSimulator() {
        super("Shopping Simulator");
    }

    @Override
    public void run() {
        menu = new Menu("Which option do you want?");
        menu.addItem(1, new UseInitialCredits());
        menu.addItem(2, new SetMyCredits());

        keepShoppingMenu = new Menu("Do you want to continue?");
        keepShoppingMenu.addItem(0, new KeepShopping());
        keepShoppingMenu.addItem(1, stopShopping);

        menu.askForChoosingAnOption("""
        Hi, welcome to %s!

        Here you can play around adding products to your shopping cart, simulating as you
        were buying things in a ecommerce. You have %s to spend with imaginary products or
        you can set your own amount to simulate the shopping. Then you're going to be asked
        about any kind of product, where you have to input its name and price and decide if
        you want to keep buying or not.
        """.formatted(getName(), currencyFormatter.format(amount)));
    }

    private void askForProduct() {
        creditsValidation();

        String formattedAmount = currencyFormatter.format(amount);
        String niceMessage = products.isEmpty()
                ? String.format("Ok, then you have %s, let's start buying!", formattedAmount)
                : String.format("For sure you still have %s, let's buy something else", formattedAmount);
        System.out.printf("%s %n", niceMessage);

        String productName = reader.askForInput("What is the product?");
        double productPrice = reader.askForDouble(String.format("What is the price of %s?", productName));

        creditsValidation(productPrice);
        products.add(new Product(productName, productPrice));
        amount -= productPrice;
        totalSpent += productPrice;

        if (amount == 0) {
            System.out.println("\n\nSince you've spent all of your credit, that's it.");
            stopShopping.run();
        }

        keepShoppingMenu.askForChoosingAnOption();
    }

    private void creditsValidation() {
        if (amount == 0) {
            System.out.println("Unfortunetelly you don't have enough credits for more products.");
            stopShopping.run();
        }
    }
    private void creditsValidation(double productPrice) {
        if (amount - productPrice < 0) {
            System.out.println("Unfortunetelly you don't have enough credits for this product.");
            keepShoppingMenu.askForChoosingAnOption();
        }
    }

    private class UseInitialCredits extends RunnableMenuOption {
        public UseInitialCredits() {
            super(String.format("Use %s to simulate shopping", currencyFormatter.format(amount)));
        }

        @Override
        public void run() {
            askForProduct();
        }
    }

    private class SetMyCredits extends RunnableMenuOption {
        public SetMyCredits() {
            super("Set my own credits before start shopping");
        }

        @Override
        public void run() {
            amount = reader.askForDouble("How much do you want to have to simulate the shopping?");
            askForProduct();
        }
    }

    private class StopShopping extends RunnableMenuOption {
        public StopShopping() {
            super("No, stop shopping");
        }

        @Override
        public void run() {
            products.sort(Comparator.comparing(Product::getPrice));
            System.out.println("\n\nHere is your shopping details:");
            System.out.println("Products bought: ");

            for (Product product : products) {
                System.out.println("  => " + product);
            }

            System.out.printf("Amout that you spent: %s %n", currencyFormatter.format(totalSpent));
            System.out.printf("Remaining amount that you have: %s %n", currencyFormatter.format(amount));
            System.out.println("\nBye, hope see you here again soon!");

            System.exit(0);
        }
    }

    private class KeepShopping extends RunnableMenuOption {
        public KeepShopping() {
            super("Yes, I want to keep shopping");
        }

        @Override
        public void run() {
            askForProduct();
        }
    }
}
