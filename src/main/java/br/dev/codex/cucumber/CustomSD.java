package br.dev.codex.cucumber;

public class CustomSD {

    /* Imports/Dependencias: Cucumber/Selenium

    /*
     * Ulitizar o properties com assertEquals
     * Porem, cuidado, precisa criar antes um metodo dentro classe Element
     */
    @Então("Escrever {string} com properties")
    public void my_name_in_login(String locator) throws Exception {
        new Element(locator).assertEquals("Olá User_1,");
    }

    /*
     * Armazenar todos dados com AssertEquals
     * Porem, cuidado, precisa criar antes um metodo dentro classe Element
     */
    @Então("Armazeno as informações do {string} e valido se {string}")
    public void data_storage(String dados, String locator) throws Exception {
        new Element(locator).setData(dados, locator);
    }
}