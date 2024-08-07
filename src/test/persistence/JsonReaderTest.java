package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.Item;
import model.ItemList;

/**
 * Unit tests for the JsonReader class
 */
// CITATION: This class has been modeled from
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ItemList il = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyItemList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyItemList.json");
        try {
            ItemList il = reader.read();
            assertEquals(0, il.getItems().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralItemList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralItemList.json");
        try {
            ItemList il = reader.read();
            List<Item> items = il.getItems();
            assertEquals(2, items.size());
            checkItem("item1", "111", "username1", items.get(0));
            checkItem("item2", "222", "username2", items.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
