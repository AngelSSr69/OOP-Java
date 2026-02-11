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
            String users = scanner.nextLine();
            System.out.print("Password: ");
            String pass = scanner.nextLine();


            // This is the sign-up/login section
            if (option.equalsIgnoreCase("sign-up")) {
                if (userManager.signUp(users, pass)) {
                    System.out.println("Account created! Please login.");
                } else {
                    System.out.println("Username already taken.");
                }
            } else if (option.equalsIgnoreCase("login")) {
                currentUser = userManager.login(users, pass);
                if (currentUser == null) {
                    System.out.println("Invalid username or password. Try again.");
                }
            }
        }

        System.out.println("\nHello, " + currentUser.getName() + "!");

        boolean shopping = true;

        while (shopping) {

            // This is the admin panel, where admins can update price/remove items from the store
            if (currentUser.getName().equals("admin")) {
                System.out.println("\n--- ADMIN PANEL ---");
                System.out.println("1. Update Item Price");
                System.out.println("2. Remove Item from Store");
                System.out.println("3. Skip to Shopping");
                System.out.print("Choice: ");
                String adminChoice = scanner.nextLine();

                if (adminChoice.equals("1")) {
                    System.out.print("Category (drinks/snacks): ");
                    String cat = scanner.nextLine();
                    System.out.print("Item Name: ");
                    String it_choice = scanner.nextLine();
                    System.out.print("New Price: ");
                    double price = Double.parseDouble(scanner.nextLine());

                    store.updateItemPrice(cat, it_choice, price); //
                    System.out.println("Price updated!");
                }
                else if (adminChoice.equals("2")) {
                    System.out.print("Category (drinks/snacks): ");
                    String cat = scanner.nextLine();
                    System.out.print("Item Name to remove: ");
                    String it_remove = scanner.nextLine();

                    if (store.removeItem(cat, it_remove)) { //
                        System.out.println("Item removed successfully.");
                    } else {
                        System.out.println("Could not find that item.");
                    }
                }
            }

            // Options
            System.out.println("\nWhat would you like today?");
            System.out.println("- Drinks");
            System.out.println("- Snacks");
            System.out.println("- Cart");
            System.out.println("- Settings");
            System.out.println("- Leave");
            System.out.print("> ");

            String category = scanner.nextLine();

            // This will take you to the drink section, where you can choos which drink to get
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
            // This will take you to the snack section, where you can choos which snack to get
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
            // This will let you leave the program
            else if (category.equalsIgnoreCase("leave")) {
                break;
            }
            // This is the cart section where you can remove items and check what you are buying
            else if (category.equalsIgnoreCase("cart")) {
                System.out.println("Your current items: " + cart.getItemNames());
                System.out.println("1. Remove Item (Delete)");
                System.out.println("2. Back");
                String cartChoice = scanner.nextLine();

                if (cartChoice.equals("1")) {
                    System.out.print("Enter item name exactly to remove: ");
                    String toRemove = scanner.nextLine();

                    if (cart.removeItemByName(toRemove)) {
                        System.out.println("Successfully removed " + toRemove);
                    } else {
                        System.out.println("Item not found in cart.");
                    }
                }
            }
            // This is the setting section where you can change password or delete account
            else if (category.equalsIgnoreCase("settings")) {
                System.out.println("\n--- ACCOUNT SETTINGS ---");
                System.out.println("1. Change Password (Update)");
                System.out.println("2. Delete Account (Delete)");
                System.out.println("3. Back");
                System.out.print("Choice: ");
                String settingsChoice = scanner.nextLine();

                if (settingsChoice.equals("1")) {
                    System.out.print("Enter current password: ");
                    String oldPass = scanner.nextLine();
                    System.out.print("Enter new password: ");
                    String newPass = scanner.nextLine();

                    if (userManager.updatePassword(currentUser.getName(), oldPass, newPass)) {
                        System.out.println("Password updated successfully!");
                    } else {
                        System.out.println("Error: Current password incorrect.");
                    }
                }
                else if (settingsChoice.equals("2")) {
                    System.out.print("CONFIRMATION: Enter password to delete your account: ");
                    String confirmPass = scanner.nextLine();

                    if (userManager.deleteUser(currentUser.getName(), confirmPass)) {
                        System.out.println("Account deleted successfully. Logging you out...");
                        shopping = false;
                    } else {
                        System.out.println("Error: Incorrect password. Account not deleted.");
                    }
                }
            }

            // Checking if the user would like to shop for more
            System.out.print("Would you like to continue (y/n)?\n> ");
            if (scanner.nextLine().equalsIgnoreCase("n")) {
                shopping = false;
            }
        }

        // The final thank you, along with a brief description of items purchased and amount spent
        System.out.println("\nThank you, come again.");
        System.out.println("Items purchased: " + cart.getItemNames());
        System.out.printf("Amount spent: $%.2f%n", cart.getTotal());

        scanner.close();
    }
}

