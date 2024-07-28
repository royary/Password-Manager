package ui;

import javax.swing.*;
import java.awt.*;

public class FindItemWindow {
    private JPanel panel;
    private JTextField itemNameField;
    private JTextField usernameField;

    // EFFECTS: constructs the remove item window with input fields
    public FindItemWindow() {
        panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Item Name:"));
        itemNameField = new JTextField();
        panel.add(itemNameField);
        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);
        panel.setBackground(Color.PINK);
    }

    // EFFECTS: returns the JPanel containing the input fields
    public JPanel getPanel() {
        return panel;
    }

    // EFFECTS: returns the item name input by the user
    public String getItemName() {
        return itemNameField.getText();
    }

    // EFFECTS: returns the username input by the user
    public String getUsername() {
        return usernameField.getText();
    }

}
