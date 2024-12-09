package br.dev.codex.asserts;

public class AssertsTest {

    private void validarSeCarrinhoEstaVazio() throws Exception {
        boolean primeiroItemPresente = yourCart.verificarONomeDoPrimeiroItemLabel().isDisplayed();
        boolean segundoItemPresente = yourCart.verificarONomeDoSegundoItemLabel().isDisplayed();

        if (primeiroItemPresente || segundoItemPresente) {
            if (primeiroItemPresente) {
                click(yourCart.primeiroItemRemoveButton());
            }
            if (segundoItemPresente) {
                click(yourCart.segundoItemRemoveButton());
            }

            Report.logCapture(Status.FAIL,
                    "Os itens do carrinho não estão vazios. Foram encontrados itens não removidos."
            );

            // Assertivas para validação detalhada
            if (primeiroItemPresente) {
                assertEquals(
                        yourCart.verificarONomeDoPrimeiroItemLabel(),
                        "Sauce Labs Backpack",
                        "O primeiro item não deveria estar no carrinho."
                );
            }
            if (segundoItemPresente) {
                assertEquals(
                        yourCart.verificarONomeDoSegundoItemLabel(),
                        "Sauce Labs Fleece Jacket",
                        "O segundo item não deveria estar no carrinho."
                );
            }
        } else {
            Report.logCapture(Status.PASS, "O carrinho está vazio.");
        }
    }
}