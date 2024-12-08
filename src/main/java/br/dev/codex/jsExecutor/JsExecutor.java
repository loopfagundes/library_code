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
}