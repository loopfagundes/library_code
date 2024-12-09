package br.dev.codex.jsExecutor;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JsExecutor {

    // borda vermelha 2px
    public static void highlight(WebDriver driver, WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].setAttribute('style', 'background: white; border: 2px solid red;');", element);
    }

    // js click
    public static void click(WebDriver driver, WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", element);
    }

    // rolar baixo
    public static void scrollIntoView(WebDriver driver, WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView()", element);
    }

    // validar se imagem esta presente
    public boolean imagePresent(WebDriver driver, WebElement element) {
        Object result = ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].complete && " +
                        "typeof arguments[0].naturalWidth != \"undefined\" && " +
                        "arguments[0].naturalWidth > 0", element);
        boolean loaded = false;
        if (result instanceof Boolean) {
            loaded = (boolean) result;
        }
        return loaded;
    }

    //Metodos juntos = highligt e injectHighlightStyle
    public static void highlight(WebDriver driver, WebElement locator) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].classList.add('highlight');", locator);
    }

    public static void injectHighlightStyle(WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript(
                "if (!document.getElementById('highlight-style')) {" +
                        "  var style = document.createElement('style');" +
                        "  style.id = 'highlight-style';" +
                        "  style.innerHTML = '.highlight { background: white; border: 2px solid red; }';" +
                        "  document.head.appendChild(style);" +
                        "}");
    }

    public static void highlightElementWithJs(WebDriver driver, WebElement locator) {
        JsExecutor.injectHighlightStyle(driver);
        JsExecutor.highlight(driver, locator);
    }


    // o tempo de carregamento da pagina
    public static void loadtime() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        Long loadEventEnd = (Long) js.executeScript("return window.performance.timing.loadEventEnd;");
        Long navigationStart = (Long) js.executeScript("return window.performance.timing.navigationStart;");
        System.out.println("tempo de carregando" + (loadEventEnd - navigationStart) + "ms");
        String LoadTime = String.valueOf(loadEventEnd - navigationStart);
        Assert.assertEquals(LoadTime, "3000");
    }

    // outro melhorar versao
    public static void loadTimePage(WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        Long loadEventEnd = (Long) jse.executeScript("return window.performance.timing.loadEventEnd;");
        Long navigationStart = (Long) jse.executeScript("return window.performance.timing.navigationStart;");
        long loadTime = loadEventEnd - navigationStart;
        Report.logCapture(Status.INFO, "Tempo de carregamento da página: " + loadTime + "ms");
        long timeLimit = 3000;
        Assert.assertTrue(loadTime <= timeLimit, "O tempo de carregamento excedeu o limite esperado. Tempo: " + loadTime + "ms");
    }

    // outro melhorar versao
    public static void pageLoadTime(WebDriver driver, long timeLimitMs) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        Long loadEventEnd = (Long) jse.executeScript("return window.performance.timing.loadEventEnd;");
        Long navigationStart = (Long) jse.executeScript("return window.performance.timing.navigationStart;");
        if (loadEventEnd == 0) {
            Report.logCapture(Status.FAIL, "A página ainda não terminou de carregar ou 'loadEventEnd' não está acessível.");
            Assert.fail("Erro ao capturar o tempo de carregamento da página. 'loadEventEnd' é zero.");
            return;
        }
        long loadTime = loadEventEnd - navigationStart;
        Report.logCapture(Status.INFO, "Tempo de carregamento da página: " + loadTime + "ms");
        Assert.assertTrue(loadTime <= timeLimitMs,
                "O tempo de carregamento da página excedeu o limite esperado. Tempo: " + loadTime + "ms, Limite: " + timeLimitMs + "ms"
        );
    }

    //versao clean code
    public static void verificarTempoDeCarregamento(WebDriver driver, long timeLimitMs) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        long loadEventEnd = (long) jsExecutor.executeScript("return window.performance.timing.loadEventEnd;");
        long navigationStart = (long) jsExecutor.executeScript("return window.performance.timing.navigationStart;");
        if (loadEventEnd == 0) {
            Report.logCapture(Status.FAIL, "A página ainda não terminou de carregar ou 'loadEventEnd' não está acessível.");
            throw new IllegalStateException("'loadEventEnd' é zero. Não foi possível calcular o tempo de carregamento.");
        }
        long loadTime = loadEventEnd - navigationStart;
        Report.logCapture(Status.INFO, "Tempo de carregamento da página: " + loadTime + "ms");
        if (loadTime > timeLimitMs) {
            String mensagemErro = String.format(
                    "Tempo de carregamento excedeu o limite. Tempo: %dms, Limite: %dms",
                    loadTime, timeLimitMs
            );
            Report.logCapture(Status.FAIL, mensagemErro);
            throw new AssertionError(mensagemErro);
        }
        Report.logCapture(Status.PASS, "O tempo de carregamento da página está dentro do limite esperado.");
    }
}