package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
// import org.junit.Test;


public class ItemTest {
    private Item testItem1;
    private Item testItem2;

    @BeforeEach
    public void runBefore() {
        testItem1 = new Item("item1", "12345", "54321");
        testItem2 = new Item("item2", "password!2", "username?3");
    }

    @Test
    public void testConstructor() {
        assertEquals("item1", testItem1.getItemName());
        assertEquals("12345", testItem1.getPassword());
        assertEquals("54321", testItem1.getUsername());
    }

    @Test
    void testGetters() {
        assertEquals("item2", testItem2.getItemName());
        assertEquals("password!2", testItem2.getPassword());
        assertEquals("username?3", testItem2.getUsername());
        
    }

}