package persistence;

import org.json.JSONObject;

// CITATION: This class has been modeled from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents the interface for making class data writable as json
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
