package Methods;

import java.util.Date;
import java.util.Scanner;

import static Helpers.PrintHelpers.*;
import static Helpers.PrintHelpers.printSeparator;

public class CustomMethods {

    public static void LoginWithUserName() {
        Scanner user_input = new Scanner(System.in);
        print_same_line("Please enter your username: ");
        String username = user_input.nextLine();
        Date loginTime = new Date();
        printLoading("Logging you in as -" + username + "-, wait please");

        printSeparator();
        printFormatted("Welcome %s ! You logged in at %s", username, loginTime);
    }
}
