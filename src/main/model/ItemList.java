package model;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// Represents an item list, with items in it.
public class ItemList implements Writable {
    private List<Item> items;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "abcdefghijklmnopqrstuvwxyz"
            + "0123456789"
            + "!@#$%^&*()-_=+[]{}|;:,.<>?";
    private static final SecureRandom RANDOM = new SecureRandom();

    // EFFECTS: Initializes an empty list of items
    public ItemList() {
        items = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: Adds an item to the list
    public void addItem(Item item) {
        items.add(item);
        EventLog.getInstance().logEvent(new Event("item added to ItemList."));
    }

    // MODIFIES: this
    // EFFECTS: Removes the item with the specified name and username from the list
    public boolean removeItem(String itemName, String username) {
        boolean ret = items
                .removeIf(item -> item.getItemName().equals(itemName) && item.getUsername().equals(username));
        if (ret) {
            EventLog.getInstance().logEvent(new Event("item deleted from ItemList."));
        } else {
            EventLog.getInstance()
                    .logEvent(new Event("Delete operation was performed, but no item was deleted."));
        }
        return ret;
    }

    // EFFECTS: Returns the count of items with the specified name
    public int countItemsWithName(String itemName) {
        int count = 0;
        for (Item item : items) {
            if (item.getItemName().equals(itemName)) {
                count++;
            }
        }
        return count;
    }

    // EFFECTS: Returns a list of items that contain the specified keyword in their
    // name
    public List<Item> findItemsByName(String keyword) {
        List<Item> result = new ArrayList<>();
        for (Item item : items) {
            if (item.containsKeyword(keyword)) {
                result.add(item);
            }
        }
        EventLog.getInstance().logEvent(new Event("find item from ItemList."));
        return result;
    }

    // EFFECTS: Returns the list of items
    public List<Item> getItems() {
        EventLog.getInstance().logEvent(new Event("get items."));
        return items;
    }

    // REQUIRES: passwordLength > 0
    // EFFECTS: Generates and returns a random password of the specified length.
    public static String generateRandom(int passwordLength) {
        StringBuilder randomPassword = new StringBuilder(passwordLength);
        for (int i = 0; i < passwordLength; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            randomPassword.append(CHARACTERS.charAt(randomIndex));
        }
        EventLog.getInstance().logEvent(new Event("generate password."));
        return randomPassword.toString();
    }

    // CITATION: modeled from
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: converts item list to JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Items", itemsToJson());
        return json;
    }

    // CITATION: modeled from
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: returns things in this item list as a JSON array
    private JSONArray itemsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Item i : items) {
            jsonArray.put(i.toJson());
        }
        return jsonArray;
    }
}
