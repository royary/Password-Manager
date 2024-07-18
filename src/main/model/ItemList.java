package model;

import java.util.ArrayList;
import java.util.List;

// Represents an item list, with items in it.
public class ItemList {
    private List<Item> items;

    // EFFECTS: Initializes an empty list of items
    public ItemList() {
        items = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: Adds an item to the list
    public void addItem(Item item) {
        items.add(item);
    }

    // MODIFIES: this
    // EFFECTS: Removes the item with the specified name and username from the list
    public boolean removeItem(String itemName, String username) {
        return items.removeIf(item -> item.getItemName().equals(itemName) && item.getUsername().equals(username));
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

    // EFFECTS: Returns a list of items that contain the specified keyword in their name
    public List<Item> findItemsByName(String keyword) {
        List<Item> result = new ArrayList<>();
        for (Item item : items) {
            if (item.containsKeyword(keyword)) {
                result.add(item);
            }
        }
        return result;
    }

    // EFFECTS: Returns the list of items
    public List<Item> getItems() {
        return items;
    }
}
