package ui;

import java.security.SecureRandom;
import java.util.List;
import java.util.Scanner;
import model.Item;
import model.ItemList;

/*
 * Represents a Password Manager application.
 * Implement function of save, view, delete, generate, and find passwords.
 */
public class PasswordManagerAPP {
    private Scanner input;
    private ItemList itemList = new ItemList();

    private static final String CHARACTERS = 
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" 
            + "abcdefghijklmnopqrstuvwxyz" 
            + "0123456789" 
            + "!@#$%^&*()-_=+[]{}|;:,.<>?";
    private static final SecureRandom RANDOM = new SecureRandom();      

    // CITATION: modeled from https://github.students.cs.ubc.ca/CPSC210/TellerApp
    // EFFECTS: Initializes and runs the password manager application.
    public PasswordManagerAPP() {
        runManager();
    }

    // EFFECTS: Runs the main loop of the password manager application.
    private void runManager() {
        boolean continueRun = true;
        String command = null;

        initialize();

        while (continueRun) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                continueRun = false;
            } else {
                runCommand(command);
            }        
        }
        System.out.println("Quit password manager successfully");
    }

    // CITATION: modeled from https://github.students.cs.ubc.ca/CPSC210/TellerApp
    // EFFECTS: Initializes the input scanner.
    private void initialize() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: Displays the main menu options to the user.
    private void displayMenu() {
        System.out.println("\nSelect from");
        System.out.println("\ts -> save password");
        System.out.println("\tf -> find password");
        System.out.println("\td -> delete password");
        System.out.println("\tg -> generate password");
        System.out.println("\tv -> view item list");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: Executes the command given by the user.
    private void runCommand(String command) {
        if (command.equals("s")) {
            savePassword();
        } else if (command.equals("v")) {
            displayItemList();
        } else if (command.equals("d")) {
            deleteItem();
        } else if (command.equals("g")) {
            generatePassword();
        } else if (command.equals("f")) {
            findPassword();
        } else {
            System.out.println("Invalid selection...");
        }
    }

    // MODIFIES: this
    // EFFECTS: Prompts user to input item name, password, and username, 
    // then saves the new item to the list.
    private void savePassword() {
        System.out.println("\nInput item name:");
        String itemName = input.next();
        System.out.println("\nInput password:");
        String password = input.next();
        System.out.println("\nInput username:");
        String username = input.next();
        Item newItem = new Item(itemName, password, username);
        itemList.addItem(newItem);
        System.out.println("\nNew item successfully created!");
    }

    // EFFECTS: Displays the list of items with their names, usernames, and passwords.
    private void displayItemList() {
        List<Item> items = itemList.getItems();
        System.out.println("------------------------------------------------");
        System.out.println("|  Item Name  |  Username  |  Password  |");
        System.out.println("------------------------------------------------");
        for (Item item : items) {
            System.out.printf("|  %-10s  |  %-10s  |  %-10s  |%n", 
                    item.getItemName(), item.getUsername(), item.getPassword());
        }
        System.out.println("------------------------------------------------");
    }

    // MODIFIES: this
    // EFFECTS: Prompts user to input item name to delete, then deletes the item from the list if it exists.
    private void deleteItem() {
        System.out.println("\nInput item name to delete:");
        String itemNameToDelete = input.next();
        System.out.println("Input username associated with the item:");
        String usernameToDelete = input.next();

        boolean success = itemList.removeItem(itemNameToDelete, usernameToDelete);
        if (success) {
            System.out.println("\nItem successfully deleted!");
        } else {
            System.out.println("\nItem not found. Please check the item name and username and try again.");
        }
    }

    // MODIFIES: this
    // EFFECTS: Generates a random password, displays it, 
    // and optionally saves it as a new item.
    private void generatePassword() {
        int passwordLength = 10;
        String randomPassword = generateRandom(passwordLength);
        System.out.println("Generated Password: " + randomPassword);
        System.out.println("Do you want to save this password? Y/N");
        String saveCommand = input.next();
        if (saveCommand.equalsIgnoreCase("y")) {
            System.out.println("Please enter item name:");
            String saveItemName = input.next();
            System.out.println("Please enter username:");
            String saveUsername = input.next();
            Item saveItem = new Item(saveItemName, randomPassword, saveUsername);
            itemList.addItem(saveItem);
            System.out.println("Successfully saved generated password!");
        }
    }

    // REQUIRES: passwordLength > 0
    // EFFECTS: Generates and returns a random password of the specified length.
    private String generateRandom(int passwordLength) {
        StringBuilder randomPassword = new StringBuilder(passwordLength);
        for (int i = 0; i < passwordLength; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            randomPassword.append(CHARACTERS.charAt(randomIndex));
        }
        return randomPassword.toString();
    }

    // EFFECTS: Prompts user to input item name to find, then displays the item details if it exists.
    private void findPassword() {
        System.out.println("\nInput item name to find:");
        String itemNameToFind = input.next();

        List<Item> foundItems = itemList.findItemsByName(itemNameToFind);
        if (foundItems.isEmpty()) {
            System.out.println("\nItem not found. Please check the item name and try again.");
        } else {
            System.out.println("\nFound Items:");
            for (Item item : foundItems) {
                System.out.println("Item name: " + item.getItemName()  
                                  + "\nPassword: " + item.getPassword() 
                                  + "\nUsername: " + item.getUsername());
            }
        }
    }
}

