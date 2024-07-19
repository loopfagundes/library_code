package br.dev.codex.json.converterToSchema;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonToSchemaConverter {

    public static JSONObject convertJsonToSchema(JSONObject json) {
        JSONObject schema = new JSONObject();
        schema.put("type", "object");
        JSONObject properties = new JSONObject();

        for (String key : json.keySet()) {
            Object value = json.get(key);
            JSONObject propertySchema = new JSONObject();
            if (value instanceof JSONObject) {
                propertySchema = convertJsonToSchema((JSONObject) value);
            } else if (value instanceof JSONArray) {
                propertySchema.put("type", "array");
                if (!((JSONArray) value).isEmpty()) {
                    Object arrayElement = ((JSONArray) value).get(0);
                    if (arrayElement instanceof JSONObject) {
                        propertySchema.put("items", convertJsonToSchema((JSONObject) arrayElement));
                    } else {
                        propertySchema.put("items", new JSONObject().put("type", getType(arrayElement)));
                    }
                } else {
                    propertySchema.put("items", new JSONObject());
                }
            } else {
                propertySchema.put("type", getType(value));
            }
            properties.put(key, propertySchema);
        }
        schema.put("properties", properties);
        return schema;
    }

    private static String getType(Object value) {
        if (value instanceof Integer) {
            return "integer";
        } else if (value instanceof Long) {
            return "integer";
        } else if (value instanceof Double) {
            return "number";
        } else if (value instanceof Boolean) {
            return "boolean";
        } else if (value instanceof String) {
            return "string";
        } else {
            return "object";
        }
    }

    public static void main(String[] args) {
        try {
            String jsonFilePath = "src/test/resources/examples/example.json"; // <-- o caminho da pasta que tem já dentro no arquivo de JSON
            Path path = Paths.get(jsonFilePath);
            if (!Files.exists(path)) {
                throw new IOException("File not found: " + jsonFilePath);
            }
            String jsonString = new String(Files.readAllBytes(path));
            JSONObject json = new JSONObject(new JSONTokener(jsonString));

            JSONObject jsonSchema = convertJsonToSchema(json);
            System.out.println(jsonSchema.toString(4));
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}
