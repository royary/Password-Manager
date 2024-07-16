package ui;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Item;


public class PasswordManagerAPP {
    private Scanner input;

    private List<Item> newItemList = new ArrayList<>(); 
    
    private static final String CHARACTERS = 
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" 
            + "abcdefghijklmnopqrstuvwxyz" 
            + "0123456789" 
            + "!@#$%^&*()-_=+[]{}|;:,.<>?";
    private static final SecureRandom RANDOM = new SecureRandom();      
    
    // CITATION
    // EFFECTS: run the password manager application
    public PasswordManagerAPP() {
        runManager();
    }

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
        System.out.println("Quit password manager successful");
    }

    private void initialize() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        //TODO

    }
    
    private void displayMenu() {
        System.out.println("\nSelect from");
        System.out.println("\ts -> save password");
        System.out.println("\tf -> find password");
        System.out.println("\td -> delete password");
        System.out.println("\tg -> generate password");
        System.out.println("\tv -> view item list");
        System.out.println("\tq -> quit");

    }

    private void runCommand(String command) {
        if (command.equals("s")) {
            savePassword();
        } else if (command.equals("v")) {
            viewItemList(newItemList);
        } else if (command.equals("d")) {
            deleteItem(newItemList);
        } else if (command.equals("g")) {
            generatePassword(newItemList);
        } else if (command.equals("f")) {
            findPassword(newItemList);
        } else {
            System.out.println("Invalid selection...");
        }
    }

    private void savePassword() {
        
        System.out.println("\nInput item name:");
        String itemName = input.next();
        System.out.println("\nInput password:");
        String password = input.next();
        System.out.println("\nInput username:");
        String username = input.next();
        Item newItem = new Item(itemName, password, username);
        newItemList.add(newItem); 
        System.out.println("\nNew item successfully created!");   
    }

    private void viewItemList(List<Item> newItemList) {
        System.out.println("------------------------------------------------");
        System.out.println("|  Item Name  |  Username  |  Password  |");
        System.out.println("------------------------------------------------");
        for (Item item : newItemList) {
            System.out.printf("|  %-10s  |  %-10s  |  %-10s  |%n", 
                    item.getItemName(), item.getUsername(), item.getPassword());
        }
        System.out.println("------------------------------------------------");
    }
    
    private void deleteItem(List<Item> newItemList) {
        System.out.println("\nInput item name to delete:");
        String itemNameToDelete = input.next();
    
        int count = countItem(newItemList, itemNameToDelete);
    
        if (count == 1) {
            deleteItemUnique(newItemList, itemNameToDelete);
        } else if (count > 1) {
            deleteItemMultiple(newItemList, itemNameToDelete);
        } else {
            System.out.println("\nItem not found. Please check the item name and try again.");
        }
    }

    public int countItem(List<Item> newItemList, String itemName) {
        int count = 0;
        for (Item item : newItemList) {
            if (item.getItemName().equals(itemName)) {
                count++;
            }
        }
        return count;
    }

    public void deleteItemUnique(List<Item> newItemList, String itemName) {
        for (Item item : newItemList) {
            if (item.getItemName().equals(itemName)) {
                newItemList.remove(item);
                System.out.println("\nItem successfully deleted!");
                break;
            }
        }
    }

    public void deleteItemMultiple(List<Item> newItemList, String itemName) {
        boolean itemFound = false;
        System.out.println("\nMultiple items found with the same name");
        System.out.println("Input username associated with the item:");
        String usernameToDelete = input.next();
        for (Item item : newItemList) {
            if (item.getItemName().equals(itemName) 
                        && item.getUsername().equals(usernameToDelete)) {
                newItemList.remove(item);
                System.out.println("\nItem successfully deleted!");
                itemFound = true;
                break;
            }
        }
        if (!itemFound) {
            System.out.println("\nItem not found. Please check the item name, username and try again");
        }
    }      

     
    private void generatePassword(List<Item> newItemList) {
        int passwordLength = 10;
        String randomPassword = generateRandom(passwordLength);
        System.out.println("Generated Password:" + randomPassword);
        System.out.println("Do you want to save this password? Y/N");
        String saveCommand = input.next();
        if ((saveCommand.toLowerCase()).equals("y")) {
            System.out.println("Please enter item name:");
            String saveItemName = input.next();
            System.out.println("Please enter username:");
            String saveUsername = input.next();
            Item saveItem = new Item(saveItemName, randomPassword, saveUsername);
            newItemList.add(saveItem); 
            System.out.println("Successfully save generated password!");
        } 
    }

    private String generateRandom(int passwordLength) {
        StringBuilder randomPassword = new StringBuilder(passwordLength);

        for (int i = 0; i < passwordLength; i++) {
            // generates a random integer from 0 (inclusive) to n (exclusive).
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            randomPassword.append(CHARACTERS.charAt(randomIndex));
        }
        return randomPassword.toString();
    }

    private void findPassword(List<Item> newItemList) {
        System.out.println("\nInput item name to find:");
        String itemNameToFind = input.next();

        int countFind = countItemFind(newItemList, itemNameToFind);

        if (countFind == 1) {
            findItemUnique(newItemList, itemNameToFind);
        } else if (countFind > 1) {
            findItemMultiple(newItemList, itemNameToFind);
        } else {
            System.out.println("\nItem not found. Please check the item name and try again.");
        }
    }

    public int countItemFind(List<Item> newItemList, String itemName) {
        int countFind = 0;
        for (Item item : newItemList) {
            if (item.getItemName().contains(itemName)) {
                countFind++;
            }
        }
        return countFind;
    }

    public void findItemUnique(List<Item> newItemList, String itemName) {
        for (Item item : newItemList) {
            if (item.getItemName().contains(itemName)) {
                String findPassword = item.getPassword();
                String findUsername = item.getUsername();
                String findItemName = item.getItemName();

                System.out.println("\nSuccessfully find!" + "\nItem name:" + findItemName 
                        + "\nPassword:" + findPassword + "\nUsername:" + findUsername);
                break;
            }
        }
    }

    public void findItemMultiple(List<Item> newItemList, String itemName) {
        boolean itemFound = false;
        System.out.println("\nMultiple items found with the same name");
        System.out.println("Input username associated with the item:");
        String usernameToFind = input.next();
        for (Item item : newItemList) {
            if (item.getItemName().contains(itemName) 
                        && item.getUsername().contains(usernameToFind)) {
                String findPassword = item.getPassword();
                String findUsername = item.getUsername();
                String findItemName = item.getItemName();

                System.out.println("\nSuccessfully find!" + "\nItem name:" + findItemName 
                            + "\nPassword:" + findPassword + "\nUsername:" + findUsername);
                itemFound = true;
                break;
            }
        }
        if (!itemFound) {
            System.out.println("\nItem not found. Please check the item name, username and try again");
        }
    }      

}
