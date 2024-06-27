package br.dev.codex.jsAlert;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

public class JsAlert {
    private static Alert alert;

    public static Alert accept(WebDriver driver) {
        alert = driver.switchTo().alert();
        alert.accept();
        return alert;
    }

    public static Alert getText(WebDriver driver) {
        alert = driver.switchTo().alert();
        alert.getText();
        return alert;
    }

    public static Alert dismiss(WebDriver driver) {
        alert = driver.switchTo().alert();
        alert.dismiss();
        return alert;
    }

    public static Alert sendKeys(WebDriver driver, String text) {
        alert = driver.switchTo().alert();
        alert.sendKeys(text);
        return alert;
    }
}