package ui;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.DefaultListModel;

import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;



import model.Item;
import model.ItemList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.util.List;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// Represents graphical user interface
public class Gui extends JFrame  {
    private static final String JSON_STORE = "./data/itemlist.json";
    private static final int WIDTH = 400;
    private static final int HEIGHT = 600;

    private DefaultListModel<Item> itemListModel;



    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private ItemList itemList;

   


    // EFFECTS: Create and set up the main application window.
    public Gui() {
        super("PasswordManager");
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.PINK);

        initializeBackend();
        initializeButtons();
        pack();
        setVisible(true);

        startLoadPrompt();
        exitSavePrompt();
    }

    // MODIFIES: this
    // EFFECTS: initializes main model and data fields
    private void initializeBackend() {
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        itemList = new ItemList();
        itemListModel = new DefaultListModel<>();
    }

    // EFFECTS: updates item on screen from backend
    private void updateItems() {

        itemListModel.clear();
        List<Item> items = itemList.getItems();
        for (Item item : items) {
            itemListModel.addElement(item);
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes load prompt popup window on start
    private void startLoadPrompt() {
        int loadOption = JOptionPane.showConfirmDialog(null,
                "Would you like to load your record?", "Load Record",
                JOptionPane.YES_NO_OPTION);
        if (loadOption == JOptionPane.YES_OPTION) {
            try {
                itemList = jsonReader.read();
                updateItems();
            } catch (IOException e) {
                System.out.println("Unable to read from file " + JSON_STORE);
            }
        }
    }

    // EFFECTS: initializes save prompt popup window when quitting
    private void exitSavePrompt() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                int saveOption = JOptionPane.showConfirmDialog(null,
                        "Would you like to save your record before exiting?", "Save Record",
                        JOptionPane.YES_NO_OPTION);
                if (saveOption == JOptionPane.YES_OPTION) {
                    try {
                        jsonWriter.open();
                        jsonWriter.write(itemList);
                        jsonWriter.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("Unable to write to file: " + JSON_STORE);
                    }
                    dispose();
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: initializes buttons and places them each on a separate row in the
    // center
    private void initializeButtons() {
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new GridBagLayout());
        buttonPane.setBackground(Color.PINK); // Set JPanel background to pink
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(5, 0, 5, 0); // Add some spacing between buttons

        buttonPane.add(createButton("Add Password", "Add"), gbc);
        buttonPane.add(createButton("Find Password", "Find"), gbc);
        buttonPane.add(createButton("Delete Password", "Delete"), gbc);
        buttonPane.add(createButton("Generate Password", "Generate"), gbc);
        buttonPane.add(createButton("View List", "View"), gbc);

        add(buttonPane, BorderLayout.CENTER); // Add buttonPane to the center
    }

    // EFFECTS: creates a button with the specified text and action command
    private JButton createButton(String text, String actionCommand) {
        JButton button = new JButton(text);
        button.setActionCommand(actionCommand);
        button.addActionListener(new ButtonListener());
        return button;
    }

    class ButtonListener implements ActionListener {

        // EFFECTS: processes button clicks and runs appropriate methods
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "Add":
                    addPasswordAction();
                    break;
                case "Find":
                    findPasswordAction();
                    break;
                case "Delete":
                    deletePasswordAction();
                    break;
                case "Generate":
                    generatePasswordAction();
                    break;
                case "View":
                    viewListdAction();
                    break;
            }
        }

        private void viewListdAction() {
            updateItems();
            JList<Item> itemListDisplay = new JList<>(itemListModel);
            JScrollPane scrollPane = new JScrollPane(itemListDisplay);
            JOptionPane.showMessageDialog(null, scrollPane, "All Items", JOptionPane.INFORMATION_MESSAGE);
        }



        private void generatePasswordAction() {
            String randomPassword = generateAndShowPassword();
            if (askToSavePassword(randomPassword)) {
                saveGeneratedPassword(randomPassword);
            }
        }
        
        private String generateAndShowPassword() {
            int passwordLength = 10;
            String randomPassword = PasswordManagerAPP.generateRandom(passwordLength);
            JOptionPane.showMessageDialog(null, "Generated Password: " + randomPassword);
            return randomPassword;
        }
        
        private boolean askToSavePassword(String randomPassword) {
            int saveOption = JOptionPane.showConfirmDialog(
                    null, "Do you want to save this password?", "Save Password",
                    JOptionPane.YES_NO_OPTION);
            return saveOption == JOptionPane.YES_OPTION;
        }
        
        private void saveGeneratedPassword(String randomPassword) {
            GenerateItemWindow newItemWindow = new GenerateItemWindow(randomPassword);
            JPanel panel = newItemWindow.returnJPanel();
        
            int optionPaneValue = JOptionPane.showConfirmDialog(
                    null, panel,
                    "Save Generated Password",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
            if (optionPaneValue == JOptionPane.OK_OPTION) {
                try {
                    String itemName = newItemWindow.getItemName();
                    String userName = newItemWindow.getUsername();
                    String password = newItemWindow.getPassword();
                    Item newItem = new Item(itemName, password, userName);
        
                    itemList.addItem(newItem);
                    updateItems();
                    JOptionPane.showMessageDialog(null, "Password saved successfully!");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input!");
                }
            }
        }
        

        private void findPasswordAction() {
            FindItemWindow findItemWindow = new FindItemWindow();
            JPanel panel = findItemWindow.getPanel();

            int optionPaneValue = JOptionPane.showConfirmDialog(
                    null, panel,
                    "Find Item",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (optionPaneValue == JOptionPane.OK_OPTION) {
                handleSearch(findItemWindow);
            }
        }



        private void handleSearch(FindItemWindow findItemWindow) {
            String itemName = findItemWindow.getItemName();
            String username = findItemWindow.getUsername();

            if (!itemName.trim().isEmpty() || !username.trim().isEmpty()) {
                List<Item> foundItems = itemList.findItemsByName(itemName);
                displaySearchResults(foundItems);
            } else {
                JOptionPane.showMessageDialog(null, "Please enter an item name or username to search.",
                        "Invalid Input", JOptionPane.WARNING_MESSAGE);
            }
        }



        private void displaySearchResults(List<Item> foundItems) {
            if (foundItems.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No items found with the specified keyword.",
                        "Search Results", JOptionPane.INFORMATION_MESSAGE);
            } else {
                DefaultListModel<Item> foundListModel = new DefaultListModel<>();
                for (Item item : foundItems) {
                    foundListModel.addElement(item);
                }
                JList<Item> foundJList = new JList<>(foundListModel);
                JOptionPane.showMessageDialog(null, new JScrollPane(foundJList),
                        "Search Results", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        

        private void deletePasswordAction() {
            FindItemWindow removeItemWindow = new FindItemWindow();
            JPanel panel = removeItemWindow.getPanel();

            int optionPaneValue = JOptionPane.showConfirmDialog(
                    null, panel,
                    "Delete Item",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (optionPaneValue == JOptionPane.OK_OPTION) {
                String itemName = removeItemWindow.getItemName();
                String username = removeItemWindow.getUsername();

                if (itemList.removeItem(itemName, username)) {
                    updateItems();
                    JOptionPane.showMessageDialog(null, "Item deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Item not found.");
                }
            }
        }

        // MODIFIES: this, itemList
        // EFFECTS: creates popup window and interface for new item creation,
        // adds item to itemList if a item was created and updates screen
        private void addPasswordAction() {
            AddItemWindow newItemWindow = new AddItemWindow();
            JPanel panel = newItemWindow.returnJPanel();

            int optionPaneValue = JOptionPane.showConfirmDialog(
                    null, panel,
                    "Add Item",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (optionPaneValue == JOptionPane.OK_OPTION) {
                try {
                    String itemName = newItemWindow.getItemName();
                    String userName = newItemWindow.getUsername();
                    String password = newItemWindow.getPassword();
                    Item newItem = new Item(itemName, password, userName);

                    itemList.addItem(newItem);
                    updateItems();
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input!");
                }
            }
        }
    }

   

   
}
