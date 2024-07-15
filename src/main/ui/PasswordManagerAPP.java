package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Item;


public class PasswordManagerAPP {
    private Scanner input;
    private String command;
    private String itemName;
    private String password;
    private String username;
    private List<Item> newItemList = new ArrayList<>(); 
    
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

    }



}
