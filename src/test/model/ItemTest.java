package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Item class
 */
class ItemTest {
    private Item item;

    @BeforeEach
    void setUp() {
        item = new Item("TestItem", "TestPassword", "TestUser");
    }

    @Test
    void testConstructorWithValidInputs() {
        assertEquals("TestItem", item.getItemName());
        assertEquals("TestPassword", item.getPassword());
        assertEquals("TestUser", item.getUsername());
    }

    @Test
    void testConstructorWithInvalidItemName() {
        Item itemWithInvalidName = new Item("", "TestPassword", "TestUser");
        assertEquals("Unnamed Item", itemWithInvalidName.getItemName());
    }

    @Test
    void testConstructorWithNullItemName() {
        Item itemWithNullName = new Item(null, "TestPassword", "TestUser");
        assertEquals("Unnamed Item", itemWithNullName.getItemName());
    }

    @Test
    void testGetItemName() {
        assertEquals("TestItem", item.getItemName());
    }

    @Test
    void testGetPassword() {
        assertEquals("TestPassword", item.getPassword());
    }

    @Test
    void testGetUsername() {
        assertEquals("TestUser", item.getUsername());
    }

    @Test
    void testContainsKeyword() {
        assertTrue(item.containsKeyword("Test"));
        assertFalse(item.containsKeyword("Invalid"));
    }

    @Test
    void testToString() {
        String expectedString = "Item Name: TestItem, Username: TestUser, Password: TestPassword";
        assertEquals(expectedString, item.toString());
    }
}
