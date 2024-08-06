package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ItemListTest {
    private ItemList itemList;
    private Item item1;
    private Item item2;
    private Item item3;

    @BeforeEach
    void setUp() {
        itemList = new ItemList();
        item1 = new Item("TestItem1", "TestPassword1", "TestUser1");
        item2 = new Item("TestItem2", "TestPassword2", "TestUser2");
        item3 = new Item("AnotherItem", "AnotherPassword", "AnotherUser");
        itemList.addItem(item1);
        itemList.addItem(item2);
        itemList.addItem(item3);
    }

    @Test
    void testAddItem() {
        Item newItem = new Item("NewItem", "NewPassword", "NewUser");
        itemList.addItem(newItem);
        assertTrue(itemList.getItems().contains(newItem));
    }

    @Test
    void testRemoveItem() {
        boolean removed = itemList.removeItem("TestItem1", "TestUser1");
        assertTrue(removed);
        assertFalse(itemList.getItems().contains(item1));
    }

    @Test
    void testRemoveItemWithDifferentUsername() {
        boolean removed = itemList.removeItem("TestItem1", "WrongUser");
        assertFalse(removed);
        assertTrue(itemList.getItems().contains(item1));
    }

    @Test
    void testRemoveItemNotFound() {
        boolean removed = itemList.removeItem("NonExistingItem", "NonExistingUser");
        assertFalse(removed);
    }

    @Test
    void testCountItemsWithName() {
        assertEquals(1, itemList.countItemsWithName("TestItem1"));
        assertEquals(0, itemList.countItemsWithName("NonExistingItem"));
    }

    @Test
    void testFindItemsByName() {
        List<Item> foundItems = itemList.findItemsByName("TestItem");
        assertTrue(foundItems.contains(item1));
        assertTrue(foundItems.contains(item2));
        assertFalse(foundItems.contains(item3));
    }

    @Test
    void testFindItemsByKeyword() {
        List<Item> foundItems = itemList.findItemsByName("Another");
        assertTrue(foundItems.contains(item3));
        assertFalse(foundItems.contains(item1));
        assertFalse(foundItems.contains(item2));
    }

    @Test
    void testGetItems() {
        List<Item> items = itemList.getItems();
        assertTrue(items.contains(item1));
        assertTrue(items.contains(item2));
        assertTrue(items.contains(item3));
    }

    @Test
    void testGenerateRandom() {
        int length = 10;
        String randomPassword = ItemList.generateRandom(length);
        assertNotNull(randomPassword);
        assertEquals(length, randomPassword.length());
    }
}