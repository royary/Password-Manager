package model;

// Represents an item, with an item name, a password, and a username.
public class Item {
    // A String of item name, can't be empty or null
    private String itemName;
    // A string of password, can be an empty string
    private String password;
    // A string of username, can be an empty string
    private String username;

    // MODIFIES: this
    // REQUIRES: itemName not null or empty
    // EFFECTS: Creates an item with the given item name, password, and username.
    public Item(String itemName, String password, String username) {
        if (itemName == null || itemName.isEmpty()) {
            this.itemName = "Unnamed Item";
        } else {
            this.itemName = itemName;
        }
        this.password = password;
        this.username = username;
    }

    public String getItemName() {
        return this.itemName;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }

    // EFFECTS: Returns true if the item contains the specified keyword in its name
    public boolean containsKeyword(String keyword) {
        return this.itemName.contains(keyword);
    }
}

