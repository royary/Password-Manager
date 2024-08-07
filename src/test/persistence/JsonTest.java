package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.Item;

/**
 * Unit tests for the Json class
 */
// CITATION: This class has been modeled from
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonTest {

    // EFFECTS: Compares given item(s) with entered data
    protected void checkItem(String itemName, String password, String username, Item item) {
        assertEquals(itemName, item.getItemName());
        assertEquals(password, item.getPassword());
        assertEquals(username, item.getUsername());
    }

}
