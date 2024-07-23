package br.dev.codex.dataProvider.standard;

import org.testng.annotations.DataProvider;

public class CepDataProvider {

    @DataProvider(name = "cepInvalidoProvider")
    public static Object[][] cepInvalidoProvider() {
        return new Object[][] {
                {"000000000"},
                {"123456"},
                {"95010A10"}
        };
    }
}

