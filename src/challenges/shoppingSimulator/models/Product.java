package challenges.shoppingSimulator.models;

import java.text.NumberFormat;
import java.util.Locale;

public class Product implements Comparable<Product> {
    private final String name;
    private final Double price;
    private final Locale locale = new Locale.Builder().setLanguageTag("pt-PT").build();
    private final NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public int compareTo(Product otherProduct) {
        return price.compareTo(otherProduct.getPrice());
    }

    @Override
    public String toString() {
        return String.format("%s - %s", getName(), currencyFormatter.format(getPrice()));
    }

    /*
     * TODO:
     *   Turn into an ordinary class, to implement compareTo and toString methods
     * */
}
