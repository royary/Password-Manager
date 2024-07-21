package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.Item;
import model.ItemList;

// CITATION: This class has been modeled from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            ItemList il = new ItemList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();



            fail("IOException was expected");



            
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            ItemList il = new ItemList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyItemList.json");
            writer.open();
            writer.write(il);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyItemList.json");
            il = reader.read();
            assertEquals(0, il.getItems().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            ItemList il = new ItemList();
            il.addItem(new Item("item1", "111", "username1"));
            il.addItem(new Item("item2", "222", "username2"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralItemList.json");
            writer.open();
            writer.write(il);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralItemList.json");
            il = reader.read();
            List<Item> items = il.getItems();
            assertEquals(2, items.size());
            checkItem("item1", "111", "username1", items.get(0));
            checkItem("item2", "222", "username2", items.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
