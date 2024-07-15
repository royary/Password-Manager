package model;


// Represents a item, with a item name, a password and a username.
public class Item {
    // A String of item name, can't be empty or null
    private String itemName;
    // A string of password, can be an empty string
    private String password;
    // A string of username, can be an empty string
    private String username;

    // itemName, password, username have a non-zero length
    // EFFECTS: creates item with ietem name, password and username
    public Item(String itemName, String password, String username) {
        this.itemName = itemName;
        this.password = password;
        this.username = username;  
    }

    public String getItemName() {
        return itemName;
    }

    public void setItem(String itemName) {
        //TODO
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void changeItemname(String newItemName) {
        this.itemName = newItemName;
    }

    public void changePassword(String newPassword) {
        this.itemName = newPassword;
    }

    public void changeUsername(String newUsername) {
        this.itemName = newUsername;
    }

}

