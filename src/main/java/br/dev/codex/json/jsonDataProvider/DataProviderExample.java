package br.dev.codex.json.jsonDataProvider;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class DataProviderExample {

    @DataProvider(name = "data")
    public Object[][] jsonDataProvider() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("src/test/resources/dataUser/data.json"); // Substitua pelo caminho real do seu arquivo JSON
        UserData[] userDataArray = mapper.readValue(file, UserData[].class);
        Object[][] data = new Object[userDataArray.length][3];
        for (int i = 0; i < userDataArray.length; i++) {
            data[i][0] = userDataArray[i].getEmail();
            data[i][1] = userDataArray[i].getName();
            data[i][2] = userDataArray[i].getPassword();
        }
        return data;
    }

    @Test(dataProvider = "data")
    public void testMethod(String email, String name, String password) {
        System.out.println(email);
        System.out.println(name);
        System.out.println(password);
    }

    // Classe interna para mapear os dados do JSON
    @Setter
    @Getter
    static class UserData {
        private String email;
        private String name;
        private String password;

    }
}