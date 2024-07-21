package ui;

import java.io.FileNotFoundException;

// Main method to run the Password Manager application
public class Main {
    public static void main(String[] args) {
        try {
            // Create and start the PasswordManagerAPP application
            new PasswordManagerAPP();
        } catch (FileNotFoundException e) {
            // Handle the exception if the required file is not found
            System.out.println("Unable to run application: file not found");
        }
    }
}