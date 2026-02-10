import Cart.Cart;
import Cart.Item;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cart cart = new Cart();
        Store store = new Store();
        UserManager userManager = new UserManager();

        User currentUser = null;

        while (currentUser == null) {
            System.out.println("Welcome to Online Vending Machine! Please type ('login' or 'sign-up') to get started:");
            System.out.print("> ");
            String option = scanner.nextLine();

            System.out.print("Username: ");
            String name = scanner.nextLine();
            System.out.print("Password: ");
            String pass = scanner.nextLine();

            if (option.equalsIgnoreCase("sign-up")) {
                if (userManager.signUp(name, pass)) {
                    System.out.println("Account created! Please login.");
                } else {
                    System.out.println("Username already taken.");
                }
            } else if (option.equalsIgnoreCase("login")) {
                currentUser = userManager.login(name, pass);
                if (currentUser == null) {
                    System.out.println("Invalid username or password. Try again.");
                }
            }
        }

        System.out.println("\nHello, " + currentUser.getName() + "!");

        boolean shopping = true;

        while (shopping) {
            System.out.println("\nWhat would you like today?");
            System.out.println("- Drinks");
            System.out.println("- Snacks");
            System.out.println("- Leave");
            System.out.print("> ");

            String category = scanner.nextLine();

            if (category.equalsIgnoreCase("drinks")) {

                ArrayList<Item> drinks = store.getCategory("drinks");

                System.out.println("Drinks:");
                for (Item item : drinks) {
                    System.out.println(item.getName() + " $" + item.getPrice());
                }

                System.out.print("> ");
                String choice = scanner.nextLine().toUpperCase();

                boolean found = false;
                for (Item item : drinks) {
                    if (item.getName().startsWith(choice)) {
                        cart.addItem(item);
                        System.out.println("Thank you for your purchase!");
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Invalid selection.");
                }

            }
            else if (category.equalsIgnoreCase("snacks")) {

                ArrayList<Item> snacks = store.getCategory("snacks");

                System.out.println("Snacks:");
                for (Item item : snacks) {
                    System.out.println(item.getName() + " $" + item.getPrice());
                }

                System.out.print("> ");
                String choice = scanner.nextLine().toUpperCase();

                boolean found = false;
                for (Item item : snacks) {
                    if (item.getName().startsWith(choice)) {
                        cart.addItem(item);
                        System.out.println("Thank you for your purchase!");
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Invalid selection.");
                }
            }
            else if (category.equalsIgnoreCase("leave")) {
                break;
            }

            System.out.print("Would you like to purchase something else (y/n)?\n> ");
            if (scanner.nextLine().equalsIgnoreCase("n")) {
                shopping = false;
            }
        }

        System.out.println("\nThank you, come again.");
        System.out.println("Items purchased: " + cart.getItemNames());
        System.out.printf("Amount spent: $%.2f%n", cart.getTotal());

        scanner.close();
    }
}

