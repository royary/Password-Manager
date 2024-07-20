package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Item;
import model.ItemList;

// CITATION: This class has been modeled from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a reader that can read a saved Item List from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Item List from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ItemList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseItemList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // parses Item List from JSON object and returns it
    private ItemList parseItemList(JSONObject jsonObject) {
        ItemList il = new ItemList();
        addItems(il, jsonObject);
        return il;
    }

    // MODIFIES: il
    // EFFECTS: parses Items from JSON object and adds them to ItemList
    private void addItems(ItemList il, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Items");
        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            addItem(il, nextItem);
        }
    }

    // MODIFIES: il
    // EFFECTS: parses Item from JSON object and adds it to ItemList
    private void addItem(ItemList il, JSONObject jsonObject) {
        String itemName = jsonObject.getString("item name");
        String password = jsonObject.getString("password");
        String username = jsonObject.getString("username");
        Item item = new Item(itemName, password, username);
        il.addItem(item);
    }

}
