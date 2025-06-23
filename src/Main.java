import java.util.*;
import javax.swing.*;
import static Helpers.PrintHelpers.*;

public class Main {

    public static void main(String[] args) {
        String username = "John";
        Date loginTime = new Date();

        printFormatted("User %s has logged in at %s", username, loginTime);

        String productName = "Laptop";
        double price = 999.99;
        int quantity = 5;

        printFormatted("Product: %s | Price: %.2f | Quantity: %d", productName, price, quantity);
        printSeparator();

        printInfo("This is an informational message.");
    }
}