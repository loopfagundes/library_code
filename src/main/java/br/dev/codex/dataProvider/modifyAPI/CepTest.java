package br.dev.codex.dataProvider.modifyAPI;

public class CepTest {

    @Epic("Teste de contrato")
    @Feature("Teste o CEP invalido com Data Provider.")
    @Description("O método de busca do CEP invalido com Data Provider deve retornar o status 400.")
    @Test(dataProvider = "zipCodeInvalidProvider", dataProviderClass = CepDataProvider.class)
    public void test400(String zipCodeInvalid) {
        given()
                .spec(baseUrl)
                .when()
                .get( zipCodeInvalid + "/json")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body(containsString("Http 400"))
                .body(containsString("Verifique a URL"))
                .body(containsString("{Bad Request}"));
    }
}
