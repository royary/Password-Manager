package ui;

import javax.swing.*;

import java.awt.*;

/**
 * The GenerateItemWindow class creates a window for adding a new item with
 * fields for item name, username, and password.
 * It includes an option to pre-fill the password field with a pre-generated
 * password.
 * The layout uses a vertical BoxLayout, and the panel has a pink background.
 */
public class GenerateItemWindow {
    private JPanel panel;
    private JTextField itemName;
    private JTextField password;
    private JTextField userName;
    private JLabel itemNameLabel;
    private JLabel passwordLabel;
    private JLabel userNameLabel;

    // EFFECTS: constructs the add item window with input fields and a pre-generated
    // password
    public GenerateItemWindow(String preGeneratedPassword) {
        initializeFields();

        panel.add(itemNameLabel);
        panel.add(itemName);
        panel.add(userNameLabel);
        panel.add(userName);
        panel.add(passwordLabel);
        panel.add(password);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setMinimumSize(new Dimension(50, 150));
        panel.setPreferredSize(new Dimension(200, 150));

        panel.setBackground(Color.PINK);

        if (preGeneratedPassword != null) {
            password.setText(preGeneratedPassword);
        }
    }

    // EFFECTS: initializes item creation fields
    private void initializeFields() {
        panel = new JPanel();

        itemNameLabel = new JLabel("Enter Item Name");
        userNameLabel = new JLabel("Enter Username");
        passwordLabel = new JLabel("Enter Password");

        itemName = new JTextField();
        itemName.setMaximumSize(new Dimension(300, 25));
        userName = new JTextField();
        userName.setMaximumSize(new Dimension(300, 25));

        password = new JTextField();
        password.setMaximumSize(new Dimension(300, 25));
    }

    public JPanel returnJPanel() {
        return panel;
    }

    // EFFECTS: retrieves item name entered
    public String getItemName() {
        return itemName.getText();
    }

    // EFFECTS: retrieves user name entered
    public String getUsername() {
        return userName.getText();
    }

    // EFFECTS: retrieves password entered
    public String getPassword() {
        return password.getText();
    }

}
