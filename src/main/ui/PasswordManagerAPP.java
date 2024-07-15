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
        System.out.println("\tv -> view item list");
        System.out.println("\tq -> quit");
    }

    private void runCommand(String command) {
        if (command.equals("s")) {
            savePassword();
        } else if (command.equals("v")) {
            viewItemList(newItemList);
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
    }

    private void viewItemList(List<Item> newItemList2) {
        System.out.println("------------------------------------------------");
        System.out.println("|  Item Name  |  Username  |  Password  |");
        System.out.println("------------------------------------------------");
        for (Item item : newItemList2) {
            System.out.printf("|  %-10s  |  %-10s  |  %-10s  |%n", 
                    item.getItemName(), item.getUsername(), item.getPassword());
        }
        System.out.println("------------------------------------------------");
        


    }
       

}
