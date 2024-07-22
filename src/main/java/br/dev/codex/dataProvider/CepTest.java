package br.dev.codex.dataProvider;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.containsString;
import static org.testng.Assert.assertEquals;

public class CepTest {

    private static final String viaCepEndpoint = "https://viacep.com.br/ws/";

    @DataProvider(name = "cepInvalidoProvider")
    public Object[][] cepInvalidoProvider() {
        return new Object[][] {
                {"000000000"},
                {"1234"},
                {"95010A10"},
                {"99999999"}
        };
    }

    @Test(dataProvider = "cepInvalidoProvider", description = "Testa contrato para CEP inválido")
    public void deveRetornar400QuandoCepForInvalido(String cepInvalido) {
        Response response = RestAssured
                .when()
                .get(viaCepEndpoint + cepInvalido + "/json");
        assertEquals(response.statusCode(), 400);
        String responseBody = response.getBody().asString();
        assertEquals(responseBody.contains("Http 400"), true);
        assertEquals(responseBody.contains("Verifique a URL"), true);
        assertEquals(responseBody.contains("{Bad Request}"), true);
    }
}

