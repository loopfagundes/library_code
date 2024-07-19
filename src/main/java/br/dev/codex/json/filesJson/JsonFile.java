package br.dev.codex.json.filesJson;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonFile {

    private static void ListToJson(String keys) {
        List<Description> descriptions = new ArrayList<>();
        descriptions.add(new Description(keys));
        ObjectMapper objectMapper = new ObjectMapper();
        createFileJson(objectMapper, descriptions);
    }

    private static void createFileJson(ObjectMapper objectMapper, List<Description> descriptions) {
        try {
            objectMapper.writeValue(new File("src/test/resources/dataUser/descriptions.json"), descriptions);
            System.out.println("Arquivo JSON criado com sucesso!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}