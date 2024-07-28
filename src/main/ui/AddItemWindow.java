package ui;

import javax.swing.*;
import java.awt.*;


public class AddItemWindow {
    JFrame frame;
    JPanel panel;
    JTextField itemName;
    JTextField userName;
    JTextField password;
    JLabel itemNameLabel;
    JLabel userNameLabel;
    JLabel passwordLabel;

    // EFFECTS: creates new item creation window
    public AddItemWindow() {
        initializeFields();

        panel.add(itemNameLabel);
        panel.add(itemName);
        panel.add(userNameLabel);
        panel.add(userName);
        panel.add(passwordLabel);
        panel.add(password);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setMinimumSize(new Dimension(50, 150));
        panel.setPreferredSize(new Dimension(50, 150));

        panel.setBackground(Color.PINK);
    }

    // EFFECTS: initializes item creation fields
    private void initializeFields() {
        frame = new JFrame("New Meal");
        panel = new JPanel();

        itemNameLabel = new JLabel("Enter Item Name");
        userNameLabel = new JLabel("Enter username");
        passwordLabel = new JLabel("Enter password");

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
