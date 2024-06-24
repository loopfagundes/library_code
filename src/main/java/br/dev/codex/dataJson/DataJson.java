package br.dev.codex.dataJson;

import java.io.IOException;

public class DataJson {

    //Imports e Dependencias: selenium/Json/Cucumber
    //Imports: FileOperation, DriverManager, Cucumber
    // Metodo é só para cucumber, mas pode ser outro metodo

     @Então("Carrego as informações de sessão do usuário 1")
    public void load_session_info1() throws IOException {
        LocalStorage local = ((WebStorage) DriverManager.getDriver()).getLocalStorage();
        String name = FileOperation.getData("dataUser_1", "name");
        String email = FileOperation.getData("dataUser_1", "email");
        String password = FileOperation.getData("dataUser_1", "password");
        String numberAccount = FileOperation.getData("dataUser_1", "numberAccount");
        String digit = FileOperation.getData("dataUser_1", "digit");
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("email", email);
        json.put("password", password);
        json.put("accountNumber", numberAccount + "-" + digit);
        json.put("balance", 1000);
        json.put("logged", false);
        local.setItem(email, String.valueOf(json));

        JSONArray array = new JSONArray();
        JSONObject json2 = new JSONObject();
        json.put("date", "23/06/2024");
        json.put("description", "Saldo adicionado ao abrir conta");
        json.put("id", "8cc9adfa-b81c-4e4d-b85c-fd0fa5ce497d");
        json.put("transferValue", 1000);
        json.put("type", "Abertura de conta");
        array.set(0, String.valueOf(json2));
        local.setItem("transaction:" + email, String.valueOf(array));
    }
}
