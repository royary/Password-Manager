package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.Item;

public class JsonTest {
    protected void checkItem(String itemName, String password, String username, Item item) {
        assertEquals(itemName, item.getItemName());
        assertEquals(password, item.getPassword());
        assertEquals(username, item.getUsername());
    }
    

}
