package model;

import org.json.JSONObject;

import persistence.Writable;

// Represents an item, with an item name, a password, and a username.
public class Item implements Writable {
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

    // CITATION: modeled from
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: converts an item into a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("item name", itemName);
        json.put("password", password);
        json.put("username", username);
        return json;
    }

    @Override
    public String toString() {
        return String.format("Item Name: %s, Username: %s, Password: %s", itemName, username, password);
    }
}
